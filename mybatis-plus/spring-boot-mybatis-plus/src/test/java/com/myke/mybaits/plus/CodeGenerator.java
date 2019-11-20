package com.myke.mybaits.plus;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * 模块名 plusdemo
 * 表名: t_user_info
 */

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/mybatis-plus/spring-boot-mybatis-plus";
        System.out.println("projectPath:" + projectPath);
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zhangjianbin");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解

        gc.setFileOverride(true);
        //XML ResultMap
        gc.setBaseResultMap(true);
        //XML columList
        gc.setBaseColumnList(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setActiveRecord(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.myke.mybaits.plus");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            //自定义属性注入:abc
            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                //Map<String, Object> map = new HashMap<>();
                //map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                //this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });


        //cfg.setFileCreate(new IFileCreate() {
        //    @Override
        //    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
        //        // 判断自定义文件夹是否需要创建
        //        checkDir("调用默认方法创建的目录");
        //        return false;
        //    }
        //});


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        //TemplateConfig templateConfig = new TemplateConfig();
        //
        //// 配置自定义输出模板
        ////指定自定义模板路径, 位置：/resources/templates/entity2.java.ftl(或者是.vm)
        ////指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        //// templateConfig.setEntity("templates/entity2.java");
        //// templateConfig.setService();
        //// templateConfig.setController();
        //
        //templateConfig.setXml(null);
        //mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(scanner("表名"));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 执行生成
        mpg.execute();

        //Set<String> keySet = objectMap.keySet();
        //
        //System.out.println("============== 自定义模板有哪些可用参数  start=====================");
        //for (String key : keySet) {
        //    System.out.println(String.format("key:%s,vaue:%s", key, objectMap.get(key)));
        //}
        //System.out.println("============== 自定义模板有哪些可用参数  end=====================");
    }

}