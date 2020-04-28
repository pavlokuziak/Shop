package pavlo.kuziak.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

import static pavlo.kuziak.shop.tool.FileTool.FILE_DIR;

@Configuration
public class StaticResourcesConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photography/**")
                .addResourceLocations(Paths.get(FILE_DIR).toUri().toString());
    }
}