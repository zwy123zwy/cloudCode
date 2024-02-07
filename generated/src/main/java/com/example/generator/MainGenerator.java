package com.example.generator;
import com.example.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;


/**
* @author Zwy
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

        String inputRootPath = ".source/acm-template-pro";
        String outputRootPath = "generated";


        String inputPath;
        String outputPath;
                boolean needGit = model.needGit;
                boolean loop = model.loop;
                    String author = model.mainTemplate.author;
                    String outputText = model.mainTemplate.outputText;

                // groupKey = git
                    if (needGit) {
                inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
                outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
                    StaticGenerator.copyFilesByHutool(inputPath, outputPath);
                inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
                outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
                    StaticGenerator.copyFilesByHutool(inputPath, outputPath);
                    }
            inputPath = new File(inputRootPath, "src/com/example/acm/MainTemplate.java.ftl").getAbsolutePath();
            outputPath = new File(outputRootPath, "src/com/example/acm/MainTemplate.java").getAbsolutePath();
                DynamicGenerator.doGenerate(inputPath, outputPath, model);
    }
}
