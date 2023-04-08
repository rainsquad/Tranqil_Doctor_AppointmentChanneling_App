package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class DocModel {
    @SerializedName("doctorid")
    private String doctorid;

    @SerializedName("patientid")
    private String patientid;

    @SerializedName("bookeddate")
    private String bookeddate;

    @SerializedName("slotsleftmorning")
    private String slotsleftmorning;
    @SerializedName("slotsleftevening")
    private String slotsleftevening;


    public String getSlotsleftmorning() {
        return slotsleftmorning;
    }

    public String getSlotsleftevening() {
        return slotsleftevening;
    }

    public String getDoctorid() {
        return doctorid;
    }
    public String getPatientid() {
        return patientid;
    }

    public String getBookeddate() {
        return bookeddate;
    }

}
