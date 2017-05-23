package applaudostudios.com.applaudo.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

import applaudostudios.com.applaudo.ActivityMain;
import applaudostudios.com.applaudo.R;
import applaudostudios.com.applaudo.entities.Team;
import applaudostudios.com.applaudo.utilities.VideoViewModified;

/**
 * Created by jorge on 5/16/2017.
 */
public class FragmentDetails extends Fragment implements OnMapReadyCallback {
    private TextView teamNameD,descriptionD ;
    private ImageView imageDetailD;
    private VideoView vidView;
    private SupportMapFragment map;
    private GoogleMap myMap;
    private Team team = null;

    @Override
    public void onMapReady(GoogleMap googleMap) {
            myMap = googleMap;
            LatLng latLon =  new LatLng(team.getLatitude(), team.getLongitude());
        Log.d("itexico", "mapReady: "+latLon.toString());
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLon , 15));
            myMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(latLon)); //Iasi, Romania
    }


    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        public void receiveFromFragmentDetails();
    }
    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private OnItemSelectedListener listener;

    public static FragmentDetails newInstance(Team team, String someTitle) {
        FragmentDetails fragmentDetails = new FragmentDetails();
        Bundle args = new Bundle();
        args.putParcelable("team", team);
        args.putString("someTitle", someTitle);
        fragmentDetails.setArguments(args);
        return fragmentDetails;
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
                    + " must implement FragmentDetails.OnItemSelectedListener");
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
        return inflater.inflate(R.layout.fragment_detail, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        map = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        teamNameD = (TextView) view.findViewById(R.id.teamNameD);
        descriptionD = (TextView) view.findViewById(R.id.descriptionD);
        imageDetailD = (ImageView) view.findViewById(R.id.imageDetailD);
        vidView = (VideoView)view.findViewById(R.id.myVideo);
        vidView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        /*final Team */team = (Team) getArguments().getParcelable("team");
        Uri vidUri = Uri.parse(team.getVideoUrl());
        vidView.setVideoURI(vidUri);
        vidView.start();
        teamNameD.setText(team.getTeamName());
        descriptionD.setText(team.getDescription());
        Glide.with(view.getContext())
                .load(team.getImgLogo())
                .error(R.drawable.error)
                .into(imageDetailD);
        ActivityMain.progDailog = ProgressDialog.show(view.getContext(), "Please wait ...", "Retrieving data ...", true);

        vidView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                ActivityMain.progDailog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

            map = SupportMapFragment.newInstance();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.map, map).commit();
            map.getMapAsync(this);

    }

    // This method is called when the fragment is no longer connected to the Activity
    // Any references saved in onAttach should be nulled out here to prevent memory leaks.
    @Override
    public void onDetach() {
        ActivityMain.progDailog.dismiss();
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        ActivityMain.progDailog.dismiss();
        super.onDestroy();
    }
}