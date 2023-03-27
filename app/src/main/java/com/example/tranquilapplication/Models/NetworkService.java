package com.example.tranquilapplication.Models;

import com.example.tranquilapplication.ResponseModels.DoctorScheduleResponseModel;
import com.example.tranquilapplication.ResponseModels.LoginResponseModel;
import com.example.tranquilapplication.ResponseModels.QuestionnaireResponseModel;
import com.example.tranquilapplication.ResponseModels.RegistrationResponseModel;
import com.example.tranquilapplication.ResponseModels.SetupProfileResponseModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkService {
    @FormUrlEncoded
    @POST("register.php")
    Call<RegistrationResponseModel> register(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponseModel> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("profile.php")
    Call<SetupProfileResponseModel> profile(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("TestHistory.php")
    Call<QuestionnaireResponseModel> upload(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("DoctorSchedule.php")
    Call<DoctorScheduleResponseModel> doctorschedule(@FieldMap HashMap<String, String> params);



}
