package com.server.database;

import com.client.logic.Profile;
import com.client.logic.Score;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseProfileManagerTest {

    @Mock
    DatabaseProfile databaseProfileMock;

    private DatabaseProfileManager databaseProfileManager;

    @Before
    public void setUp(){
        databaseProfileManager = DatabaseProfileManager.getInstance(databaseProfileMock);
    }

    @Test
    public void getInstance() {
        assertEquals(databaseProfileManager, DatabaseProfileManager.getInstance());
    }

    @Test
    public void getProfile() {
        Profile profile = new Profile();
        profile.setName("Raul");
        profile.setScore(new Score(23,312,5,1));
        given(databaseProfileMock.getProfile(anyString(),anyString())).willReturn(profile);

        assertEquals(profile, databaseProfileManager.getProfile("Juan","Raul"));
    }
}