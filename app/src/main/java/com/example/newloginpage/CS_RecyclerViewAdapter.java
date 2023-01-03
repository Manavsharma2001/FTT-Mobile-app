package com.example.newloginpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CS_RecyclerViewAdapter extends RecyclerView.Adapter<CS_RecyclerViewAdapter.MyViewHolder>  {
    private final RecyclerViewInterface recyclerViewInterface;


    Context context;
    ArrayList<CryptoStockModel> cryptoStockModels;

    public CS_RecyclerViewAdapter(Context context, ArrayList<CryptoStockModel> cryptoStockModels, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.cryptoStockModels = cryptoStockModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CS_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new CS_RecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CS_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(cryptoStockModels.get(position).getImage());
    holder.tvFullName.setText(cryptoStockModels.get(position).getStockNotCryptoAbb());
    holder.tvPosNeg.setText(cryptoStockModels.get(position).getStockCryptoPosNeg());
    holder.tvCurrPrice.setText(cryptoStockModels.get(position).getStockCryptoCurrentPrice());
    holder.tvAbb.setText(cryptoStockModels.get(position).getStockCryptoAbb());

    }

    @Override
    public int getItemCount() {

        return cryptoStockModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvFullName;
        TextView tvAbb;
        TextView tvCurrPrice;
        TextView tvPosNeg;

        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvAbb = itemView.findViewById(R.id.textViewAbb);
            tvFullName = itemView.findViewById(R.id.fullname);
            tvCurrPrice = itemView.findViewById(R.id.currentPrice);
            tvAbb = itemView.findViewById(R.id.textViewAbb);
            tvPosNeg = itemView.findViewById(R.id.textViewPosNeg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){

                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });


        }
    }
}
