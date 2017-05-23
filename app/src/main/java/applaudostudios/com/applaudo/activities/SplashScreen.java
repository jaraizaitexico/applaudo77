package applaudostudios.com.applaudo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import applaudostudios.com.applaudo.ActivityMain;
import applaudostudios.com.applaudo.R;
import applaudostudios.com.applaudo.asynctask.AsynctaskSplash;
import applaudostudios.com.applaudo.entities.Team;
import applaudostudios.com.applaudo.retrofit.ApiClient;
import applaudostudios.com.applaudo.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorge on 5/16/2017.
 */
public class SplashScreen extends AppCompatActivity {
    public static String TAG = "Applaudo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash);

    }

    @Override
    protected void onResume() {
        super.onResume();
       /* AsynctaskSplash asynctask = new AsynctaskSplash(this);
        asynctask.execute();*/
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Team>> call = apiService.listTeam();
        call.enqueue(new Callback<List<Team>>() {

            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(SplashScreen.TAG, "teams: " + response.code());
                List<Team> teams = new ArrayList<Team>(response.body().subList(0, 10));
                Log.e(SplashScreen.TAG, "teams: " + teams.size());
                nextActivity(teams);
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.e(SplashScreen.TAG, "onFailure: " + t.getMessage());
                t.printStackTrace();
                Toast.makeText(SplashScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }


    public void nextActivity(List<Team> listTeams){
        Intent intent = new Intent(this, ActivityMain.class);
        intent.putParcelableArrayListExtra("myTeamList", (ArrayList<? extends Parcelable>) listTeams);
        startActivity(intent);
    }
}