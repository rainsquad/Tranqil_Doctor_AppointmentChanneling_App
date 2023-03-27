package com.example.tranquilapplication.ResponseModels;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class doctorScheduleDetailsModel {
    @SerializedName("Doc_Schedule_Details")
    private List<DoctorScheduleModel> DoctorScheduleDetails;

    public List<DoctorScheduleModel> getDoctorScheduleDetails() {
        return DoctorScheduleDetails;
    }
}
