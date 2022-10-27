package sandwich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sandwich.repository.OrderRepository;
import sandwich.repository.PersonRepository;
import sandwich.repository.SandwichRepository;
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

    @Bean
    public OrderRepository orderRepository(OrderRepository orderRepository) {
        return orderRepository;
    }

    @Bean
    public SandwichRepository sandwichRepository(SandwichRepository sandwichRepository) {
        return sandwichRepository;
    }

    @Bean
    public PersonRepository personRepository(PersonRepository personRepository) {
        return personRepository;
    }

}
