package interview.com.basemvp;

import android.app.Application;

import interview.com.basemvp.network.BaseApi;
import interview.com.basemvp.network.NetworkAdapter;
import timber.log.Timber;

/**
 * Created by Sai on 11/06/18.
 */
public class MainApplication extends Application {

    public BaseApi baseApi;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree() {
            @Override protected String createStackElementTag(StackTraceElement element) {
                return super.createStackElementTag(element) + ":" + element.getLineNumber(); //to return line number along with the tag
            }
        });
        baseApi = NetworkAdapter.getClient().create(BaseApi.class);
    }


}
