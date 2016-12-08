package edu.tecii.android.proyectoportalaiesec;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 08/12/2016.
 */

public class ProfilePostAdapter extends ArrayAdapter{
    private String url = "https://gis-api.aiesec.org:443/v2/people.json?access_token=611c4b0c587fdf2a3614ad64da6bc7337a5f90c12416535856316629b53e68cc";
    private static final String TAG = "PostAdapter";
    List<ProfilePost> data;
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;

    public ProfilePostAdapter(Context context){
        super(context, 0);
        requestQueue = Volley.newRequestQueue(context);

        jsArrayRequest = new JsonObjectRequest(Request.Method.GET, url,
                (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        data = parseJson(response);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );

        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View listItemView;

        listItemView = null == convertView ? layoutInflater.inflate(R.layout.pr_list, parent, false) : convertView;
        //Log.d(TAG, "VER QUE PASA: "+listItemView.);
        ProfilePost item = data.get(position);

        TextView pr_name = (TextView) listItemView.findViewById(R.id.pr_name);
        //Log.d(TAG, "getView: "+pr_name);
        TextView pr_mail = (TextView) listItemView.findViewById(R.id.pr_mail);
        final ImageView pr_image = (ImageView) listItemView.findViewById(R.id.pr_image);

        pr_name.setText(item.getFull_name());
        pr_mail.setText(item.getEmail());

        ImageRequest request = new ImageRequest(
                item.getImagen(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        pr_image.setImageBitmap(response);
                    }
                }, 0, 0, null, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error en respuesta Bitmap: " + error.getMessage());
                    }
                });
        requestQueue.add(request);
        return listItemView;

    }

    public List<ProfilePost> parseJson(JSONObject jsonObject){
        List<ProfilePost> posts = new ArrayList<>();
        JSONArray jsonArray = null;

        try {
            jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i<jsonArray.length(); i++){
                try{
                    JSONObject object = jsonArray.getJSONObject(i);

                    ProfilePost post = new ProfilePost(
                            object.getString("id"),
                            object.getString("email"),
                            object.getString("full_name"),
                            object.getString("profile_photo_url")
                    );

                    posts.add(post);
                }catch (JSONException e){
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }

}
