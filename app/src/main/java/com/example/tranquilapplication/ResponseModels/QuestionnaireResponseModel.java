package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class QuestionnaireResponseModel {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;
    @SerializedName("user_details")
    private UserDetailModel userDetailObject;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


    public UserDetailModel getUserDetailObject() {
        return userDetailObject;
    }
}
