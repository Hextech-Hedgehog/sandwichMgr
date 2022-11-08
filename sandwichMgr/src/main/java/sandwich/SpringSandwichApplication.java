package sandwich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import sandwich.service.*;

@SpringBootApplication
public class SpringSandwichApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSandwichApplication.class, args);
    }

    @Bean
    public AppService appService(BillService billService, CourseService courseService, UserService personService) {
        AppServiceImpl appService = new AppServiceImpl();
        appService.setBillService(billService);
        appService.setCourseService(courseService);
        appService.setPersonService(personService);
        return appService;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
