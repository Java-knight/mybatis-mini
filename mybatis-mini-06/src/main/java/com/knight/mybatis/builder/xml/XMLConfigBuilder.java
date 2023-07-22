package com.knight.mybatis.builder.xml;

import com.knight.mybatis.builder.BaseBuilder;
import com.knight.mybatis.datasource.DataSourceFactory;
import com.knight.mybatis.io.Resources;
import com.knight.mybatis.mapping.BoundSql;
import com.knight.mybatis.mapping.Environment;
import com.knight.mybatis.mapping.MappedStatement;
import com.knight.mybatis.mapping.SqlCommandType;
import com.knight.mybatis.session.Configuration;
import com.knight.mybatis.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * xml 构建器
 * @desc
 * @author knight
 * @date 2023/6/15
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // (1) 调用父类初始化 Configuration
        super(new Configuration());
        // (2) dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析配置: 类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     * @return
     */
    public Configuration parse() {
        try {
            // 解析环境
            environmentsElements(root.element("environments"));

            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    /**
     * 解析环境 environments
     * explain:
     *     <environments default="development">
     *         <environment id="development">
     *             <transactionManager type="JDBC">
     *                 <property name="..." value="..."/>
     *             </transactionManager>
     *             <dataSource type="DRUID">
     *                 <property name="driver" value="${driver}"/>
     *                 <property name="url" value="${url}"/>
     *                 <property name="username" value="${username}"/>
     *                 <property name="password" value="${password}"/>
     *             </dataSource>
     *         </environment>
     *     </environments>
     * @param context
     * @throws Exception
     */
    private void environmentsElements(Element context) throws Exception {
        String environment = context.attributeValue("default");

        List<Element> environmentList = context.elements("environment");
        for (Element e : environmentList) {
            String id = e.attributeValue("id");
            if (environment.equals(id)) {  // 区分环境dev、staging、prod
                // 事务管理器
                TransactionFactory transactionFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(e.element("transactionManager").attributeValue("type")).newInstance();

                // 数据源
                Element dataSourceElement = e.element("dataSource");
                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties props = new Properties();
                for (Element property : propertyList) {
                    props.setProperty(property.attributeValue("name"), property.attributeValue("value"));
                }
                dataSourceFactory.setProperties(props);
                DataSource dataSource = dataSourceFactory.getDataSource();

                // 构建环境
                Environment buildEnvironment = new Environment.Builder(id)
                        .transactionFactory(transactionFactory)
                        .dataSource(dataSource)
                        .build();
                configuration.setEnvironment(buildEnvironment);
            }
        }
    }

    /**
     * 解析映射器 mappers
     * @param mappers
     * @throws Exception
     */
    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mapper");
        for (Element mapper : mapperList) {
            String resource = mapper.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();

            // 命名空间
            String namespace = root.attributeValue("namespace");

            // select
            List<Element> selectNodes = root.elements("select");
            for (Element node : selectNodes) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();

                // sql语句 ? 匹配
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1; matcher.find(); i++) {
                    String group1 = matcher.group(1);
                    String group2 = matcher.group(2);
                    parameter.put(i, group2);
                    sql = sql.replace(group1, "?");
                }

                String namespaceId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

                BoundSql boundSql = new BoundSql(sql, parameter, parameterType, resultType);
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, namespaceId, sqlCommandType, boundSql).build();
                // 添加解析 SQL
                configuration.addMappedStatement(mappedStatement);
            }

            // 注册 Mapper 映射器
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}
