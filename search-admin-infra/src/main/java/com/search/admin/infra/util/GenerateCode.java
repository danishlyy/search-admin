package com.search.admin.infra.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GenerateCode {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://172.16.113.253:5432/search","search_admin","search_admin")
                .globalConfig(builder -> {
                    builder.author("liyongyong")
                            .fileOverride()
                            .outputDir("/Users/yongyongli/project");
                })
                .packageConfig(builder -> {
                    builder.parent("com.search.admin.infra")
                            .moduleName("storage")
                            .pathInfo(Collections.singletonMap(OutputFile.mapper,"/Users/yongyongli/project"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("index_settings");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
