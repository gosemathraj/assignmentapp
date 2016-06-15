package com.gosemathraj.paritycubeapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gosemathraj.paritycubeapp.R;
import com.gosemathraj.paritycubeapp.adapters.RecyclerviewAdapter;
import com.gosemathraj.paritycubeapp.database.Dbhelper;
import com.gosemathraj.paritycubeapp.model.Deals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iamsparsh on 14/6/16.
 */
public class Popular extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Deals> dealsList = new ArrayList<>();
    private RecyclerviewAdapter recyclerviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_home,container,false);


        getData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private void getData() {

        String URL = "http://139.162.46.29/v1/deals/popular.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i = 0;i < jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Deals deals = new Deals();
                                deals.setId(jsonObject.getString("id"));
                                deals.setTitle(jsonObject.getString("title"));
                                deals.setCurrent_price(jsonObject.getString("current_price"));
                                deals.setOriginal_price(jsonObject.getString("original_price"));
                                deals.setOff_percent(jsonObject.getString("off_percent"));

                                deals.setLike_count(jsonObject.getString("popularity_count"));
                                deals.setComments_count(jsonObject.getString("comments_count"));
                                dealsList.add(deals);
                            }

                            recyclerView.setAdapter(new RecyclerviewAdapter(getContext(), dealsList));
                            Dbhelper dbhelper = new Dbhelper(getContext());
                            dbhelper.addDeals(dealsList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        String s = error.toString();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("X-Desidime-Client", "0c50c23d1ac0ec18eedee20ea0cdce91ea68a20e9503b2ad77f44dab982034b0");
                return params;
            };
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
