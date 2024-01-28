package com.example.maker;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.example.maker.generator.JarGenerator;
import com.example.maker.generator.ScriptGenerator;
import com.example.maker.generator.file.DynamicFileGenerator;

import com.example.maker.meta.Meta;
import com.example.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {

        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);
//        输出的根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath+ File.separator+"generated";
        // 不存在就创建
        if(!FileUtil.exist(outputPath)){
            FileUtil.mkdir(outputPath);
        }

        //读取resource目录,这里与源代码存在分歧
        ClassPathResource resource = new ClassPathResource(" ");
//        System.out.println(resource.toString());
//        System.out.println(resource.getAbsolutePath());
//        System.out.println(resource.getPath().toString());
        String inputResourcePath = resource.getPath();

        System.out.println(inputResourcePath);
        //  读取java包的路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath+ File.separator+"src/main/java/"+outputBasePackagePath;


//         dataModel
        String  inputFilePath =inputResourcePath+File.separator+"templates/java/model/DataModel.java.ftl";
        String outputFilePath =outputBaseJavaPackagePath+"/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath,meta);
        // command ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command" + File.separator + "ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
//        command ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command" + File.separator + "ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //command GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command" + File.separator + "GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //command JsonGenerateCommand
//        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command" + File.separator + "JsonGenerateCommand.java.ftl";
//        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/JsonGenerateCommand.java";
//        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);


//        // util ConsoleUtil
//        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/util" + File.separator + "ConsoleUtil.java.ftl";
//        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/util/ConsoleUtil.java";
//        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
//
//
//        // util ReflexUtil
//        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/util" + File.separator + "ReflexUtil.java.ftl";
//        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/util/ReflexUtil.java";
//        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
//
//
//        // util ConvertUtil
//        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/util" + File.separator + "ConvertUtil.java.ftl";
//        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/util/ConvertUtil.java";
//        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);



        // cli CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli" + File.separator + "CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);


        // java Main
        inputFilePath = inputResourcePath + File.separator + "templates/java" + File.separator + "Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator" + File.separator + "StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator" + File.separator + "DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator" + File.separator + "MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        System.out.println("文件输出完毕");
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(),meta.getVersion());
        ScriptGenerator.doGenerate(outputPath + File.separator + "generator", "target/" + jarName);
        // 构建jar包


        // readme.md
//        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
//        outputFilePath = outputPath + File.separator + "README.md";
//        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        System.out.println("Hello world!");
    }
}
