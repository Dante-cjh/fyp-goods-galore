package jiahan.chen.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 相关配置
 *
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {

    /**
     * 创建api
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                // api接口扫包范围
                .apis(RequestHandlerSelectors.basePackage("jiahan.chen.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 配置api信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("Goods Galore Api")
                .contact(new Contact("Jiahan Chen", "https://www.mayikt.com", "20104725@mail.wit.ie"))
                .version("v1.1.0")
                .title("API Test Document")
                .build();
    }

}