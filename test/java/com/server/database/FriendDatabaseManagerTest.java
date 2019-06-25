package com.server.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FriendDatabaseManagerTest {

    @Mock
    DbFriendship db_friendshipMock;

    private FriendDatabaseManager friendDatabaseManager;


    @Before
    public void setUp(){
        DbFriendship.setDbFriendshipInstance(db_friendshipMock);
        friendDatabaseManager = FriendDatabaseManager.getInstance();
    }

    @Test
    public void setFriendDatabaseManagerInstance() {
        FriendDatabaseManager.setFriendDatabaseManagerInstance(friendDatabaseManager);
        assertEquals(friendDatabaseManager,FriendDatabaseManager.getInstance());
    }

    @Test
    public void getInstance() {
        assertEquals(friendDatabaseManager,FriendDatabaseManager.getInstance());
    }

    @Test
    public void sortUsernames() {
        String username1 = "Raulaz";
        String username2 = "Juanito";
        String[] expected = {username2,username1};

        assertArrayEquals(expected,friendDatabaseManager.sortUsernames("Raulaz","Juanito"));
    }

    @Test
    public void sendRequest() {

        //TODO: FIX
        String user1 = "Juan";
        String user2 = "Raul";

        given(db_friendshipMock.friendRequest(user1,user2,0,user1)).willReturn(true);

        assertTrue(true);
    }

    @Test
    public void getUsersByStatus() {



    }

    @Test
    public void setStatus() {
    }

    @Test
    public void removeFriend() {
    }

    @Test
    public void getStatus() {
    }

    @Test
    public void getSentRequests() {
    }

    @Test
    public void getReceivedRequests() {
    }
}