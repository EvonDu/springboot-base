package com.self.base.generators;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * SpringbootPlus 示例代码生成器
 */
public class ExampleGenerator {

    /**
     * 入口函数
     * @param args 参数
     */
    public static void main(String[] args) {
        // 定义配置
        Map<String,String> config = new HashMap<String,String>(){{
            // 作者信息
            put("author", "base");

            // 包名
            put("package", "com.self.base.modules");

            // 数据库配置(由于)
            put("db_url", "jdbc:mysql://localhost:3306/tc?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
            put("db_driver", "com.mysql.cj.jdbc.Driver");
            put("db_username", "root");
            put("db_password", "");
        }};

        // 执行生成器
        run(config);
    }

    /**
     * 输入输入提示
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 执行函数主方法
     * @param config 配置
     */
    private static void run(Map<String,String> config) {
        // 项目目录
        String projectPath = System.getProperty("user.dir");

        // 生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");  //设置输出目录
        globalConfig.setAuthor(config.get("author"));               //设置作者名
        globalConfig.setSwagger2(true);                             //实体属性 Swagger2 注解
        globalConfig.setOpen(false);
        mpg.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(config.get("db_url"));
        dataSourceConfig.setDriverName(config.get("db_driver"));
        dataSourceConfig.setUsername(config.get("db_username"));
        dataSourceConfig.setPassword(config.get("db_password"));
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(config.get("package"));
        mpg.setPackageInfo(pc);

        // 配置自定义属性注入
        InjectionConfig injectionConfig  = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(injectionConfig);

        // 定义模板
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        // 策略配置-父类
        /*strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");               // 设置实体父类
        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");         // 设置控制器父类*/

        // 策略配置-父类公共字段
        /*strategy.setSuperEntityColumns("id","name");                                    // 设置父类公共字段
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");*/

        // 执行生成
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
