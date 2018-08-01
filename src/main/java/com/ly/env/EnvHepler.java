package com.ly.env;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;

/**
 * @Description:
 * @Date 2018-08-01 20:23
 * @Author ly
 */
public class EnvHepler {
    public EnvHepler() {
    }

    public static void replaceContentToFile(String path, String env) {
        try {
            File file = ResourceUtils.getFile("classpath:" + path);
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            StringBuilder content = new StringBuilder();

            while(br.ready()) {
                String line = br.readLine();
                if (line != null && line.contains("env=")) {
                    line = handleEnv(line, env);
                }

                if (line != null && line.contains("enable.remote.conf=")) {
                    if ("local".equals(env)) {
                        line = "enable.remote.conf=false";
                    } else {
                        line = "enable.remote.conf=true";
                    }
                }

                if (line != null && line.contains("conf_server_host=")) {
                    if (!"local".equals(env) && !"dev".equals(env)) {
                        if ("deve".equals(env)) {
                            line = "conf_server_host=172.16.1.118:80";
                        } else if (!"pre".equals(env) && !"prod".equals(env)) {
                            line = "conf_server_host=10.46.64.254:80";
                        } else {
                            line = "conf_server_host=10.161.147.77:80,10.171.220.157:80";
                        }
                    } else {
                        line = "conf_server_host=120.55.188.112:80";
                    }
                }

                content.append(line);
                content.append("\r\n");
            }

            br.close();
            read.close();
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            URL url = cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path);
            FileOutputStream fs = new FileOutputStream(url.getPath());
            fs.write(content.toString().getBytes());
            fs.close();
        } catch (FileNotFoundException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }

    private static String handleEnv(String line, String env) {
        return "env=" + env;
    }

    private static void replaceContent(StringBuilder content, String env) {
        if ("local".equals(env)) {
            String useDisconf = "enable.remote.conf=true";
            int dex1 = content.indexOf(useDisconf);
            if (dex1 != -1) {
                content.delete(dex1, dex1 + useDisconf.length());
                content.append("enable.remote.conf=false").append("\r\n");
            }
        }

    }

    public static void deteleFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }

    }

    public static void writeContentToFile(String path, String con) {
        try {
            File file = new File(path);
            FileOutputStream fs = new FileOutputStream(file);
            fs.write(con.getBytes());
            fs.close();
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

}
