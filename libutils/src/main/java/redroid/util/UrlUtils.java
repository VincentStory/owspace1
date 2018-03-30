package redroid.util;

import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Set;

/**
 * TODO
 *
 * @author RobinVanYang created at 2017-07-18 13:11.
 */

public class UrlUtils {
    private static final String TAG = "UrlUtils";

    public static HashMap<String, String> getParams(String url){
        HashMap<String, String> parameters = new HashMap<>();
        try{
            Uri uri = Uri.parse(url);
            Set<String> keys = uri.getQueryParameterNames();

            for(String key : keys){
                parameters.put(key, uri.getQueryParameter(key));
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        return parameters;
    }
}
