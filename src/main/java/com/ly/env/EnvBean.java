package com.ly.env;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

/**
 * @Description: todo 判断环境，待续修改
 * @Date 2018-08-01 20:22
 * @Author ly
 */
public class EnvBean implements BeanDefinitionRegistryPostProcessor, PriorityOrdered, ApplicationContextAware {
    public static final String ENV_LOCAL = "local";
    private static String env = null;
    private static Logger logger = LoggerFactory.getLogger(EnvBean.class);

    public EnvBean() {
    }

    public static synchronized void init() {
        env = initEnv();
        logger.info("--------------bbtree  env--{}------------", env);
        setlocalEnv(env);
        String path = "disconf.properties";
        EnvHepler.replaceContentToFile(path, env);
    }

    private static void log4jDir(String projectName) {
    }

    private static String getProjectName() {
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        URL urlCan = cl != null ? cl.getResource("log4j2.xml") : ClassLoader.getSystemResource("log4j2.xml");
        String canPath = urlCan.getPath().substring(0, urlCan.getPath().indexOf(File.separator + "WEB-INF"));
        int ind = canPath.lastIndexOf(File.separator);
        return canPath.substring(ind);
    }

    private static void setlocalEnv(String env) {
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        URL url = cl != null ? cl.getResource("env.properties") : ClassLoader.getSystemResource("env.properties");
        String canPath = "";
        if (url == null) {
            String tempLog = "log4j2.xml";
            URL urlCan = cl != null ? cl.getResource(tempLog) : ClassLoader.getSystemResource(tempLog);
            if (urlCan == null) {
                tempLog = "log4j.properties";
                urlCan = cl != null ? cl.getResource(tempLog) : ClassLoader.getSystemResource(tempLog);
            }

            canPath = urlCan.getPath().substring(0, urlCan.getPath().length() - tempLog.length());
        } else {
            EnvHepler.deteleFile(url.getFile());
            canPath = url.getPath().substring(0, url.getPath().length() - "env.properties".length());
        }

        StringBuilder con = new StringBuilder();
        con.append("bbtree_env=").append(env).append("\n");
        EnvHepler.writeContentToFile(canPath + "env.properties", con.toString());
    }

    private static String initEnv() {
        env = System.getProperty("bbtree_env");
        logger.info("--------1.-{}-------", env);
        if (StringUtils.isBlank(env)) {
            env = System.getenv("bbtree_env");
            logger.info("--------2.-{}-------", env);
        }

        if (!StringUtils.isBlank(env)) {
            return env.trim();
        } else {
            String envFile;
            File file;
            FileReader read;
            BufferedReader br;
            String line;
            try {
                envFile = "/etc/profile";
                file = ResourceUtils.getFile(envFile);
                read = new FileReader(file);
                br = new BufferedReader(read);

                while(br.ready()) {
                    line = br.readLine();
                    if (line != null && line.startsWith("bbtree_env=")) {
                        env = line.split("=")[1];
                        break;
                    }
                }

                br.close();
                read.close();
            } catch (Exception var6) {
                ;
            }

            logger.info("--------3.-{}-------", env);
            if (!StringUtils.isBlank(env)) {
                return env.trim();
            } else {
                try {
                    envFile = "/etc/profile.d/bbtreeenv.sh";
                    file = ResourceUtils.getFile(envFile);
                    read = new FileReader(file);
                    br = new BufferedReader(read);

                    while(br.ready()) {
                        line = br.readLine();
                        if (line != null && line.startsWith("bbtree_env=")) {
                            env = line.split("=")[1];
                            break;
                        }
                    }

                    br.close();
                    read.close();
                } catch (Exception var5) {
                    ;
                }

                logger.info("--------4.-{}-------", env);
                if (env == null) {
                    env = "local";
                }

                return env.trim();
            }
        }
    }

    public static String getEnv() {
        return env;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public int getOrder() {
        return -2147483646;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    }

}
