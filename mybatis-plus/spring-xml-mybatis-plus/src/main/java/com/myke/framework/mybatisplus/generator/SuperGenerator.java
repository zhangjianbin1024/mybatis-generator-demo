package com.myke.framework.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.File;
import java.util.*;

/**
 * 代码生成器相关配置
 */
public class SuperGenerator {

    //src 位置
    private static String javaLocation = "/src/main/java/";

    //src 测试位置
    //private static String javaLocation = "/src/test/java/";

    private static String[] SuperEntityColumns = new String[]{"createTime", "updateTime", "creator", "modify"};

    /**
     * 根据表自动生成
     *
     * @param author
     * @param packageName
     * @param tableNames
     */
    protected AutoGenerator generateByTables(String author, String packageName, String... tableNames) {
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(author);
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        //策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);

        //代码模块配置
        VelocityTemplateEngine templateEngine = new VelocityTemplateEngine();
        TemplateConfig templateConfig = getTemplateConfig();
        //注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig injectionConfig = getInjectionConfig();

        //自动生成
        return atuoGeneratorBuilder()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(templateEngine)
                .setTemplate(templateConfig)
                .setCfg(injectionConfig);
    }


    /**
     * 获取AutoGenerator
     *
     * @return
     */
    private AutoGenerator atuoGeneratorBuilder() {
        return new AutoGenerator();
    }

    /**
     * 配置模板
     *
     * @return
     */
    protected TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别

        //entity
        //templateConfig.setEntity("templates/entity.java");

        //service
        //templateConfig.setService("");
        //templateConfig.setServiceImpl("");

        //值设置为空时,controller不生成
        templateConfig.setController("");
        return templateConfig;
    }

    /**
     * 注入自定义配置变量,可以在 VM 中使用 cfg.abc 设置的值
     *
     * @return
     */
    protected InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                //该对象可以传递到模板引擎通过 cfg.xxx 引用
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        };
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @return PackageConfig 包名配置
     * @author Terry
     */
    protected PackageConfig getPackageConfig(String packageName) {
        return new PackageConfig()
                //父包名
                .setParent(packageName)
                //Service包名
                .setService("service")
                //Service实现包名
                .setServiceImpl("service.impl")
                //Mapper包名
                .setMapper("mapper")
                //Mapper XML包名
                .setXml("mapper")
                //Entity包名
                .setEntity("model.entity");
    }


    /**
     * 全局配置
     *
     * @param author
     * @return
     */
    protected GlobalConfig getGlobalConfig(String author) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setOutputDir(getOutputDir()) //生成文件的输出目录
                .setFileOverride(false) //是否覆盖文件
                .setActiveRecord(false) //开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true) //XML ResultMap
                .setBaseColumnList(true) //XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                .setOpen(false)//是否打开输出目录
                .setAuthor(author) //开发人员

                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%sEntity") //实体命名方式
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl");

        return globalConfig;
    }

    /**
     * 返回项目路径
     *
     * @return 项目路径
     * @author Terry
     */
    private String getOutputDir() {
        //获取JAVA目录
        String javaPath = getRootPath() + javaLocation;
        System.err.println(" Generator Java Path:【 " + javaPath + " 】");
        return javaPath;
    }

    /**
     * 获取根目录
     *
     * @return
     */
    private String getRootPath() {
        String file = Objects.requireNonNull(SuperGenerator.class.getClassLoader().getResource("")).getFile();
        return new File(file).getParentFile().getParentFile().getPath();
    }

    /**
     * 策略配置
     *
     * @param tableNames 表名
     * @return StrategyConfig
     * @author Terry
     */
    protected StrategyConfig getStrategyConfig(String... tableNames) {
        List<TableFill> tableFillList = getTableFills();
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置需要生成的表
        strategy.setInclude(tableNames);
        //移除表前缀
        strategy.setTablePrefix("t_ccs_");
        //【实体】是否为lombok模型（默认 false） <a href="https://projectlombok.org/">document</a>
        strategy.setEntityLombokModel(true);
        //【实体】是否为构建者模型（默认 false）
        strategy.setEntityBuilderModel(false);
        // Boolean类型字段是否移除is前缀（默认 false）
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(false);

        //逻辑删除属性名称
        //strategy.setLogicDeleteFieldName()
        //乐观锁属性名称
        //strategy.setVersionFieldName()


        //自定义实体父类
        //strategy.setSuperEntityClass("com.secoo.ccs.framework.mybatisplus.entity.BaseEntity");
        // 自定义实体，公共字段
        //strategy.setSuperEntityColumns(SuperEntityColumns);
        //表填充字段
        //strategy.setTableFillList(tableFillList);


        //自定义 mapper 父类
        strategy.setSuperMapperClass("com.secoo.ccs.base.mapper.SecooCcsMybatisPlusMarker");
        // 自定义 service 接口父类
        strategy.setSuperServiceClass("com.secoo.ccs.framework.mybatisplus.service.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.secoo.ccs.framework.mybatisplus.service.impl.BaseServiceImpl");

        return strategy;
    }


    /**
     * 获取TableFill策略
     *
     * @return
     */
    protected List<TableFill> getTableFills() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
        tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("creator", FieldFill.INSERT));
        tableFillList.add(new TableFill("modify", FieldFill.INSERT_UPDATE));
        return tableFillList;
    }

    /**
     * 配置数据源
     *
     * @return 数据源配置 DataSourceConfig
     * @author Terry
     */
    protected DataSourceConfig getDataSourceConfig() {
        //测试数据库地址
        String dbUrl = "jdbc:mysql://10.185.240.86:3306/secooErpDB?useUnicode=true&characterEncoding=utf8&noAccessToProcedureBodies=true";
        String userName = "3306_test";
        String password = "iS6CXpYqgZ8Mhjui";
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setTypeConvert(getMySqlTypeConvert())
                .setUrl(dbUrl)
                .setUsername(userName)
                .setPassword(password)
                .setDriverName("com.mysql.jdbc.Driver");
    }

    /**
     * mysql 数据库类型与java类型映射
     *
     * @return
     */
    protected MySqlTypeConvert getMySqlTypeConvert() {
        return new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.toLowerCase().equals("bit")) {
                    return DbColumnType.BOOLEAN;
                }
                if (fieldType.toLowerCase().equals("tinyint")) {
                    return DbColumnType.BOOLEAN;
                }
                if (fieldType.toLowerCase().equals("date")) {
                    return DbColumnType.DATE;
                }
                if (fieldType.toLowerCase().equals("time")) {
                    return DbColumnType.DATE;
                }
                if (fieldType.toLowerCase().equals("datetime")) {
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        };
    }

}
