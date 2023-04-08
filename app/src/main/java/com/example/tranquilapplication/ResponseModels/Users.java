package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class Users
{
   // @SerializedName("id") private int id;
    @SerializedName("patienttestresults") private String patienttestresults;
    @SerializedName("patientid") private String patientid;

    @SerializedName("doctorid") private String doctorid;
    @SerializedName("bookeddate") private String bookeddate;
    @SerializedName("timeslotassigned") private String timeslotassigned;

    public String getTimeslotassigned() {
        return timeslotassigned;
    }

    public String getBookeddate() {
        return bookeddate;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public String getPatienttestresults() {
        return patienttestresults;
    }

    public String getPatientid() {
        return patientid;
    }
}
