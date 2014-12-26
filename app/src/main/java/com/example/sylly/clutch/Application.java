package com.example.sylly.clutch;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Sylly on 2014-12-26.
 * testing git...
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Event.class);
        ParseObject.registerSubclass(Person.class);

        //configHelper = new ConfigHelper();
        //configHelper.fetchConfigIfNeeded();
    }

}
