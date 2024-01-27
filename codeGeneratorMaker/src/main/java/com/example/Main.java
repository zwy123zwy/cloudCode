package com.example;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.example.maker.generator.file.DynamicFileGenerator;
import com.example.maker.meta.Meta;
import com.example.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException {
        Meta metaObject = MetaManager.getMetaObject();
        System.out.println(metaObject);
//        输出的根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath+ File.separator+"generated";
//        不存在就创建
        if(!FileUtil.exist(outputPath)){
            FileUtil.mkdir(outputPath);
        }
//        读取resource目录
        ClassPathResource resource = new ClassPathResource("meta.json");
        String inputResourcePath = resource.getAbsolutePath();

        System.out.println(inputResourcePath);
//        读取java包的路径
        String outputBasePackage = metaObject.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputJavaBasePackagePath = outputPath+ File.separator+"src/main/java"+outputBasePackagePath;


//         dataModel
        String  inputFilePath =inputResourcePath+File.separator+"templates/java/model/DataModel.java.ftl";
        String outputFilePath =outputJavaBasePackagePath+"/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath,metaObject);


        System.out.println("Hello world!");
    }
}