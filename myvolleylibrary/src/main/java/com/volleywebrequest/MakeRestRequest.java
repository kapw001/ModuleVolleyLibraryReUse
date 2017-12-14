package com.volleywebrequest;

import java.util.Map;

/**
 * Created by yasar on 5/10/17.
 */

public interface MakeRestRequest {

    void postData(String url, int method, final Map<String, String> params, final VolleyWebResponse volleyWebResponse);

    void postJsonObject(String url, final Map<String, Object> params, final VolleyWebResponse volleyWebResponse);

    <T> void postJsonObjectCustomGson(String url, final Map<String, Object> params, Class<T> clazz, final VolleyWebResponse volleyWebResponse);
}
