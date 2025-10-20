package mx.arkn37.meli;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Meli API",
        description = "API for managing orders",
        version = "0.0.1-SNAPSHOT",
        contact = @Contact(
                name = "Angel Hernandez",
                email = "hdzangel.gran@gmail.com",
                url = "https://angelhdzgran.dev/"
        ),
        license = @License(
                name = "MIT",
                url = "https://opensource.org/licenses/MIT"
        )
))
public class MeliApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeliApplication.class, args);
    }

}
