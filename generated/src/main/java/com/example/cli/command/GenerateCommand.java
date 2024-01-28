package com.example.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.example.generator.MainGenerator;
import com.example.model.DataModel;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;




@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    @Option(
    names = {"-l" ,"--loop"},
    arity = "0..1",
    description = "是否生成循环",
    echo = true,
    interactive = true)
    private boolean loop= false;
    @Option(
    names = {"-a" ,"--author"},
    arity = "0..1",
    description = "作者注释",
    echo = true,
    interactive = true)
    private String author= "Zwy";
    @Option(
    names = {"-o" ,"--outputText"},
    arity = "0..1",
    description = "输出信息",
    echo = true,
    interactive = true)
    private String outputText= "sum = ";
    public Integer call() throws Exception {
        DataModel dataModel= new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        System.out.println("配置信息：" + dataModel);
        MainGenerator.doGenerate(dataModel);
        return 0;
    }
}

