package com.huaxu.minimybatis.session.defaults;

import com.huaxu.minimybatis.constants.Constant;
import com.huaxu.minimybatis.session.Configuration;
import com.huaxu.minimybatis.session.SqlSession;
import com.huaxu.minimybatis.session.SqlSessionFactory;
import com.huaxu.minimybatis.utils.CommonUtis;
import com.huaxu.minimybatis.utils.XmlUtil;

import java.io.File;
import java.net.URL;

/**
 * @description: DefaultSqlSessionFactory
 * <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 4:39 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
        loadMappersInfo(Configuration.getProperty(Constant.MAPPER_LOCATION).replaceAll("\\.", "/"));
    }

    @Override
    public SqlSession openSession() {
        SqlSession session = new DefaultSqlSession(this.configuration);
        return session;
    }

    public void loadMappersInfo(String dirName){
        URL resources = DefaultSqlSessionFactory.class.getClassLoader().getResource(dirName);
        File mappersDir = new File(resources.getFile());
        if (mappersDir.isDirectory()){
            // 显示包下所有文件
            File[] mappers = mappersDir.listFiles();
            if (CommonUtis.isNotEmpty(mappers)){
                for (File file : mappers){
                    // 对文件夹继续递归
                    if (file.isDirectory()){
                        loadMappersInfo(dirName + "/" + file.getName());
                    }
                    else if (file.getName().endsWith(Constant.MAPPER_FILE_SUFFIX)){
                        // 只对XML文件解析
                        XmlUtil.readMapperXml(file, this.configuration);
                    }
                }

            }
        }
    }

}
