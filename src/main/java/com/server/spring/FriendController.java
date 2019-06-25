package com.server.spring;

import com.server.database.FriendDatabaseManager;
import com.server.endpoints.FriendEndpoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FriendEndpoints.FRIEND)
public class FriendController {

    @PostMapping(value = FriendEndpoints.SEND_REQUEST)
    public boolean sendRequest(@RequestBody String[] usernames) {
        return FriendDatabaseManager.getInstance().sendRequest(usernames[0], usernames[1]);
    }

    @PostMapping(value = FriendEndpoints.REMOVE_FRIEND)
    public boolean removeFriend(@RequestBody String[] usernames) {
        return FriendDatabaseManager.getInstance().removeFriend(usernames[0], usernames[1]);
    }

    @GetMapping(value = FriendEndpoints.GET_STATUS)
    public int getStatus(@RequestParam String user1, @RequestParam String user2) {
        return FriendDatabaseManager.getInstance().getStatus(user1, user2);
    }

    @PostMapping(value = FriendEndpoints.ACCEPT_REQUEST)
    public boolean acceptRequest(@RequestBody String[] usernames) {
        return FriendDatabaseManager.getInstance().setStatus(usernames[0],usernames[1], 1);
    }

    @GetMapping(value = FriendEndpoints.GET_USERS_BY_STATUS)
    public String[] getUsersByStatus(@RequestParam String name, @RequestParam int status) {
        return FriendDatabaseManager.getInstance().getUsersByStatus(name,status);
    }

    @GetMapping(value = FriendEndpoints.GET_SENT_REQUESTS)
    public String[] getSentRequests(@RequestParam String name) {
        return FriendDatabaseManager.getInstance().getSentRequests(name);
    }

    @GetMapping(value = FriendEndpoints.GET_RECEIVED_REQUESTS)
    public String[] getReceivedRequests(@RequestParam String name) {
        return FriendDatabaseManager.getInstance().getReceivedRequests(name);
    }
}
