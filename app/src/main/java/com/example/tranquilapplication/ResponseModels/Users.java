package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class Users
{
   // @SerializedName("id") private int id;
    @SerializedName("patienttestresults") private String patienttestresults;
    @SerializedName("patientid") private String patientid;

    public String getPatienttestresults() {
        return patienttestresults;
    }

    public String getPatientid() {
        return patientid;
    }
}
