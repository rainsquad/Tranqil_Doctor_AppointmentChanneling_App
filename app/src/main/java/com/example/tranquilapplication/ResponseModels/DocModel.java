package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class DocModel {
    @SerializedName("doctorid")
    private String doctorid;

    @SerializedName("patientid")
    private String patientid;

    @SerializedName("bookeddate")
    private String bookeddate;

    @SerializedName("bookedtime")
    private String bookedtime;



    public String getDoctorid() {
        return doctorid;
    }
    public String getPatientid() {
        return patientid;
    }

    public String getBookeddate() {
        return bookeddate;
    }
    public String getBookedtime() {
        return bookedtime;
    }

}
