package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class DoctorScheduleModel {
    @SerializedName("bookedtime")
    private String bookedtime;

    @SerializedName("bookeddate")
    private String bookeddate;

    @SerializedName("patientid")
    private String patientid;


    @SerializedName("doctorid")
    private String doctorid;



    public String getBookedtime() {
        return bookedtime;
    }

    public String getBookeddate() {
        return bookeddate;
    }

    public String getPatientid() {
        return patientid;
    }


    public String getDoctorid()
    {
        return doctorid;
    }
}
