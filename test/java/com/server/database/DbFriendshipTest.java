package com.server.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class DbFriendshipTest {

    @Mock
    DbAdapter dbAdapterMock;

    @Mock
    ResultSet resultSetMock;


    DbFriendship dbFriendship;

    @Before
    public void setUp() throws Exception{
        DbAdapter.setDbAdapter_instance(dbAdapterMock);
        dbFriendship = DbFriendship.getInstance();
        given(dbAdapterMock.query(anyString())).willReturn(resultSetMock);
    }

    @Test
    public void getInstance() {
        assertEquals(dbFriendship,DbFriendship.getInstance());
    }

    @Test
    public void setDbFriendshipInstance() {
        DbFriendship.setDbFriendshipInstance(dbFriendship);
        assertEquals(dbFriendship, DbFriendship.getInstance() );
    }

    @Test
    public void getStatus() throws Exception{
        given(resultSetMock.next()).willReturn(true);
        given(resultSetMock.getInt("status")).willReturn(1);

        int actual = dbFriendship.getStatus("Juan","Raul");
        assertEquals(1,actual);
    }

    @Test
    public void getUsersByStatus() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Juan");
        expected.add("Palpatine");
        expected.add("person");

        try {
            given(resultSetMock.next()).willReturn(true,true,true,false);
            given(resultSetMock.getString("firstUsername"))
                    .willReturn("Raul","Raul","person");
            given(resultSetMock.getString("secondUsername"))
                    .willReturn("Juan","Palpatine","Raul");

            assertEquals(expected,dbFriendship.getUsersByStatus("Raul",2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSentRequests() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("person");

        try {
            given(resultSetMock.next()).willReturn(true,true,true,false);
            given(resultSetMock.getString("firstUsername"))
                    .willReturn("Raul","Raul","person");
            given(resultSetMock.getString("secondUsername"))
                    .willReturn("Juan","Palpatine","Raul");
            given(resultSetMock.getString("actionUsername"))
                    .willReturn("Juan","Palpatine","Raul");

            assertEquals(expected,dbFriendship.getSentRequests("Raul"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getReceivedRequests() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Juan");
        expected.add("Palpatine");

        try {
            given(resultSetMock.next()).willReturn(true,true,true,false);
            given(resultSetMock.getString("firstUsername"))
                    .willReturn("Raul","Raul","person");
            given(resultSetMock.getString("secondUsername"))
                    .willReturn("Juan","Palpatine","Raul");
            given(resultSetMock.getString("actionUsername"))
                    .willReturn("Juan","Palpatine","Raul");

            assertEquals(expected,dbFriendship.getReceivedRequests("Raul"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}