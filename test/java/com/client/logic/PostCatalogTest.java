package com.client.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PostCatalogTest {
    PostCatalog NW = new PostCatalog();
    PostCatalog postCatalog = new PostCatalog();
    Object scat = new FoodPost();
    PostCatalog obj = NW;
    ArrayList<SuperPost> NWW = new ArrayList<SuperPost>();
    FoodPost FP = new FoodPost("","");

    @Test
    public void add(){
        NW.add(FP);
        NWW.add(FP);
        assertEquals(FP, NW.get(0));
        assertEquals(1,NW.size());
        assertEquals(NWW , NW.getPostcatalog());

    }

    @Test
    public void equals(){
        assertTrue(postCatalog.equals(NW));
        assertTrue(postCatalog instanceof PostCatalog);
        assertFalse(scat instanceof PostCatalog);
        assertEquals(obj, NW);
        assertNotEquals(scat, NW);
    }


}
