package com.pichincha.tienda.demo.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix="product.ws")
@Configuration()
@PropertySource("classpath:product_service.properties")
@Getter
@Setter

public class Configs {
    private String url;
}
