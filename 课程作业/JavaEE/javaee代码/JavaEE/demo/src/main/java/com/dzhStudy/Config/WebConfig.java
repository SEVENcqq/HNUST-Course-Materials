package com.dzhStudy.Config;

import com.dzhStudy.utils.FileUploadUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ZiHao Deng
 * @Created: 2023/4/8 13:01
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射路径:FileUploadUtil.getBasePath() 获取的路径
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + FileUploadUtil.getBasePath());
    }
}

