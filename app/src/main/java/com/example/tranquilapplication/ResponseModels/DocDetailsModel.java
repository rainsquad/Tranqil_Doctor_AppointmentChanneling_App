package com.example.tranquilapplication.ResponseModels;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocDetailsModel {

    @SerializedName("user_details")
    private List<DocModel> docDetails;

    public List<DocModel> getDocDetails() {
        return docDetails;
    }
}
