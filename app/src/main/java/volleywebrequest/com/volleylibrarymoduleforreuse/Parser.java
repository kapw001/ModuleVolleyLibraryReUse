package volleywebrequest.com.volleylibrarymoduleforreuse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import volleywebrequest.com.volleylibrarymoduleforreuse.models.ResultResponse;

/**
 * Created by yasar on 5/10/17.
 */

public class Parser {

    public static ResultResponse getResponse(String response) {
        Gson gson = new GsonBuilder().create();
        ResultResponse resultResponse = gson.fromJson(response, ResultResponse.class);
        return resultResponse;
    }
}
