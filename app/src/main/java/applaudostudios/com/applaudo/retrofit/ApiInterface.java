package applaudostudios.com.applaudo.retrofit;

import java.util.List;

import applaudostudios.com.applaudo.entities.Team;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jorge on 5/15/2017.
 */
public interface ApiInterface {
    @GET("external/applaudo_homework")
    Call<List<Team>> listTeam();
}
