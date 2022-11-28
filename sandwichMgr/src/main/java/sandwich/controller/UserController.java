package sandwich.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sandwich.dto.UserDTO;
import sandwich.exception.PersonNotFoundException;
import sandwich.model.Bill;
import sandwich.model.User;
import sandwich.service.AppService;
import sandwich.service.PersonService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@RestController
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class UserController {

    @Autowired
    AppService appService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("user/login")
    public ResponseEntity<? extends Object> findPersonByMailAndPwd(@RequestBody UserDTO userDTO, HttpServletRequest request) throws PersonNotFoundException {
        PersonService personService = this.appService.getPersonService();
        User user = personService.findUser(userDTO.getUsername(), userDTO.getPassword());
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        personService.login(user.getUserId());
        HttpStatus status = HttpStatus.OK;
        String apiKey = personService.getKeyByUserId(user.getUserId());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        responseHeaders.add("api-key", apiKey);
        return new ResponseEntity<>(new UserDTO(user), responseHeaders, status);
    }

    @PostMapping(path = "user/bill")
    @RolesAllowed({"ROLE_ADMIN"})
    public Bill findBillByDate(@RequestBody @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return this.appService.getBillService().findBillByMonth(date);
    }

    @GetMapping(path = "user/bill")
    @RolesAllowed({"ROLE_ADMIN"})
    public Bill getBillByDate(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return this.appService.getBillService().findBillByMonth(date);
    }


}

