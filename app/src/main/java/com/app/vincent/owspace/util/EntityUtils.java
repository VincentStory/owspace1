package com.app.vincent.owspace.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

/**
 * Created by czxyl171151 on 2018/4/4.
 */

public final class EntityUtils {
    private EntityUtils(){}

    public static final Gson gson=new GsonBuilder()
            .registerTypeAdapter(DateTime.class,new DateTimeTypeAdapter())
            .create();
}
