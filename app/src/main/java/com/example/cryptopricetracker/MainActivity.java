package com.example.cryptopricetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText search;
    private RecyclerView recyclerview;
    private ProgressBar loadingpb;
    private ArrayList<CurrencyRvModel>currencyRvModelArrayList;
    private CurrencyRVAdapter currencyRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.idEdtSearch);
        recyclerview = findViewById(R.id.idRVcurrencies);
        loadingpb = findViewById(R.id.idPBloading);
        currencyRvModelArrayList =new ArrayList<>();
        currencyRVAdapter =new CurrencyRVAdapter(currencyRvModelArrayList,this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(currencyRVAdapter);
        getCurrencyData();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            filterCurrencies(s.toString());
            }
        });
    }
    private void filterCurrencies (String currency){
        ArrayList<CurrencyRvModel> filteredList =new ArrayList<>();
        for (CurrencyRvModel item : currencyRvModelArrayList){
            if (item.getName().toLowerCase().contains(currency.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"NO CURRENCY FOUND",Toast.LENGTH_SHORT).show();
        }else{
                currencyRVAdapter.filterList(filteredList);
        }
    }
    private void getCurrencyData(){
        loadingpb.setVisibility(View.VISIBLE);
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            loadingpb.setVisibility(View.GONE);
            try{
                JSONArray dataArray = response.getJSONArray("data");
                for(int i=0 ;i<dataArray.length();i++){
                    JSONObject dataObj = dataArray.getJSONObject(i);
                    String name = dataObj.getString("name");
                    String symbol = dataObj.getString("symbol");
                    JSONObject quote =dataObj.getJSONObject("quote");
                    JSONObject USD = quote.getJSONObject("USD");
                    double price =USD.getDouble("price");
                    currencyRvModelArrayList.add(new CurrencyRvModel(name,symbol,price));
                }
                currencyRVAdapter.notifyDataSetChanged();
            }catch (JSONException e ){
                  e.printStackTrace();
                  Toast.makeText(MainActivity.this, "Failed To Json Data",Toast.LENGTH_SHORT).show();
            }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingpb.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"FAIL TO LOAD DATA",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers =new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY","f8febc17-19c5-48e0-860f-8f07e0031486");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void newsbutton(View view) {
        Intent intent =new Intent(this,NewsActivity.class);
        startActivity(intent);
    }
}