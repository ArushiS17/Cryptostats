package com.example.cryptopricetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.ViewHolder> {
    private ArrayList<CurrencyRvModel> currencyRvModelArrayList;
    private Context context;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public CurrencyRVAdapter(ArrayList<CurrencyRvModel> currencyRvModelArrayList, Context context) {
        this.currencyRvModelArrayList = currencyRvModelArrayList;
        this.context = context;
    }
    public  void filterList(ArrayList<CurrencyRvModel>filteredList){
        currencyRvModelArrayList=filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CurrencyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currencyrvitem,parent,false);
        return new CurrencyRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVAdapter.ViewHolder holder, int position) {
    CurrencyRvModel currencyRvModel=currencyRvModelArrayList.get(position);
    holder.currencyNameTv.setText(currencyRvModel.getName());
        holder.symbolTv.setText(currencyRvModel.getSybmol());
        holder.rateTv.setText("$" + df2.format(currencyRvModel.getPrice()));
    }

    @Override
    public int getItemCount() {
        return currencyRvModelArrayList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyNameTv,symbolTv,rateTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyNameTv=itemView.findViewById(R.id.idTvCurrencyName);
            symbolTv=itemView.findViewById(R.id.idTvSymbol);
            rateTv=itemView.findViewById(R.id.idTvCurrencyRate);
        }
    }
}
