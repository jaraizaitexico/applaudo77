package applaudostudios.com.applaudo.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import applaudostudios.com.applaudo.ActivityMain;
import applaudostudios.com.applaudo.R;
import applaudostudios.com.applaudo.adapter.TeamAdapter;
import applaudostudios.com.applaudo.entities.Team;

/**
 * Created by jorge on 5/16/2017.
 */
public class FragmentList extends Fragment implements TeamAdapter.OnRecyclerItemClickListener{
    private List<Team> teamList = null;
    private RecyclerView recyclerView;
    private TeamAdapter mAdapter;

    @SuppressLint("ValidFragment")
    public FragmentList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public FragmentList() {
    }

    @Override
    public void onItemClick(Team team) {
        listener.receiveFromFragment1(team);
    }


    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        public void receiveFromFragment1(Team team);
    }
    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private OnItemSelectedListener listener;

    public static FragmentList newInstance(List<Team> teamList, String someTitle) {
        FragmentList myListFragment = new FragmentList();
        Bundle args = new Bundle();
        args.putParcelableArrayList("myTeamList", (ArrayList<? extends Parcelable>) teamList);
        args.putString("someTitle", someTitle);
        myListFragment.setArguments(args);
        return myListFragment;
    }
    // This event fires 1st, before creation of fragment or any views
    // The onAttach method is called when the Fragment instance is associated with an Activity.
    // This does not mean the Activity is fully initialized.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentList.OnItemSelectedListener");
        }
    }
    // This event fires 2nd, before views are created for the fragment
    // The onCreate method is called when the Fragment instance is being created, or re-created.
    // Use onCreate for any standard setup that does not require the activity to be fully created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_list, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (ActivityMain.progDailog!=null && ActivityMain.progDailog.isShowing()) {
            ActivityMain.progDailog.dismiss();
        }
        if(teamList==null) {
            teamList = getArguments().getParcelableArrayList("myTeamList");
        }
        /*Code to add values to the recyclerview*/
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new TeamAdapter(teamList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        super.onDetach();
    }
}