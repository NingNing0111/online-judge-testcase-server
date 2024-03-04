package com.ningning0111.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 10:43
 * @Description:
 */
@Configuration
@ConfigurationProperties(
        prefix = "case"
)
@Data
public class TestCaseSystemConfig {
    private String secretKey;
}
