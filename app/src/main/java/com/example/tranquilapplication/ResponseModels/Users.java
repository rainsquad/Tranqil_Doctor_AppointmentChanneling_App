package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class Users
{
   // @SerializedName("id") private int id;
    @SerializedName("bookeddate") private String bookeddate;
    @SerializedName("bookedtime") private String bookedtime;

//    public int getId() {
//        return id;
//    }


    public String getBookeddate() {
        return bookeddate;
    }

    public String getBookedtime() {
        return bookedtime;
    }
}
