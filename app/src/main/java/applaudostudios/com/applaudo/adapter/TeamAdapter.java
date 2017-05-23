package applaudostudios.com.applaudo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import applaudostudios.com.applaudo.R;
import applaudostudios.com.applaudo.entities.Team;

/**
 * Created by jorge on 5/17/2017.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    public  interface OnRecyclerItemClickListener{
        public void onItemClick(Team team);
    }
    private List<Team> teamList;
    private final OnRecyclerItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView  teamName, adress;
        public ImageView imageView;
        public View view;
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            teamName = (TextView) view.findViewById(R.id.teamName);
            adress = (TextView) view.findViewById(R.id.adress);
            this.view = view;
        }
        public void bind(final Team item, final OnRecyclerItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }


    public TeamAdapter(List<Team> teamList, OnRecyclerItemClickListener listener) {
        this.teamList = teamList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Team team = teamList.get(position);
        holder.teamName.setText(team.getTeamName());
        holder.adress.setText("" + team.getAddress());
        Glide.with(holder.view.getContext())
                .load(team.getImgLogo())
                .error(R.drawable.error)
                .into(holder.imageView);
        holder.bind(team, listener);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }




}