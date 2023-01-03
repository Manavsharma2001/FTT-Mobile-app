package com.example.newloginpage;

public class CryptoStockModel {
    String stockCryptoAbb;
    String stockNotCryptoAbb;
    String stockCryptoPosNeg;
    String stockCryptoCurrentPrice;
    int image;

    public String getStockCryptoAbb() {
        return stockCryptoAbb;
    }

    public String getStockNotCryptoAbb() {
        return stockNotCryptoAbb;
    }

    public String getStockCryptoPosNeg() {
        return stockCryptoPosNeg;
    }

    public String getStockCryptoCurrentPrice() {
        return stockCryptoCurrentPrice;
    }

    public int getImage() {
        return image;
    }

    public CryptoStockModel(String stockCryptoAbb, String stockNotCryptoAbb, String stockCryptoPosNeg, String stockCryptoCurrentPrice, int image) {
        this.stockCryptoAbb = stockCryptoAbb;
        this.stockNotCryptoAbb = stockNotCryptoAbb;
        this.stockCryptoPosNeg = stockCryptoPosNeg;
        this.stockCryptoCurrentPrice = stockCryptoCurrentPrice;
        this.image = image;
    }
}
