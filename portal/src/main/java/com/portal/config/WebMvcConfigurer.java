package com.portal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @author Joyce
 */
@ServletComponentScan
@SpringBootApplication
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    /**
     * Debug使用
     */
    public WebMvcConfigurer() {
        logger.info("Scan WebMvcConfigurer ClassConfig");
    }

    /**
     * 用於解決CORS相關配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
