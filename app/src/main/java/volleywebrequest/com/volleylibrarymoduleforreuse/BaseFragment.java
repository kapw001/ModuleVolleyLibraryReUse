package volleywebrequest.com.volleylibrarymoduleforreuse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.mysessionmanagementlibrary.SessionManager;
import com.volleywebrequest.MakeRestRequest;
import com.volleywebrequest.VolleyWebRequest;
import com.volleywebrequest.VolleyWebResponse;

/**
 * Created by yasar on 5/10/17.
 */

public abstract class BaseFragment extends Fragment implements VolleyWebResponse {
    String url = "https://reqres.in/api/login";
    //    String url = "https://amspreprod.pappaya.co.uk/vms/device/get_visitor_data";
    public MakeRestRequest volleyWebRequest;
    public SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyWebRequest = VolleyWebRequest.getInstance(getActivity());
        sessionManager = SessionManager.getInstance(getActivity());
    }
}
