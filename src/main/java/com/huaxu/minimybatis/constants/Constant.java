package com.huaxu.minimybatis.constants;

/**
 * @description: <p></p>
 * @author: DongxuHua
 * @create: at 2021-03-30 5:23 下午
 * @version: 1.0.0
 * @history: modify history             <desc>
 */
public interface Constant {

    /** UTF-8编码 */
    String CHARSET_UTF8 = "UTF-8";

    /******** 在properties文件中配置信息 **************/
    String MAPPER_LOCATION = "mapper.location";

    String DB_DRIVER_CONF = "db.driver";

    String DB_URL_CONF = "db.url";

    String DB_USERNAME_CONF = "db.username";

    String DB_PASSWORD = "db.password";

    /************ mapper xml  ****************/

    /** mapper文件后缀 */
    String MAPPER_FILE_SUFFIX = ".xml";

    String XML_ROOT_LABEL = "mapper";

    String XML_ELEMENT_ID = "id";

    String XML_SELECT_NAMESPACE = "namespace";

    String XML_SELECT_RESULTTYPE = "resultType";

    /**
     * SQL类型枚举，如select、insert、update
     */
    public enum SqlType
    {
        SELECT("select"),
        INSERT("insert"),
        UPDATE("update"),
        DELETE("delete"),
        DEFAULT("default");

        private String value;

        private SqlType(String value)
        {
            this.value = value;
        }

        public String value()
        {
            return this.value;
        }
    }

}
