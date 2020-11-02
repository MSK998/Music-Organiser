package com.nextgate.assesment.rest;

import com.nextgate.assesment.datatypes.User;
import com.nextgate.assesment.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private UserService userService;

    /**
     * POST method to authenticate a currently existing user
     * @param loginDetails the details that the user supplies
     * @return as this is a dummy login system, will just return
     * a success if the user is found
     * */

    @PostMapping("user/login")
    public String loginUser(@RequestBody User loginDetails){
        System.out.println(loginDetails.toString());
        return "Successfully logged you in";
    }

    /**
     * POST method to sign a new user up
     * @param signupDetails the login details that the user
     * inputs to log in with after this method executes
     *
     * @return again due to this being a dummy system there
     * is no actual need to do anything other than to tell
     * the user that the details have been recorded
     * */

    @PostMapping("user/signup")
    public String signupUser(@RequestBody User signupDetails){
        System.out.println(signupDetails.toString());
        return "Successfully signed up";
    }

}
