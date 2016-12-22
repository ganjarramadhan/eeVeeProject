package sense.nl.eevee.repository;

import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sense.nl.eevee.model.MyResponse;
import sense.nl.eevee.model.User;
import sense.nl.eevee.service.RestClient;
import sense.nl.eevee.service.UserService;

/**
 * Created by ganjarramadhan on 12/22/16.
 */

public class UserRepository {

    private UserService userService;

    public UserRepository() {
        userService = RestClient.getInstance().createInterface(UserService.class);
    }

    public void login(final String email, final String pin, final OnLoginEventListener listener){

        Call<MyResponse<User>> loginUser = userService.loginUser();
        loginUser.enqueue(new Callback<MyResponse<User>>() {
            @Override
            public void onResponse(Call<MyResponse<User>> call, Response<MyResponse<User>> response) {
                User user = response.body().getResults().get(0);
                Log.e("Email From MyResponse", user.getEmail());

                String validEmail = user.getEmail();
                String validPin = user.getLogin().getPassword();
                if (validEmail.equals(email) && validPin.equals(pin)) {
                    listener.onLoginSuccess();
                } else {
                    listener.onLoginFailed("Email and pin does not match");
                }
            }

            @Override
            public void onFailure(Call<MyResponse<User>> call, Throwable t) {
                listener.onLoginFailed(t.getMessage());
            }
        });

    }

    public void loadContact(final LoadContactListener listener){
        Call<MyResponse<User>> contactsCall = userService.getUsers();
        contactsCall.enqueue(new Callback<MyResponse<User>>() {
            @Override
            public void onResponse(Call<MyResponse<User>> call, Response<MyResponse<User>> response) {
                MyResponse<User> myResponse = response.body();
                listener.onLoadContactSuccess(myResponse.getResults());
            }

            @Override
            public void onFailure(Call<MyResponse<User>> call, Throwable t) {
                listener.onLoadContactFailed(t.getMessage());
            }
        });
    }

    public interface OnLoginEventListener {
        void onLoginSuccess();
        void onLoginFailed(String message);
    }

    public interface LoadContactListener {
        void onLoadContactSuccess(List<User> contactList);
        void onLoadContactFailed(String message);
    }

}
