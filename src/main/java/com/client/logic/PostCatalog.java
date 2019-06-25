package com.client.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PostCatalog {
    private ArrayList<SuperPost> postcatalog;

    public PostCatalog() {
        this.postcatalog = new ArrayList<SuperPost>();
    }

    /**
     * sort by Date.
     *
     * @param pc postCatalog
     * @return PostCatalog
     */
    public static PostCatalog sortByDate(PostCatalog pc) {
        //returns new postcatalog that has been sorted
        ArrayList<SuperPost> posts = pc.getPostcatalog();

        posts.sort(Comparator.comparing(SuperPost::getDate));
        Collections.reverse(posts);
        PostCatalog cat = new PostCatalog();
        cat.getPostcatalog().addAll(posts);

        return cat;
    }

    public void add(SuperPost post) {
        postcatalog.add(post);
    }

    public int size() {
        return postcatalog.size();
    }

    public SuperPost get(int index) {
        return postcatalog.get(index);
    }

    public ArrayList<SuperPost> getPostcatalog() {
        return postcatalog;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PostCatalog)) {
            return false;
        }

        PostCatalog that = (PostCatalog) obj;

        return getPostcatalog() != null ? getPostcatalog()
                .equals(that.getPostcatalog()) : that.getPostcatalog() == null;
    }

}






