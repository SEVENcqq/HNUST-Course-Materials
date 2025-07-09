package com.hnust.xy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: xyApplication
 * Package: com.hnust.xy
 * Description:
 *
 * @Author: 陈琪琪
 * @Create: 2023/5/21 - 21:54
 */

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class xyApplication {
    public static void main(String[] args) {
        SpringApplication.run(xyApplication.class,args);
        log.info("项目启动成功!!!");
    }
}
