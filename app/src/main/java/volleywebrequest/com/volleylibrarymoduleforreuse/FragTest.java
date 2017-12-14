package volleywebrequest.com.volleylibrarymoduleforreuse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.mysessionmanagementlibrary.SessionManager;
import com.volleywebrequest.Utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by yasar on 5/10/17.
 */

public class FragTest extends BaseFragment {


    private static final String TAG = "FragTest";
    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager.setUrl("https://reqres.in/api/register");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();

            }
        });
    }

    private void makeRequest() {

        if (sessionManager.isLoggedIn()) {
            Map<String, String> map = sessionManager.getDetails();
            url = sessionManager.getKeyUrl();
            Log.e(TAG, "makeRequest: url  " + url);

            if (url != null) {
                Utils.showProgress(getActivity(), "Loading");
                volleyWebRequest.postData(url, Request.Method.POST, map, this);
            } else {
                Log.e(TAG, "makeRequest: There is no url set ");
            }


        } else {
            Log.e(TAG, "makeRequest: There is no details saved ");
        }


    }

    @Override
    public void onResponse(Object response) {
        Utils.hideProgress();
        Log.e(TAG, "onResponse:  " + response);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Utils.hideProgress();
        String body;
        //get status code here
//        String statusCode = String.valueOf(error.networkResponse.statusCode);
        //get response body and parse with appropriate encoding
        if (error.networkResponse.data != null) {
            try {
                body = new String(error.networkResponse.data, "UTF-8");
                Log.e(TAG, "onErrorResponse: body  " + body);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
