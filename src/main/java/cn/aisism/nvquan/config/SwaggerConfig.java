package cn.aisism.nvquan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static cn.cutenine.talvapp.constant.SysConstant.*;

@Component
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))//api接口包扫描路径
                .paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SYSTEM_NAME)//设置文档的标题
                .description(SYSTEM_DESCRIPTION)//设置文档的描述->1.Overview
                .version(SYSTEM_VERSION)//设置文档的版本信息-> 1.1 Version information
                .termsOfServiceUrl(AUTHOR_URL)//设置文档的License信息->1.3 License information
                .build();
    }
}
