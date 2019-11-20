package com.myke.tk.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MBGGenerator {

    public static void main(String[] args) throws Exception {
        String config = "";
        config = "native-generatorConfig.xml";


        MBGGenerator app = new MBGGenerator();
        System.out.println(app.getClass().getResource("/").getPath());
        app.generator(config);
        System.out.println(System.getProperty("user.dir"));
    }

    public void generator(String generatorConfig) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream resourceAsStream =
                this.getClass().getClassLoader().getResourceAsStream(generatorConfig);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(resourceAsStream);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
