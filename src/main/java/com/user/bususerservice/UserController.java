package com.user.bususerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @DeleteMapping ("/user/userid")
    public ResponseEntity<User> deleteUser(@PathVariable("userid") String userid){
        return new ResponseEntity<>(userService.deleteUser(userid), HttpStatus.OK);
    }

    @PostMapping("/user/balance")
    public ResponseEntity<User> updateWallet(@RequestBody WalletTransaction walletTransaction){
        return new ResponseEntity<>(userService.updateBalance(walletTransaction), HttpStatus.OK);
    }

}
