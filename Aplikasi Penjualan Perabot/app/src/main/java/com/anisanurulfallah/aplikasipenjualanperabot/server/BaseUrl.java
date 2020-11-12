package com.anisanurulfallah.aplikasipenjualanperabot.server;

public class BaseUrl {

    public static String baseUrl ="http://172.20.10.2:7000/";

    public static String login    = baseUrl +"user/login";
    public static String registrasi = baseUrl + "user/registrasi";

    //Perabot
    public static String dataPerabot = baseUrl + "perabot/dataperabot";
    public static String editDataPerabot = baseUrl + "editdataperabot/ubah/";
    public static String hapusData = baseUrl + "hapusdata/hapus/";
    public static String inputData = baseUrl + "inputdata/input/";
    }
