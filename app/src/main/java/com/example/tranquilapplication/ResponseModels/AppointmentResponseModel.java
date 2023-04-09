package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class AppointmentResponseModel {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    @SerializedName("user_details")
    private DocDetailsModel docDetailObject;

    public DocDetailsModel getDocDetailObject() {
        return docDetailObject;
    }

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
