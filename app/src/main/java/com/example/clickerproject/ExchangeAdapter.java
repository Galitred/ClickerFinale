package com.example.clickerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder> {
   private final LayoutInflater inflater;
   private final List<ExchangeStat> exchangeStats;

   TextView mCurrentBalance;
    /*public ExchangeAdapter(LayoutInflater inflater, List<ExchangeAdapter> exchangeAdapters) {
        this.inflater = inflater;
        this.exchangeAdapters = exchangeAdapters;
    }*/

    public ExchangeAdapter(Context context, List<ExchangeStat> exchangeStats){
        this.exchangeStats = exchangeStats;
        this.inflater = LayoutInflater.from(context);
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exchange_element, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExchangeStat exchangeStat = exchangeStats.get(position);
        holder.exchangeImage.setImageResource(exchangeStat.getImgLink());
        holder.name.setText(exchangeStat.getName());
        holder.price.setText(String.format("%.2f", exchangeStat.getFloatPrice()));
        holder.exButton.setId(exchangeStat.getId());
        holder.exchangePrice.setText(String.format("%.2f", exchangeStat.getFloatExchangePrice()));
        holder.exButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.codeLines >= exchangeStat.getFloatPrice()){
                    MainActivity.codeLines -= exchangeStat.getFloatPrice();
                    MainActivity.coinBalance += exchangeStat.getFloatExchangePrice();
                    ExchangeActivity.mCurrentBalance.setText(String.format("Current balance: " + "%.2f", MainActivity.codeLines));

                } else {
                    Toast.makeText(v.getContext(), "Недостаточно строк кода!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return exchangeStats.size();
    }






    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView price, name, exButton, exchangePrice;
        private final ImageView exchangeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.exPrice);
            name = itemView.findViewById(R.id.name);
            exButton = itemView.findViewById(R.id.exButton);
            exchangeImage = itemView.findViewById(R.id.exchangeImage);
            exchangePrice = itemView.findViewById(R.id.exChangePrice);
        }

    }
}


