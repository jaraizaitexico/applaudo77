package applaudostudios.com.applaudo;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import applaudostudios.com.applaudo.entities.Team;
import applaudostudios.com.applaudo.fragments.FragmentDetails;
import applaudostudios.com.applaudo.fragments.FragmentList;
import applaudostudios.com.applaudo.retrofit.ApiClient;
import applaudostudios.com.applaudo.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity implements FragmentList.OnItemSelectedListener, FragmentDetails.OnItemSelectedListener{
    ArrayList<Team> teams;
    Toolbar toolbar;
    int FRAGMENT_LIST = 99;
    int FRAGMENT_DETAILS = 98;
    int current_frament=99;
    int PORTRAIT= 0;
    int LANDSCAPE= 1;
    public static ProgressDialog progDailog;
    public static int current_mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            teams = getIntent().getParcelableArrayListExtra("myTeamList");
            // Replace the contents of the container with the new fragment
            FragmentList myListFragment = FragmentList.newInstance(teams, "List with teams");
            setContentView(R.layout.activity_main);
            initToolBar();
            ft.replace(R.id.fragment, myListFragment );
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            ft.commit();
            current_mode = PORTRAIT;
        }else{
            current_mode = LANDSCAPE;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            teams = getIntent().getParcelableArrayListExtra("myTeamList");
            // Replace the contents of the container with the new fragment
            FragmentList myListFragment = FragmentList.newInstance(teams, "List with teams");
            ft.replace(R.id.fragment, myListFragment );
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
            FragmentDetails myFragmentDetail = FragmentDetails.newInstance(teams.get(0), "List with teams");
            ft.replace(R.id.fragment_detail, myFragmentDetail );
            ft.commit();
            setContentView(R.layout.activity_main);
            initToolBar();
        }
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_app);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.rewind_64);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(current_frament == FRAGMENT_DETAILS){
                            receiveFromFragmentDetails();
                        }else{
                            Toast.makeText(ActivityMain.this, "No action available", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    public void receiveFromFragment1(Team team) {
        if(current_mode==PORTRAIT){
            Log.d("Fragment1", team.toString());
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
            FragmentDetails myFragmentDetails = FragmentDetails.newInstance(team, "DEtails with teams: "+team);
            ft.replace(R.id.fragment, myFragmentDetails );
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
            ft.commit();
            current_frament = FRAGMENT_DETAILS;
        }else{
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
            FragmentDetails myFragmentDetails = FragmentDetails.newInstance(team, "DEtails with teams: "+team);
            ft.replace(R.id.fragment_detail, myFragmentDetails );
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
            ft.commit();
            current_frament = FRAGMENT_DETAILS;
        }
    }

    @Override
    public void receiveFromFragmentDetails() {
        if(current_mode==PORTRAIT){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
            FragmentList myFragmentList = FragmentList.newInstance(teams, "LIST with teams");
            ft.replace(R.id.fragment, myFragmentList );
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
            ft.commit();
            current_frament = FRAGMENT_LIST;
        }
    }
}
