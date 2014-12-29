package com.example.sylly.clutch;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by Sylly on 2014-12-26.
 */
@ParseClassName("Person")
public class Person extends ParseObject {
    public String getFirstName(){
        return getString("firstName");
    }

    public void setFirstName(String firstName){
        put ("firstName", firstName);
    }

    public String getLastName(){
        return getString("lastName");
    }

    public void setLastName(String lastName){
        put ("lastName", lastName);
    }

    public static ParseQuery<Person> getQuery(){
        return ParseQuery.getQuery(Person.class);
    }
}
