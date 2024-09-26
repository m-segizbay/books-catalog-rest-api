package kz.segizbay.bookscatalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI usersOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Books Service API")
                        .description("Обычный CRUD приложение")
                        .version("1.0"));
    }
}
