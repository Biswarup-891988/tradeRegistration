package com.reg.controller;

import java.util.List;
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
import com.reg.service.RegistrationService;

@RestController
@RequestMapping("/reg")
public class ResgistrationRestController {

  private final RegistrationService resgistrationService;

  public ResgistrationRestController(RegistrationService resgistrationService) {
    this.resgistrationService = resgistrationService;
  }

  @PostMapping("/doRegister")
  public ResponseEntity createRegistration(@RequestBody User user) {
    User userdto = resgistrationService.save(user);
    HttpHeaders header = new HttpHeaders();
    header.add("Location", "/reg/user/" + userdto.getUserId());
    return new ResponseEntity<>(header, HttpStatus.CREATED);

  }

  @PostMapping("/doLogin")
  public ResponseEntity<String> userLogin(@RequestParam(name = "name") String name,
      @RequestParam(name = "password") String password) throws Exception {
    resgistrationService.validateUser(name, password);
    return new ResponseEntity<>("Login Successfull", HttpStatus.ACCEPTED);
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

}
