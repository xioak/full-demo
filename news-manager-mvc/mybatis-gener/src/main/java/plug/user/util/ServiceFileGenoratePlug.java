package plug.user.util;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by xioa on 2016/5/28.
 */
public class ServiceFileGenoratePlug extends PluginAdapter {

    private List<String> serviceFiles = new ArrayList();

    List<String> info;

    private FullyQualifiedJavaType returnType;
    private FullyQualifiedJavaType keyType;
    private String infeType;
    private String domainName;

    private IntrospectedTable introspectedTable;


    public ServiceFileGenoratePlug() {
        System.out.println("ServiceFileGenoratePlug ....");

    }

    public boolean validate(List<String> warnings) {
        boolean valid = true;

        this.info = warnings;

        if (!StringUtility.stringHasValue(this.properties.getProperty("targetProject"))) {
            warnings.add(Messages.getString("ValidationError.18", "SqlMapConfigPlugin", "targetProject"));
            valid = false;
        }

        if (!StringUtility.stringHasValue(this.properties.getProperty("targetPackage"))) {
            warnings.add(Messages.getString("ValidationError.18", "SqlMapConfigPlugin", "targetPackage"));
            valid = false;
        }

        return valid;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

        this.returnType = introspectedTable.getRules().calculateAllFieldsClass();
        this.keyType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
        this.domainName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        this.infeType = this.properties.getProperty("targetPackage") + ".I" + this.domainName + "Service";
        this.introspectedTable = introspectedTable;
        TreeSet importedTypes = new TreeSet();
        importedTypes.add(this.returnType);
        List<GeneratedJavaFile> generatedJavaFiles = new ArrayList<>(1);
        generatedJavaFiles.add(this.servieJavaFile(importedTypes));
        generatedJavaFiles.add(this.servieImplJavaFile());
        return generatedJavaFiles;

    }


    /**
     * MapperXml 覆盖插件
     *
     * @param sqlMap
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {

        boolean mapperOverwrite = true;
        mapperOverwrite = Boolean.valueOf(properties.getProperty("mapperOverwrite", String.valueOf(mapperOverwrite)));
        if (mapperOverwrite) {
            try {
                java.lang.reflect.Field mergedField = GeneratedXmlFile.class.getDeclaredField("isMergeable");
                mergedField.setAccessible(true);
                mergedField.setBoolean(sqlMap, false);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }


    //实现类文件
    private GeneratedJavaFile servieImplJavaFile() {
        TopLevelClass topLevelClass = new TopLevelClass(this.properties.getProperty("targetPackage") + ".impl." + this.domainName + "ServiceImpl");
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        GeneratedJavaFile javaFileImpl = new GeneratedJavaFile(topLevelClass, this.properties.getProperty("targetProject"), "utf-8", this.context.getJavaFormatter());
//        //添加注解
//        topLevelClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
//        topLevelClass.addAnnotation("@Service");
        return javaFileImpl;
    }


    private Method findByKey(TreeSet importedTypes) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(this.returnType);
        method.setName("find" + this.domainName);
        if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
            FullyQualifiedJavaType introspectedColumns1 = new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType());
            importedTypes.add(introspectedColumns1);
            method.addParameter(new Parameter(introspectedColumns1, "key"));
        } else {
            List introspectedColumns = this.introspectedTable.getPrimaryKeyColumns();
            boolean annotate = introspectedColumns.size() > 1;
            if (annotate) {
                importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
            }

            StringBuilder sb = new StringBuilder();

            Parameter parameter;
            for (Iterator i$ = introspectedColumns.iterator(); i$.hasNext(); method.addParameter(parameter)) {
                IntrospectedColumn introspectedColumn = (IntrospectedColumn) i$.next();
                FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
                importedTypes.add(type);
                parameter = new Parameter(type, introspectedColumn.getJavaProperty());
                if (annotate) {
                    sb.setLength(0);
                    sb.append("@Param(\"");
                    sb.append(introspectedColumn.getJavaProperty());
                    sb.append("\")");
                    parameter.addAnnotation(sb.toString());
                }
            }
        }

        return method;
    }

    private Method addAndUpdateDomin(TreeSet importedTypes, String nameType) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        importedTypes.add(FullyQualifiedJavaType.getIntInstance());
        method.setName(nameType + this.domainName);
        method.addParameter(new Parameter(this.returnType, "recode"));
        return method;
    }

    //接口文件
    private GeneratedJavaFile servieJavaFile(TreeSet importedTypes) {

        Interface interfaze = new Interface(this.infeType);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        String rootInterface = introspectedTable.getTableConfigurationProperty("rootInterface");
        if (!StringUtility.stringHasValue(rootInterface)) {
            rootInterface = this.context.getJavaClientGeneratorConfiguration().getProperty("rootInterface");
        }

        if (StringUtility.stringHasValue(rootInterface)) {
            FullyQualifiedJavaType answer = new FullyQualifiedJavaType(rootInterface);
            interfaze.addSuperInterface(answer);
            interfaze.addImportedType(answer);
        }
        //新增
        interfaze.addMethod(this.addAndUpdateDomin(importedTypes, "add"));
        interfaze.addMethod(this.addAndUpdateDomin(importedTypes, "update"));
        //查询
        interfaze.addMethod(this.findByKey(importedTypes));
        interfaze.addImportedTypes(importedTypes);
        GeneratedJavaFile javaFile = new GeneratedJavaFile(interfaze, this.properties.getProperty("targetProject"), "utf-8", this.context.getJavaFormatter());
        return javaFile;
    }


}
