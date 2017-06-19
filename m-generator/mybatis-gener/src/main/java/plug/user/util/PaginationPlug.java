package plug.user.util;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.SimpleXMLMapperGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * Created by xions on 2016/5/22.
 */
public class PaginationPlug extends PluginAdapter {

    private String paginationType;

    private String sqlIdName;

    private String pagePackege;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        paginationType = properties.getProperty("paginationType");
        pagePackege = properties.getProperty("pagePackege");
    }

    @Override
    public boolean validate(List<String> list) {

        sqlIdName ="selectByPagination";

        return true;
    }

    public boolean sqlMapDocumentGenerated(Document document,IntrospectedTable introspectedTable) {
        if (StringUtility.stringHasValue(paginationType) && paginationType.equalsIgnoreCase("oracle")) {

            XmlElement parentElement = document.getRootElement();
            OraclePrefix(parentElement,introspectedTable);
            OraclePaginationCreat(parentElement,introspectedTable);
        }
        return true;
    }

    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

//        addPage(topLevelClass,introspectedTable,"page");
        return true;
    }




    private void OraclePaginationCreat(XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", sqlIdName));
        answer.addAttribute(new Attribute("resultMap",introspectedTable.getBaseResultMapId()));
        XmlElement pageStart = new XmlElement("include"); //$NON-NLS-1$
        pageStart.addAttribute(new Attribute("refid", "OracleDialectPrefix"));
        answer.addElement(pageStart);

        StringBuilder sb = new StringBuilder();
        sb.append("select");

        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement(introspectedTable));
        sb.setLength(0);

        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        XmlElement pageEnd = new XmlElement("include"); //$NON-NLS-1$
        pageEnd.addAttribute(new Attribute("refid", "OracleDialectSuffix"));
        answer.addElement(pageEnd);

        element.addElement(answer);
    }

    private void OraclePrefix(XmlElement parentElement, IntrospectedTable introspectedTable){

        // 产生分页语句前半部分
        XmlElement paginationPrefixElement = new XmlElement("sql");
        paginationPrefixElement.addAttribute(new Attribute("id",
                "OracleDialectPrefix"));
        XmlElement pageStart = new XmlElement("if");
        pageStart.addAttribute(new Attribute("test", "page != null"));
        pageStart.addElement(new TextElement(
                "select * from ( select row_.*, rownum rownum_ from ( "));
        paginationPrefixElement.addElement(pageStart);
        parentElement.addElement(paginationPrefixElement);

        // 产生分页语句后半部分
        XmlElement paginationSuffixElement = new XmlElement("sql");
        paginationSuffixElement.addAttribute(new Attribute("id",
                "OracleDialectSuffix"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "page != null"));
        pageEnd.addElement(new TextElement(
                "<![CDATA[ ) row_ ) where rownum_ > #{page.begin} and rownum_ <= #{page.end} ]]>"));
        paginationSuffixElement.addElement(pageEnd);
        parentElement.addElement(paginationSuffixElement);

    }

    protected XmlElement getBaseColumnListElement( IntrospectedTable introspectedTable) {
        XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
        answer.addAttribute(new Attribute("refid", //$NON-NLS-1$
                introspectedTable.getBaseColumnListId()));
        return answer;
    }


    private void addPage(TopLevelClass topLevelClass,  IntrospectedTable introspectedTable, String name) {

        pagePackege = "com.xioa.page.Page";
        topLevelClass.addImportedType(new FullyQualifiedJavaType(pagePackege));
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType(pagePackege));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(
                pagePackege), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(
                pagePackege));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }
}
