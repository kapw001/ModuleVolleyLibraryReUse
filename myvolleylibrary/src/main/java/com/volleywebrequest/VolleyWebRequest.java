package com.volleywebrequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 4/10/17.
 */

public class VolleyWebRequest implements MakeRestRequest {
    private final Gson gson = new Gson();
    private static final String TAG = "VolleyWebRequest";
    private int timeout = 60000; // 60 seconds - time out
    private Context context;
    private VolleyController volleyController;
    private static VolleyWebRequest webPostRequest;

    private VolleyWebRequest(Context context) {
        this.context = context;
        this.volleyController = VolleyController.getInstance(context);
    }

    public static synchronized VolleyWebRequest getInstance(Context context) {
        if (webPostRequest == null) {
            webPostRequest = new VolleyWebRequest(context);
        }

        return webPostRequest;
    }


    @Override
    public void postData(String url, int method, final Map<String, String> params, final VolleyWebResponse volleyWebResponse) {
        // Tag used to cancel the request
        String tag_string_req = "string_req";
        Log.e(TAG, "postJsonObject: " + new JSONObject(params).toString());
        StringRequest strReq = new StringRequest(method,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                volleyWebResponse.onResponse(response);


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                volleyWebResponse.onErrorResponse(error);

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
//                headers.put("Authorization", basicAuth);
//                headers.put("Accept-Encoding", "");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

//        // Request Time out
//        strReq.setRetryPolicy(new DefaultRetryPolicy(timeout,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        strReq.setRetryPolicy(new DefaultRetryPolicy(timeout,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        System.setProperty("http.keepAlive", "false");


// Adding request to request queue
        volleyController.addToRequestQueue(strReq, tag_string_req);
    }

    @Override
    public void postJsonObject(String url, final Map<String, Object> params, final VolleyWebResponse volleyWebResponse) {

        Log.e(TAG, "postJsonObject: " + new JSONObject(params).toString());
        try {
            Log.e(TAG, "postJsonObject  " + new JSONObject(params).toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        volleyWebResponse.onResponse(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                volleyWebResponse.onErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json");
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };

// add the request object to the queue to be executed
        volleyController.addToRequestQueue(req);
    }


    @Override
    public <T> void postJsonObjectCustomGson(String url, final Map<String, Object> params,Class<T> clzz, final VolleyWebResponse volleyWebResponse) {

        Log.e(TAG, "postJsonObject: " + new JSONObject(params).toString());
        try {
            Log.e(TAG, "postJsonObject  " + new JSONObject(params).toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequestGson req = new JsonObjectRequestGson(url, new JSONObject(params), clzz,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        volleyWebResponse.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                volleyWebResponse.onErrorResponse(error);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json");
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };

// add the request object to the queue to be executed
        volleyController.addToRequestQueue(req);
    }

}
