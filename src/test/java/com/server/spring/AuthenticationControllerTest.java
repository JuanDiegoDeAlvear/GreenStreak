package com.server.spring;

import com.client.logic.SignUpUser;
import com.client.logic.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.database.DatabaseManager;
import com.server.endpoints.AuthenticationEndpoints;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    DatabaseManager databaseManagerMock;

    @Before
    public void setUp(){
        DatabaseManager.setDatabaseManagerInstance(databaseManagerMock);
    }

    @Test
    public void checkLoginAccept() {
        User given = new User();
        when(databaseManagerMock.checkLogin(anyObject())).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(AuthenticationEndpoints.AUTHENTICATION +
                    AuthenticationEndpoints.LOGIN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(given)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void checkLoginDeny() {
        User given = new User();
        when(databaseManagerMock.checkLogin(anyObject())).thenReturn(false);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(AuthenticationEndpoints.AUTHENTICATION +
                    AuthenticationEndpoints.LOGIN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(given)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("false"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void singupUserGood() {
        SignUpUser given = new SignUpUser();
        when(databaseManagerMock.signUpUser(anyObject())).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(AuthenticationEndpoints.AUTHENTICATION +
                    AuthenticationEndpoints.SIGNUP)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(given)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void singupUserBad() {
        SignUpUser given = new SignUpUser();
        when(databaseManagerMock.signUpUser(anyObject())).thenReturn(false);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(AuthenticationEndpoints.AUTHENTICATION +
                    AuthenticationEndpoints.SIGNUP)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(given)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("false"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void usernameExists(){
        String username = "Raul";

        when(databaseManagerMock.existsUser("username",username)).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(AuthenticationEndpoints.AUTHENTICATION+
                    AuthenticationEndpoints.USERNAME_EXISTS+
                    "?username=" + username))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void emailExists(){
        String email = "juan@unicorns.com";

        when(databaseManagerMock.existsUser("email",email)).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(AuthenticationEndpoints.AUTHENTICATION+
                    AuthenticationEndpoints.EMAIL_EXISTS+
                    "?email=" + email))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }
    }

}