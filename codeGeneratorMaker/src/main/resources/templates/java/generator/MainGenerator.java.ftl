package ${basePackage}.generator;
import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
* @author ${author}
* 核心生成器
*/
public class MainGenerator {

/**
* 核心生成器
* @param model model数据模型
* @throws IOException IOException
* @throws TemplateException TemplateException
*/
public static void doGenerate(Object model) throws IOException, TemplateException {

        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";


        String inputPath;
        String outputPath;

        <#list fileConfig.files as fileInfo>
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
        <#if fileInfo.generateType=="static">
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
        <#else >
        DynamicGenerator.doGenerate(inputPath, outputPath, model);
        </#if>
        </#list>
    }
}
