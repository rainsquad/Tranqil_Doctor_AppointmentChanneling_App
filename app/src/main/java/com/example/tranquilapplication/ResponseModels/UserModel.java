package com.example.tranquilapplication.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;
    @SerializedName("id")
    private String id;
    @SerializedName("depressionType")
    private String depressionType;
    @SerializedName("userCategory")
    private String userCategory;
    @SerializedName("profilepicture")
    private String profilepicture;

    public String getProfilepicture() {
        return profilepicture;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }


    public String getId()
    {
        return id;
    }

    public String getDepressionType()
    {
        return depressionType;
    }





    }



