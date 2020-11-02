package com.nextgate.assesment.service;

import com.nextgate.assesment.datatypes.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String loginUser(User loginDetails){

        return "Successfully logged in";
    }

    public String signupUser(User signupDetails){
        return "Successfully created an account";
    }
}
