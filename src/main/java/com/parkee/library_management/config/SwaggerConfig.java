package com.parkee.library_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Afif Rana M
 * on 25/02/2025
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springDic() {
        return new OpenAPI()
                .info(new Info()
                        .title("library-management")
                        .version("1.0.0"));
    }
}
