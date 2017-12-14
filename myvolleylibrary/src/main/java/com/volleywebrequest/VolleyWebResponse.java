package com.volleywebrequest;

import com.android.volley.VolleyError;

/**
 * Created by yasar on 4/10/17.
 */

public interface VolleyWebResponse {

    void onResponse(Object response);

    void onErrorResponse(VolleyError error);

}
