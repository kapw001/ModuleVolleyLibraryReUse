package volleywebrequest.com.volleylibrarymoduleforreuse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mysessionmanagementlibrary.SessionManager;
import com.volleywebrequest.MakeRestRequest;
import com.volleywebrequest.VolleyWebRequest;
import com.volleywebrequest.VolleyWebResponse;

import java.util.HashMap;
import java.util.Map;

import volleywebrequest.com.mysqlitelibrary.DatabaseHandler;
import volleywebrequest.com.volleylibrarymoduleforreuse.models.ResultResponse;

/**
 * Created by yasar on 5/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements VolleyWebResponse {

    private static final String TAG = "BaseActivity";
    //    private String url = "https://api.androidhive.info/volley/string_response.html";
//    String url = "https://api.androidhive.info/volley/person_object.json";
//    String url = "https://reqres.in/api/login";
    String url = "https://amspreprod.pappaya.co.uk/vms/device/get_visitor_data";
    public MakeRestRequest volleyWebRequest;
    public SessionManager sessionManager;
    public DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyWebRequest = VolleyWebRequest.getInstance(BaseActivity.this);
        sessionManager = SessionManager.getInstance(this);
        databaseHandler = DatabaseHandler.getInstace(this);

        Log.e(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    public void makeRequest() {
        Map<String, Object> jsonData = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fromdate", "2017-08-12");
        params.put("todate", "2017-09-11");

        int[] s = {1, 2, 3, 8};

        params.put("university_id", s);
        jsonData.put("params", params);
        volleyWebRequest.postJsonObjectCustomGson(url, jsonData, ResultResponse.class, this);

    }

    @Override
    protected void onDestroy() {
        databaseHandler.closeDatabase();
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
