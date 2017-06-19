package plug.user.util;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

/**
 * Created by xions on 2016/5/22.
 */
public class MapperFileOverwritePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    private boolean mapperOverwrite = true;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        mapperOverwrite = Boolean.valueOf(properties.getProperty("mapperOverwrite", String.valueOf(mapperOverwrite)));
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        if(mapperOverwrite){
            try {
                Field mergedField = GeneratedXmlFile.class.getDeclaredField("isMergeable");
                mergedField.setAccessible(true);
                mergedField.setBoolean(sqlMap, false);
                return true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }
}
