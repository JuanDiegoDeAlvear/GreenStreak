package com.server.spring;

import com.client.logic.ConsumptionPost;
import com.client.logic.FoodPost;
import com.client.logic.TransportationPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.database.DatabaseManager;
import com.server.endpoints.PostEndpoints;
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
public class PostControllerTest {

    @Autowired
    PostController postController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    DatabaseManager databaseManagerMock;

    @Before
    public void setUp() throws Exception {
        DatabaseManager.setDatabaseManagerInstance(databaseManagerMock);
    }

    @Test
    public void addConsumptionPost() {
        ConsumptionPost post1 = new ConsumptionPost("Recycling",false);

        when(databaseManagerMock.addConsumptionPost(post1)).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(PostEndpoints.POSTS +
                    PostEndpoints.ADD_CONSUMPTION)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(post1)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void addFoodPost() {
        FoodPost post1 = new FoodPost("Dinner","Vegetarian");

        when(databaseManagerMock.addFoodPost(post1)).thenReturn(true);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(PostEndpoints.POSTS +
                    PostEndpoints.ADD_FOOD)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(post1)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));

        }catch (Exception e){
            fail();
        }

    }

    @Test
    public void addTransportationPost() {
        TransportationPost post1 = new TransportationPost(20,"Car");

        when(databaseManagerMock.addTransportationPost(post1)).thenReturn(false);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(post(PostEndpoints.POSTS +
                    PostEndpoints.ADD_TRANSPORT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(post1)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("false"));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void getUserConsumptionPosts() {
        String username = "Juan";

        ConsumptionPost post1 = new ConsumptionPost("Was cools",true);
        ConsumptionPost post2 = new ConsumptionPost("Saved kittens",false);
        ConsumptionPost post3 = new ConsumptionPost("Recycled",true);

        ConsumptionPost[] expected = {post1,post2,post3};

        when(databaseManagerMock.getConsumptionPosts("username",username)).thenReturn(expected);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(PostEndpoints.POSTS+
                    PostEndpoints.GET_CONSUMPTION+
                    "?username=" + username))
                    .andExpect(status().isOk())
                    .andExpect(content().string(om.writeValueAsString(expected)));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void getUserFoodPosts() {
        String username = "Juan";

        FoodPost post1 = new FoodPost("Lunch","Vegan");
        FoodPost post2 = new FoodPost("Lunch","Vegan");
        FoodPost post3 = new FoodPost("Dinner","Meat");

        FoodPost[] expected = {post1,post2,post3};

        when(databaseManagerMock.getFoodPosts("username",username)).thenReturn(expected);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(PostEndpoints.POSTS+
                    PostEndpoints.GET_FOOD+
                    "?username=" + username))
                    .andExpect(status().isOk())
                    .andExpect(content().string(om.writeValueAsString(expected)));

        }catch (Exception e){
            fail();
        }
    }

    @Test
    public void getUserTransportPosts() {
        String username = "Juan";

        TransportationPost post1 = new TransportationPost(23,"Walking");
        TransportationPost post2 = new TransportationPost(512,"Car");
        TransportationPost post3 = new TransportationPost(12,"Public Transport");

        TransportationPost[] expected = {post1,post2,post3};

        when(databaseManagerMock.getTransportationPosts("username",username)).thenReturn(expected);

        ObjectMapper om = new ObjectMapper();

        try {
            mockMvc.perform(get(PostEndpoints.POSTS+
                    PostEndpoints.GET_TRANSPORT+
                    "?username=" + username))
                    .andExpect(status().isOk())
                    .andExpect(content().string(om.writeValueAsString(expected)));

        }catch (Exception e){
            fail();
        }
    }
}