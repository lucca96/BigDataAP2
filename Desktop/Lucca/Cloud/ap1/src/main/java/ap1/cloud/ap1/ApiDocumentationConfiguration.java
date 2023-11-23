package ap1.cloud.ap1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
    
@Configuration
public class ApiDocumentationConfiguration { 

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("AP1 - CLOUD")
                        .description("Exemplo de utilização do Spring Boot")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Lucca Lanzelloti")
                                .email("teste@teste.com.br")));
    }
}