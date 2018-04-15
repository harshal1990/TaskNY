package com.harshal.tasknyt.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Harshal on 07-Apr-18.
 */

public class Results implements Serializable {
    public String bestsellers_date;
    public String published_date;
    public String published_date_description;
    public String previous_published_date;
    public String next_published_date;
    public List<Lists> lists;

    public String getBestsellers_date() {
        return bestsellers_date;
    }

    public void setBestsellers_date(String bestsellers_date) {
        this.bestsellers_date = bestsellers_date;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getPublished_date_description() {
        return published_date_description;
    }

    public void setPublished_date_description(String published_date_description) {
        this.published_date_description = published_date_description;
    }

    public String getPrevious_published_date() {
        return previous_published_date;
    }

    public void setPrevious_published_date(String previous_published_date) {
        this.previous_published_date = previous_published_date;
    }

    public String getNext_published_date() {
        return next_published_date;
    }

    public void setNext_published_date(String next_published_date) {
        this.next_published_date = next_published_date;
    }

    public List<Lists> getLists() {
        return lists;
    }

    public void setLists(List<Lists> lists) {
        this.lists = lists;
    }
}
