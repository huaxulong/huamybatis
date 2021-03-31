package com.huaxu.minimybatis.utils;

import com.huaxu.minimybatis.constants.Constant;
import com.huaxu.minimybatis.mapping.MappedStatement;
import com.huaxu.minimybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 8:39 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public final class XmlUtil {

    @SuppressWarnings("rawtypes")
    public static void readMapperXml(File fileName, Configuration configuration){
        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setEncoding(Constant.CHARSET_UTF8);
            Document document = saxReader.read(fileName);

            Element rootElement = document.getRootElement();
            if (!Constant.XML_ROOT_LABEL.equals(rootElement.getName())) {
                return;
            }
            String namespace = rootElement.attributeValue(Constant.XML_SELECT_NAMESPACE);
            List<MappedStatement> statements = new ArrayList<>();
            for (Iterator iterator = rootElement.elementIterator(); iterator.hasNext();){
                Element element = (Element) iterator.next();
                String elementName = element.getName();
                MappedStatement statement = new MappedStatement();
                if (Constant.SqlType.SELECT.value().equals(elementName)) {
                    String resultType = element.attributeValue(Constant.XML_SELECT_RESULTTYPE);
                    statement.setResultType(resultType);
                    statement.setSqlCommandType(Constant.SqlType.SELECT);
                }else if (Constant.SqlType.UPDATE.value().equals(elementName)){
                    statement.setSqlCommandType(Constant.SqlType.UPDATE);
                }else {
                    statement.setSqlCommandType(Constant.SqlType.DEFAULT);
                }
                String sqlId = namespace + "." + element.attributeValue(Constant.XML_ELEMENT_ID);
                statement.setSqlId(sqlId);
                statement.setNamespace(namespace);
                statement.setSql(CommonUtis.stringTrim(element.getStringValue()));
                statements.add(statement);

                configuration.addMappedStatement(sqlId, statement);
                // 这里其实是在mapperRegistry 中生产一个mapper对应的代理工厂
                configuration.addMapper(Class.forName(namespace));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
