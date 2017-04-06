package com.example.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.github.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		GithubService service = retrofit.create(GithubService.class);

		Call<List<Repo>> repos = service.listRepos("volyx");

		final Response<List<Repo>> response = repos.execute();

//		System.out.println(response.raw().body().string());
		final List<Repo> body = response.body();
		for (Repo repo : body) {
			System.out.println(repo.name);
		}

	}
}
