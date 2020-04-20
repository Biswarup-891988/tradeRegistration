package com.reg.controller;

import java.util.List;
import java.util.Set;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.reg.entity.User;
import com.reg.entity.UserPortfolio;
import com.reg.service.RegistrationService;

@RestController
@RequestMapping("/reg")
public class ResgistrationRestController {

  private final RegistrationService resgistrationService;

  public ResgistrationRestController(RegistrationService resgistrationService) {
    this.resgistrationService = resgistrationService;
  }

  @PostMapping("/doRegister")
  public ResponseEntity createRegistration(@RequestBody User user) throws Exception {
    User userdto = resgistrationService.save(user);
    HttpHeaders header = new HttpHeaders();
    header.add("Location", "/reg/user/" + userdto.getUserId());
    return new ResponseEntity<>(header, HttpStatus.CREATED);

  }

  @PostMapping("/doLogin")
  public ResponseEntity<User> userLogin(@RequestParam(name = "name") String name,
      @RequestParam(name = "password") String password) throws Exception {
    User userDto = resgistrationService.validateUser(name, password);
    return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
  }

  @GetMapping("/user")
  public ResponseEntity<List<User>> getAllUser() {
    List<User> userList = resgistrationService.getUserList();
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @GetMapping("/user/{name}")
  public ResponseEntity<User> getUserInfo(@PathVariable("name") String name) throws Exception {
    User user = resgistrationService.getUserInfo(name);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/user/trade/{name}")
  public ResponseEntity<Set<UserPortfolio>> getUserPortfolio(@PathVariable("name") String name)
      throws Exception {
    Set<UserPortfolio> userList = resgistrationService.getUserPortfolio(name);
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @PostMapping("/doTrade")
  public ResponseEntity<UserPortfolio> tradeService(@RequestParam(name = "name") String name,
      @RequestParam(name = "ticker") String ticker, @RequestParam(name = "units") int units)
      throws Exception {
    UserPortfolio userDto = resgistrationService.doTrade(name, ticker, units);
    return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
  }

}
