package com.server.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.database.FriendDatabaseManager;
import com.server.endpoints.FriendEndpoints;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FriendControllerTest {

    @Autowired
    FriendController friendController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    FriendDatabaseManager databaseManagerMock;

    @Before
    public void setUp(){
        FriendDatabaseManager.setFriendDatabaseManagerInstance(databaseManagerMock);
    }

    @Test
    public void sendRequest() {
        String[] usernames = {"Juan","Raul"};

        when(databaseManagerMock.sendRequest(usernames[0],usernames[1])).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(FriendEndpoints.FRIEND +
                    FriendEndpoints.SEND_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(usernames)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void removeFriend() {
        String[] usernames = {"Juan","Raul"};

        when(databaseManagerMock.removeFriend(usernames[0],usernames[1])).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(FriendEndpoints.FRIEND +
                    FriendEndpoints.REMOVE_FRIEND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(usernames)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void getStatus() {
        String username1 = "Raul";
        String username2 = "Juan";

        when(databaseManagerMock.getStatus(username1,username2)).thenReturn(2);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(FriendEndpoints.FRIEND +
                    FriendEndpoints.GET_STATUS +
                    "?user1=" + username1 + "&user2=" + username2))
                    .andExpect(status().isOk())
                    .andExpect(content().string("2"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void getUsersByStatusBlocked() {
        String username = "Raul";
        int status = 2;

        String[] pending = {"Juan","Juana"};

        when(databaseManagerMock.getUsersByStatus(username,status)).thenReturn(pending);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(FriendEndpoints.FRIEND +
                    FriendEndpoints.GET_USERS_BY_STATUS +
                    "?name=" + username + "&status=" + status))
                    .andExpect(status().isOk())
                    .andExpect(content().string(om.writeValueAsString(pending)));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void getUsersByStatusPending() {
        String username = "Raul";
        int status = 1;

        String[] pending = {"Juan","Juana"};

        when(databaseManagerMock.getUsersByStatus(username,status)).thenReturn(pending);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(FriendEndpoints.FRIEND +
                    FriendEndpoints.GET_USERS_BY_STATUS +
                    "?name=" + username + "&status=" + status))
                    .andExpect(status().isOk())
                    .andExpect(content().string(om.writeValueAsString(pending)));

        }catch (Exception e){
            fail();
        }
    }
}