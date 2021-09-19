package com.hadestrb.zibo.Service;

import com.google.gson.JsonObject;
import com.hadestrb.zibo.Model.HardwareKey;
import com.hadestrb.zibo.Model.RegisterDisplay;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    //String URL = "http://192.168.1.10:3000/";

    @POST("required-file")
    Call<JsonObject> getRequireFile(@Body HardwareKey hardwareKey);

    @POST("register-display")
    Call<JsonObject> Register(@Body RegisterDisplay registerdisplay);

    @POST("schedule")
    Call<JsonObject> getSchedule(@Body HardwareKey hardwareKey);

    @GET("xmds.php")
    Call<String> getURLVideo(@Query("file") String file,
                             @Query("displayId") String displayID,
                             @Query("type") String type,
                             @Query("itemId") String itemID);


}
