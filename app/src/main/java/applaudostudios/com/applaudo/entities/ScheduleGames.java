package applaudostudios.com.applaudo.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by jorge on 5/15/2017.
 */
public class ScheduleGames implements Parcelable {
    @SerializedName("date")
    private String date;
    @SerializedName("stadium")
    private String stadium;

    protected ScheduleGames(Parcel in) {
        date = in.readString();
        stadium = in.readString();
    }

    public static final Creator<ScheduleGames> CREATOR = new Creator<ScheduleGames>() {
        @Override
        public ScheduleGames createFromParcel(Parcel in) {
            return new ScheduleGames(in);
        }

        @Override
        public ScheduleGames[] newArray(int size) {
            return new ScheduleGames[size];
        }
    };

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(stadium);
    }
}
