package com.example.newloginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements  RecyclerViewInterface {

    ArrayList<CryptoStockModel> cryptoStockModels = new ArrayList<>();

    int[] cryptoStockImages  = {R.drawable.btc_svgrepo_com,
            R.drawable.eth_svgrepo_com,
            R.drawable.nvidia_logo_svgrepo_com,
            R.drawable.amd_logo_1,
            R.drawable.apple_13,
            R.drawable.microsoft,
            R.drawable.adobe_svgrepo_com,






    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setCryptoStockModels();

        CS_RecyclerViewAdapter adapter = new CS_RecyclerViewAdapter(this,cryptoStockModels,(RecyclerViewInterface) this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    private void setCryptoStockModels(){
        String[] cryptoStockAbb = getResources().getStringArray(R.array.crypto_stock_abb);
        String[] cryptoStockNotAbb = getResources().getStringArray(R.array.crypto_stock_not_abb);
        String[] cryptoStockCurrentPrice = getResources().getStringArray(R.array.current_price);
        String[] cryptoStockPosNeg = getResources().getStringArray(R.array.pos_or_neg_price);


        for (int i =0; i < cryptoStockAbb.length; i++){
            cryptoStockModels.add(new CryptoStockModel(cryptoStockAbb[i],cryptoStockNotAbb[i],cryptoStockPosNeg[i],cryptoStockCurrentPrice[i],cryptoStockImages[i]));
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(FirstActivity.this,RecyclerNext.class);

        intent.putExtra("abb",cryptoStockModels.get(position).getStockCryptoAbb());


        startActivity(intent);
    }

    @Override
    public void onItemClick1(int position) {

    }

    @Override
    public void onItemClick2(int position) {

    }

}