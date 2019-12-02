package com.zhaofan.rxjava2.api;

import com.zhaofan.rxjava2.bean.Repo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("users/{user}/repos")
    Single<List<Repo>> listRepos(@Path("user") String user);

}
