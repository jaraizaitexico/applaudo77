package applaudostudios.com.applaudo.asynctask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import applaudostudios.com.applaudo.ActivityMain;
import applaudostudios.com.applaudo.activities.SplashScreen;
import applaudostudios.com.applaudo.entities.Team;
import applaudostudios.com.applaudo.retrofit.ApiClient;
import applaudostudios.com.applaudo.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorge on 5/16/2017.
 */
public class AsynctaskSplash extends AsyncTask<String, Void, String> {
    Context ctx;
    SplashScreen splashScreen;
    List<Team> teams;
    public AsynctaskSplash(Context ctx) {
        this.ctx = ctx;
        this.splashScreen = (SplashScreen)ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Team>> call = apiService.listTeam();
        call.enqueue(new Callback<List<Team>>() {

            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(SplashScreen.TAG, "teams: " + response.code());
                teams = new ArrayList<Team>(response.body().subList(0, 10));
                Log.e(SplashScreen.TAG, "teams: " + teams.size());
                int i = 0;
                while (i < teams.size()) {
                    System.out.println(teams.get(i));
                    i++;
                }
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.e(SplashScreen.TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
              /*  Toast.makeText(getCtx(), t.getMessage(), Toast.LENGTH_SHORT).show();*/
            }


        });
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        splashScreen.nextActivity(teams);
    }
    public Context getCtx() {
        return ctx;
    }
}
