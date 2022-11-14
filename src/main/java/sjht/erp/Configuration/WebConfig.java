package sjht.erp.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String connectPath = "/images/*";
    final Path FILE_ROOT = Paths.get("C:/Users/CMY/git/erp/src/main/resources/static/images").toAbsolutePath().normalize();
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(FILE_ROOT.toUri().toString());
    }
}