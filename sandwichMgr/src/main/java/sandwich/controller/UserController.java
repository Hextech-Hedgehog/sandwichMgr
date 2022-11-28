package sandwich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import sandwich.dto.UserDTO;
import sandwich.exception.UserNotFoundException;
import sandwich.model.Bill;
import sandwich.service.AppService;
import sandwich.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class UserController {

    @Autowired
    AppService appService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("user/login")
    public ResponseEntity<? extends Object> findPersonByMailAndPwd(@RequestBody UserDTO userDTO, HttpServletRequest request) throws UserNotFoundException {
        UserService userService = this.appService.getPersonService();
        //User user = userService.findUser(userDTO.getUsername(), userDTO.getPassword());
        return null;
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

