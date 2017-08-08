package au.com.gravitywave.amber.maps;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by georg on 8/08/2017.
 */

public class PlaceHelper {

    private static final PlaceHelper ourInstance = new PlaceHelper();
    GoogleApiClient mGoogleApiClient;

    private PlaceHelper() {

    }

    public static PlaceHelper getInstance(Context context) {
        return ourInstance;
    }
}
