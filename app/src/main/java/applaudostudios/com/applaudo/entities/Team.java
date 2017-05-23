package applaudostudios.com.applaudo.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jorge on 5/15/2017.
 */
public class Team implements Parcelable  {
    @SerializedName("id")
    private int id;
    @SerializedName("team_name")
    private String teamName;
    @SerializedName("since")
    private int since;
    @SerializedName("coach")
    private String coach;
    @SerializedName("team_nickname")
    private String teamNickname;
    @SerializedName("stadium")
    private String stadium;
    @SerializedName("img_logo")
    private String imgLogo;
    @SerializedName("img_stadium")
    private String imgStadium;
    @SerializedName("latitude")
    private float latitude;
    @SerializedName("longitude")
    private float longitude;
    @SerializedName("website")
    private String website;
    @SerializedName("tickets_url")
    private String ticketsUrl;
    @SerializedName("address")
    private String address;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("description")
    private String description;
    @SerializedName("video_url")
    private String videoUrl;
    @SerializedName("schedule_games")
    private List<ScheduleGames> scheduleGames;

    protected Team(Parcel in) {
        id = in.readInt();
        teamName = in.readString();
        since = in.readInt();
        coach = in.readString();
        teamNickname = in.readString();
        stadium = in.readString();
        imgLogo = in.readString();
        imgStadium = in.readString();
        latitude = in.readFloat();
        longitude = in.readFloat();
        website = in.readString();
        ticketsUrl = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        description = in.readString();
        videoUrl = in.readString();
        scheduleGames = in.createTypedArrayList(ScheduleGames.CREATOR);
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getTeamNickname() {
        return teamNickname;
    }

    public void setTeamNickname(String teamNickname) {
        this.teamNickname = teamNickname;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }

    public String getImgStadium() {
        return imgStadium;
    }

    public void setImgStadium(String imgStadium) {
        this.imgStadium = imgStadium;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTicketsUrl() {
        return ticketsUrl;
    }

    public void setTicketsUrl(String ticketsUrl) {
        this.ticketsUrl = ticketsUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<ScheduleGames> getScheduleGames() {
        return scheduleGames;
    }

    public void setScheduleGames(List<ScheduleGames> scheduleGames) {
        this.scheduleGames = scheduleGames;
    }

    @Override
    public String toString() {
        return "*****id: "+getId()+", Team: "+getTeamName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(teamName);
        dest.writeInt(since);
        dest.writeString(coach);
        dest.writeString(teamNickname);
        dest.writeString(stadium);
        dest.writeString(imgLogo);
        dest.writeString(imgStadium);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeString(website);
        dest.writeString(ticketsUrl);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeString(description);
        dest.writeString(videoUrl);
        dest.writeTypedList(scheduleGames);
    }
}
