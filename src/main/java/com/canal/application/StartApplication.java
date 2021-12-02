package com.canal.application;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动服务之前,先确认Docker服务 Canal容器 、 MySQL容器是否启动
 * 04f0aefb6f00   canal/canal-server    "/alidata/bin/main.s…"   2 months ago    Up 7 seconds            9100/tcp, 11110/tcp, 11112/tcp, 0.0.0.0:11111->11111/tcp, :::11111->11111/tcp canal-server
 * dee7873e0203   mysql:5.7             "docker-entrypoint.s…"   6 months ago    Up 6 hours              0.0.0.0:3306->3306/tcp, :::3306->3306/tcp, 33060/tcp       mysql-database-5.7
 */
@SpringBootApplication
@EnableCanalClient
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        System.out.println("-------Canal监听服务启动完成<--------");
    }
}
