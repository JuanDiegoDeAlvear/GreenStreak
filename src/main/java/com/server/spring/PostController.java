package com.server.spring;


import com.client.logic.ConsumptionPost;
import com.client.logic.FoodPost;
import com.client.logic.TransportationPost;

import static com.server.debug.DebugTools.debug;

import com.server.database.DatabaseManager;
import com.server.endpoints.PostEndpoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(PostEndpoints.POSTS)
public class PostController {

    /**
     * Adds ConsumptionPost received in request to the database.
     *
     * @param post The post to be added
     * @return Whether the post was successfully added to the database
     */
    @PostMapping(value = PostEndpoints.ADD_CONSUMPTION)
    public boolean addConsumptionPost(@RequestBody ConsumptionPost post) {
        debug("Received post: " + post.getPostClass());

        return DatabaseManager.getInstance().addConsumptionPost(post);
    }

    /**
     * Adds FoodPost received in request to the database.
     *
     * @param post The post to be added
     * @return Whether the post was successfully added to the database
     */
    @PostMapping(value = PostEndpoints.ADD_FOOD)
    public boolean addFoodPost(@RequestBody FoodPost post) {
        debug("Received post: " + post.getPostClass());

        return DatabaseManager.getInstance().addFoodPost(post);
    }

    /**
     * Adds TransportationPost received in request to the database.
     *
     * @param post The post to be added
     * @return Whether the post was successfully added to the database
     */
    @PostMapping(value = PostEndpoints.ADD_TRANSPORT)
    public boolean addTransportationPost(@RequestBody TransportationPost post) {
        debug("Received post: " + post.getPostClass());

        return DatabaseManager.getInstance().addTransportationPost(post);
    }

    /**
     * Returns the ConsumptionPosts in array format for the given username.
     *
     * @param username The username to query the database with
     * @return The ConsumptionPost array
     */
    @GetMapping(value = PostEndpoints.GET_CONSUMPTION)
    public ConsumptionPost[] getUserConsumptionPosts(@RequestParam String username) {
        debug("Request for Consumption posts for user: " + username);

        return DatabaseManager.getInstance().getConsumptionPosts("username", username);
    }

    /**
     * Returns the FoodPosts in array format for the given username.
     *
     * @param username The username to query the database with
     * @return The FoodPost array
     */
    @GetMapping(value = PostEndpoints.GET_FOOD)
    public FoodPost[] getUserFoodPosts(@RequestParam String username) {
        debug("Request for Food posts for user: " + username);

        return DatabaseManager.getInstance().getFoodPosts("username", username);
    }

    /**
     * Returns the TranportationPosts in array format for the given username.
     *
     * @param username The username to query the database with
     * @return The TransportationPost array
     */
    @GetMapping(value = PostEndpoints.GET_TRANSPORT)
    public TransportationPost[] getUserTransportPosts(@RequestParam String username) {
        debug("Request for Transportation posts for user: " + username);

        return DatabaseManager.getInstance().getTransportationPosts("username", username);
    }

}
