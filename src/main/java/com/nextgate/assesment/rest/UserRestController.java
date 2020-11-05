package com.nextgate.assesment.rest;

import com.nextgate.assesment.datatypes.User;
import com.nextgate.assesment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * POST method to authenticate a currently existing user
     * @param loginDetails the details that the user supplies
     * @return as this is a dummy login system, will just return
     * a success if the user is found
     * */

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User loginDetails){
        if(userService.loginUser(loginDetails)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * POST method to sign a new user up
     * @param signupDetails the login details that the user
     * inputs to log in with after this method executes
     * @return again due to this being a dummy system there
     * is no actual need to do anything other than to tell
     * the user that the details have been recorded
     * */

    @PostMapping("/signup")
    public ResponseEntity signupUser(@RequestBody User signupDetails){
        if(userService.signupUser(signupDetails)){
            String message = "User successfully added";
            return ResponseEntity.ok().body(message);
        }
        return ResponseEntity.badRequest().build();
    }

}
