package com.liuscoding.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class CodeGenerator {

    @Test
    public void run() {

        //create code generator
        AutoGenerator mpg = new AutoGenerator();

        //global Config
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("E:\\guli_parent\\service\\service_edu" + "/src/main/java");
        gc.setAuthor("Li Chen");
        gc.setOpen(false);
        gc.setFileOverride(false);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ID_WORKER_STR);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);

        //config data source
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/guli?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //config package
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("edu");
        pc.setParent("com.liuscoding");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        //config strategy
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu_video");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);

        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);


        //execute
        mpg.execute();
    }
}
