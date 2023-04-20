package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class TestResults
{
    @SerializedName("id") private int id;

    @SerializedName("userid") private String userid;

    @SerializedName("testmarks") private String testmarks;
    @SerializedName("date") private String date;

    public int getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getTestmarks() {
        return testmarks;
    }

    public String getDate() {
        return date;
    }
}
