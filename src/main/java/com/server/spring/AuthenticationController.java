package com.server.spring;

import com.client.logic.SignUpUser;
import com.client.logic.User;
import com.server.database.DatabaseManager;
import com.server.endpoints.AuthenticationEndpoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthenticationEndpoints.AUTHENTICATION)
public class AuthenticationController {

    @PostMapping(value = AuthenticationEndpoints.LOGIN)
    public boolean checkLogin(@RequestBody User details) {
        return DatabaseManager.getInstance().checkLogin(details);
    }

    @PostMapping(value = AuthenticationEndpoints.SIGNUP)
    public boolean signUpUser(@RequestBody SignUpUser details) {
        return DatabaseManager.getInstance().signUpUser(details);
    }

    @GetMapping(value = AuthenticationEndpoints.USERNAME_EXISTS)
    public boolean usernameExists(@RequestParam String username) {
        return DatabaseManager.getInstance().existsUser("username",username);
    }

    @GetMapping(value = AuthenticationEndpoints.EMAIL_EXISTS)
    public boolean emailExists(@RequestParam String email) {
        return DatabaseManager.getInstance().existsUser("email",email);
    }
}
