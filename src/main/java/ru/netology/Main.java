package ru.netology;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        int port = 8080;
        String contextPath = "";
        String baseDir = new File(".").getAbsolutePath();

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(baseDir);

        var ctx = tomcat.addWebapp(contextPath, baseDir + "/src/main/webapp");
        ctx.addApplicationListener("ru.netology.config.WebAppInitializer");

        tomcat.start();
        tomcat.getServer().await();
    }
}
