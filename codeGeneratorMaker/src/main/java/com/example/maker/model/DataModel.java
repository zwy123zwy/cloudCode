package com.example.maker.model;

import lombok.Data;

/**
 * 动态模版配置
 */
@Data
public class DataModel {
    /*
    * 是否生成gitignore文件
    * */
    public  boolean needGit;
    /**
     * 是否生成循环
     */
    private boolean loop;
    /*
    用于生成核心模板文件 内部封装类
    *
    */
    public MainTemplate mainTemplate=new MainTemplate();
    @Data
    public static class MainTemplate{
        /**
         * 作者注释
         */
        private String author = "Zwy";

        /**
         * 输出信息
         */
        private String outputText = "outputInfo ";
    }

}
