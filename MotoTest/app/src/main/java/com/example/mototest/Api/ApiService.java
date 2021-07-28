package com.example.mototest.Api;

import com.example.mototest.Model.Post;
import com.example.mototest.Model.Test;
import com.example.mototest.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//https://btl60pm2.000webhostapp.com/api
public interface ApiService {
    String api="api";
//    String api="index.php";
    Gson gson = new GsonBuilder().create();
    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("https://btl60pm2.000webhostapp.com/")
//            .baseUrl("http://project3h.me/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET(api)
    Call<Test> getTest(@Query("action") String action,
                       @Query("idtest") String idtest);
    @GET(api)
    Call<Alltest> getAllTest(@Query("action") String action);
//    FOR INSERT DB OFF
    @GET(api)
    Call<AllTestQS> getAllTestAndQS(@Query("action") String action);
    @FormUrlEncoded
    @POST(api)
    Call<User> login(
            @Field("Username") String username,
            @Field("Password") String password
    );

    @FormUrlEncoded
    @POST(api)
    Call<User> register(
            @Field("Name") String name,
            @Field("Username") String username,
            @Field("Password") String password
    );

    @GET(api)
    Call<AllQues> getAllQues(@Query("action") String action);

//    UPDATE CAU HOI
    @FormUrlEncoded
    @POST(api)
    Call<Status> querryQues(
            @Field("action") String action,
            @Field("QId") String QId,
            @Field("QForm") String Qform,
            @Field("QContent") String QContent,
            @Field("QDa1") String QDa1,
            @Field("QDa2") String QDa2,
            @Field("QDa3") String QDa3,
            @Field("QDa4") String QDa4,
            @Field("QDadung") String QDadung,
            @Field("Access_token") String access_token
    );

    @FormUrlEncoded
    @POST(api)
    Call<Status> querryTest(
            @Field("action") String action,
            @Field("TestID") String TestID,
            @Field("QuesID") String QuesID,
            @Field("Access_token") String access_token
    );


//USER MANAGER
    @FormUrlEncoded
    @POST(api)
    Call<AllUser> getAllUser(@Field("action") String action,@Field("Iduser") String Iduser,@Field("Access_token") String access_token);

    @FormUrlEncoded
    @POST(api)
    Call<Status> querryUser(
            @Field("action") String action,
            @Field("Iduser") String iduser,
            @Field("Username") String Username,
            @Field("Password") String Password,
            @Field("Name") String Name,
            @Field("Permission") String Permission,
            @Field("Active") String Active,
            @Field("Recover") String Recover,
            @Field("Access_token") String access_token
    );


//    Comment
    @GET(api)
    Call<AllCmt> getAllCmt(@Query("action") String action,
                           @Query("TestId") String TestId);
    @FormUrlEncoded
    @POST(api)
    Call<Status> createCmt(
            @Field("action") String action,
            @Field("IdUser") String iduser,
            @Field("IdTest") String idtest,
            @Field("Content") String content,
            @Field("Access_token") String access_token
    );
    // Ôn tập lý thuyết
    ApiService apiservice2=new Retrofit.Builder()
            .baseUrl("https://meothi.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("index.php")
    Call<Post> getLuatGT(@Query("action") String action);

    @GET("index.php")
    Call<Post> getBienbao(@Query("action") String action);

    @GET("index.php")
    Call<Post> getXuphat(@Query("action") String action);

    @GET("index.php")
    Call<Post> getMeothilt(@Query("action") String action);

    @GET("index.php")
    Call<Post> getMeothith(@Query("action") String action);

}
