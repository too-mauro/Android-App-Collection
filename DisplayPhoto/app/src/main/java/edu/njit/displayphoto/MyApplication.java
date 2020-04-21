package edu.njit.displayphoto;
import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Joe on 2/28/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}