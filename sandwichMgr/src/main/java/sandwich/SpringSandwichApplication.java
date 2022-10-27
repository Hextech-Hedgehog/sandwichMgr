package sandwich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sandwich.service.*;

@SpringBootApplication
public class SpringSandwichApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSandwichApplication.class, args);
    }

    @Bean
    public AppService appService(BillService billService, CourseService courseService, PersonService personService) {
        AppServiceImpl appService = new AppServiceImpl();
        appService.setBillService(billService);
        appService.setCourseService(courseService);
        appService.setPersonService(personService);
        return appService;
    }

}
