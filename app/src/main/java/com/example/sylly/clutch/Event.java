package com.example.sylly.clutch;

/**
 * Created by Sylly on 2014-12-26.
 */

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseClassName;

import java.util.Date;

@ParseClassName("Event")
public class Event extends ParseObject{

    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put ("name", name);
    }

    public Date getDate(){
        return getDate("date");
    }

    public void setDate(Date date){
        put ("date", date);
    }

    public static ParseQuery<Event> getQuery(){
        return ParseQuery.getQuery(Event.class);
    }
}
