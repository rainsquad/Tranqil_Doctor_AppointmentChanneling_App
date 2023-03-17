package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class SetupProfileResponseModel {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
