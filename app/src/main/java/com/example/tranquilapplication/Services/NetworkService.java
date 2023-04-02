package com.example.tranquilapplication.Services;

import com.example.tranquilapplication.ResponseModels.DocResponseModel;
import com.example.tranquilapplication.ResponseModels.LoginResponseModel;
import com.example.tranquilapplication.ResponseModels.QuestionnaireResponseModel;
import com.example.tranquilapplication.ResponseModels.RegistrationResponseModel;
import com.example.tranquilapplication.ResponseModels.SetupProfileResponseModel;
import com.example.tranquilapplication.ResponseModels.Users;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    Call<DocResponseModel> doctorschedule(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("checkdoctorschedule.php")
    Call<DocResponseModel> doctorcheck(@Field("doctorid") String doctorid, @Field("bookeddate") String bookeddate);



    @GET("getdoctorscheduledates.php")
    Call<List<Users>> getUsers(

            @Query("key") String key,
             @Query("doctorid") String doctorid

    );
}
