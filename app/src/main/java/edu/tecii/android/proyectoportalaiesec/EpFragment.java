package edu.tecii.android.proyectoportalaiesec;


import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class EpFragment extends Fragment {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lvEps;

    ArrayList<HashMap<String, String>> eps;

    public EpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ep, container, false);

        eps=new ArrayList<>();
        lvEps=(ListView) v.findViewById(R.id.ep_list);
        
        new GetRecords().execute();
        return v;
    }

    private class GetRecords extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = "https://gis-api.aiesec.org:443/v2/people.json?access_token=39649efec3d81a3f7286d6d1f8321b74f565b9ac1628a8fa85112665a69b78d7";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject epps= new JSONObject(jsonStr);
                    //JSONArray epps=jsonObj.getJSONObject(0);
                    JSONArray eppo = epps.getJSONArray("data");
                    Log.e(TAG, "Hla: " + eppo);
                    for (int i = 0; i < eppo.length(); i++) {
                        JSONObject c = eppo.getJSONObject(i);
                        String id = c.getString("id");
                        String ep_mail = c.getString("email");
                        String ep_url = c.getString("url");
                        String first_name = c.getString("first_name");
                        String full_name = c.getString("full_name");
                        String last_name = c.getString("last_name");
                        String img = c.getString("profile_photo_url");
                        //int group_id= c.getInt("group_id");
                        //String organisation_id= c.getString("profile_photo_urls");
                        //String cover_photo_urls=c.getString("cover_photo_urls");


                        HashMap<String, String> opp = new HashMap<>();

                        opp.put("id", id);
                        opp.put("email", ep_mail);
                        opp.put("full_name", full_name);
                        opp.put("img", img);

                        eps.add(opp);

                    }


                } catch (JSONException e) {
                    Log.e(TAG, "Something is wrong");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "AUIDAAAAAAAA");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(getContext(), eps,
                    R.layout.ep_list, new String[]{ "full_name","email"},
                    new int[]{R.id.ep_name, R.id.ep_mail});
            lvEps.setAdapter(adapter);

        }

    }
}
