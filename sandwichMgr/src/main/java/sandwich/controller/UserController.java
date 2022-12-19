package sandwich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import sandwich.mapper.UserMapper;
import sandwich.model.dto.BillDTO;
import sandwich.model.dto.OrderDTO;
import sandwich.model.dto.SandwichDTO;
import sandwich.exception.CourseNotFoundException;
import sandwich.exception.SessionNotFoundException;
import sandwich.mapper.BillMapper;
import sandwich.mapper.OrderMapper;
import sandwich.mapper.SandwichMapper;
import sandwich.model.dto.UserDTO;
import sandwich.model.entities.Sandwich;
import sandwich.service.AppService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AppService appService;

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return this.appService.getUserService().getAllUsers().stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "bill")
    public BillDTO getBillByDate(@RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return BillMapper.toDto(this.appService.getBillService().findBillByMonth(date));
    }

    @GetMapping(path="orders")
    public List<OrderDTO> getOrdersByDate(@RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return this.appService.getBillService().findOrdersByDate(date).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path="bill/{id}/orders")
    public List<OrderDTO> getOrdersByBillAndDate(@PathVariable("id") int billId, @RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return this.appService.getBillService().findOrdersByBillAndDate(billId, date).stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/sandwich/order")
    public void OrderSandwich(@RequestBody SandwichDTO sandwich) throws CourseNotFoundException, SessionNotFoundException {
        Sandwich sdwch = SandwichMapper.toSandwich(sandwich);
        this.appService.orderSandwich(sdwch.getUser(), sandwich.getSandwichType().getShop(), sdwch);
    }

}