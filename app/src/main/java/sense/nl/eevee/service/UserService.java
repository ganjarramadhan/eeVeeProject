package sense.nl.eevee.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sense.nl.eevee.model.MyResponse;
import sense.nl.eevee.model.User;

/**
 * Created by ahmadmuhsin on 8/2/16.
 */
public interface UserService {

    @GET("?seed=ramadhan&password=number,6")
    Call<MyResponse<User>> loginUser();

    @GET("?results=10")
    Call<MyResponse<User>> getUsers();

}
