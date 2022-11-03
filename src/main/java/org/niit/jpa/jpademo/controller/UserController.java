package org.niit.jpa.jpademo.controller;

import org.niit.jpa.jpademo.domain.User;
import org.niit.jpa.jpademo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userdetails/app/")
public class UserController
{

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService= userService;
    }

    //http://localhost:8080/userdetails/app/user
    @PostMapping("/user")
    public ResponseEntity<?> insertUser(@RequestBody User user)
    {
        return new ResponseEntity<> (userService.addUser(user), HttpStatus.CREATED);
    }

    //http://localhost:8080/userdetails/app/users
    @GetMapping("/users")
    public ResponseEntity<?> getuserdetails()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
    }
    //http://localhost:8080/userdetails/app/user/rishav@niit.com
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email)
    {
       return new ResponseEntity<>(userService.deleteUserByEmail(email),HttpStatus.OK);
    }
    //http://localhost:8080/userdetails/app/users/rameshwari
    @GetMapping("/users/{firstname}")
    public ResponseEntity<?> fetchByName(@PathVariable String firstname)
    {
        return new ResponseEntity<>(userService.getUserByFirstname(firstname),HttpStatus.FOUND);
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUser(@RequestBody User user ,@PathVariable String email)
    {
        return  new ResponseEntity<>(userService.updateUser(user,email),HttpStatus.OK);
    }

}
