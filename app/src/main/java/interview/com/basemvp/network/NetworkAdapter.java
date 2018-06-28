package interview.com.basemvp.network;

import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Sai on 11/06/18.
 * This class is to setup the retrofit client.
 */
public class NetworkAdapter {
    public static final String BASE_URL = "";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder()
//                            .addQueryParameter("api_key", "DEMO_KEY")
                            .build();
                    request = request.newBuilder().url(url).build();
                    okhttp3.Response response = chain.proceed(request);

                    return response;
                }
            }).addInterceptor(loggingInterceptor).build();

            Moshi moshi = new Moshi.Builder().build();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }
        return retrofit;
    }
}
