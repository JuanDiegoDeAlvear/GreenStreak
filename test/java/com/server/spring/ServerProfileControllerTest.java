package com.server.spring;

import com.server.database.DatabaseManager;
import com.server.endpoints.ProfileEndpoints;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerProfileControllerTest {

    @Autowired
    ProfileController profileController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    DatabaseManager databaseManager_mock;

    @Before
    public void setUp(){
        DatabaseManager.setDatabaseManagerInstance(databaseManager_mock);
    }

    @Test
    public void getProfile() {
    }

    @Test
    public void searchUsername() {
        String[] usernames = {"Raul","RaulSerd1h", "Raulidda", "Rauld12"};

        when(databaseManager_mock.searchUsername("Raul")).thenReturn(usernames);

        try {
            mockMvc.perform(get(ProfileEndpoints.PROFILE+ ProfileEndpoints.SEARCH_USERNAME+"?username=Raul"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]",is("Raul")))
                    .andExpect(jsonPath("$[1]",is("RaulSerd1h")))
                    .andExpect(jsonPath("$[2]",is("Raulidda")))
                    .andExpect(jsonPath("$[3]",is("Rauld12")));
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }
}