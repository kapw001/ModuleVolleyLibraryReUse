package volleywebrequest.com.volleylibrarymoduleforreuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.volleywebrequest.Utils;
import com.volleywebrequest.VolleyWebRequest;
import com.volleywebrequest.VolleyWebResponse;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import volleywebrequest.com.mysqlitelibrary.Contact;
import volleywebrequest.com.volleylibrarymoduleforreuse.models.ResultResponse;
import volleywebrequest.com.volleylibrarymoduleforreuse.models.VisitorList;

public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";

    private EditText name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);



        Random random = new Random();
        List<Contact> contactList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact();

            contact.setName("Test" + i);

//            int n = random.nextInt(20000);

            contact.setNumber(6 + i + System.currentTimeMillis());

            contactList.add(contact);
        }

        databaseHandler.addData(contactList);

    }

    public void onRequest(View view) {

        Utils.showProgress(MainActivity.this, "Loading");
//        makeRequest();

//        Map<String, String> params = new HashMap<String, String>();
//        params.put("email", "abc@androidhive.info");
//        params.put("password", "aaa");
//        volleyWebRequest.postData(url, Request.Method.POST, params, this);
        if (sessionManager.isLoggedIn()) {
            Map<String, String> map = sessionManager.getDetails();
//            volleyWebRequest.postData(url, Request.Method.POST, map, this);
            makeRequest();
        } else {
            Log.e(TAG, "makeRequest: There is no details saved ");
        }


    }

    @Override
    public void onResponse(Object response) {
        Utils.hideProgress();
        Log.e(TAG, "onResponse:  " + response);

        ResultResponse resultResponse = (ResultResponse) response;

        Log.e(TAG, "onResponse: " + resultResponse.toString());


        List<VisitorList> list = resultResponse.getResult().getVisitorList();

        for (VisitorList visitorList : list
                ) {

            Log.e(TAG, "onResponse: Data  " + visitorList.getId());
        }


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

    public void onSave(View view) {

        String n = name.getText().toString();
        String p = password.getText().toString();

        if (sessionManager.isLoggedIn()) {
            onRequest(view);
        } else {
            sessionManager.createSession(n, p);
        }

    }

    public void onClear(View view) {
        if (sessionManager.isLoggedIn()) {
            sessionManager.claerData();
        } else {
            Log.e(TAG, "onClear: no data ");
        }
    }

    public void onAdd(View view) {
        Random random = new Random();
        List<Contact> contactList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Contact contact = new Contact();

            contact.setName("Test" + i);

//            int n = random.nextInt(20000);

            contact.setNumber(6 + i + System.currentTimeMillis());

            contactList.add(contact);
        }

        databaseHandler.addData(contactList);

    }

    public void onFetch(View view) {

        List<Contact> contactList = databaseHandler.getAllDatas();

        Log.e(TAG, "onFetch: count " + databaseHandler.getDataCount());

        for (Contact contact : contactList
                ) {
//            Log.e(TAG, "onFetch: name  :  " + contact.getName() + "  number  : " + contact.getNumber());
        }
    }
}
