package sense.nl.eevee.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import sense.nl.eevee.BuildConfig;

/**
 * Created by ganjarramadhan on 12/22/16.
 */

public class RestClient {

    public static final String TAG = "eeVeRestClient";
    private static String BASE_URL = "https://randomuser.me/api/";
    private static RestClient instance;
    private Retrofit mRetrofit;

    public RestClient(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        OkHttpClient client = clientBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build();
    }

    public static RestClient getInstance() {
        if (instance == null) {
            synchronized (RestClient.class) {
                if (instance == null)
                    instance = new RestClient();
            }
        }
        return instance;
    }

    public<T> T createInterface(Class<T> T) {
        return mRetrofit.create(T);
    }

}
