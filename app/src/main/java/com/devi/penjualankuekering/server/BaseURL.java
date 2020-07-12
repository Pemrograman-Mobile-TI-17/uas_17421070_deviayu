package com.devi.penjualankuekering.server;

public class BaseURL {

    public static String baseUrl = "http://192.168.100.24:3030/";

    public static String login      = baseUrl + "user/login";
    public static String register   = baseUrl + "user/registrasi";

    //buku
    public static String dataKue = baseUrl + "kue/datakue";
    public static String editDataKue = baseUrl + "kue/ubah/";
    public static String hapus = baseUrl + "kue/hapus/";
    public static String inputKue = baseUrl + "kue/input";

}
