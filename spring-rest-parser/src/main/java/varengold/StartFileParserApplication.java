package sirma;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"sirma"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class StartFileParserApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartFileParserApplication.class, args);
    }


}