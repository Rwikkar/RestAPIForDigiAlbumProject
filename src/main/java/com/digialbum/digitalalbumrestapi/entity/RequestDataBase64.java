package com.digialbum.digitalalbumrestapi.entity;


import java.sql.Timestamp;

public class RequestDataBase64 {
    private int ID;
    private Timestamp CRT_TS;
    private Timestamp UPD_TS;
    private char STATUS;
    private String raw_dataBase64;
    private String file_name;
    private String filetype;
    private String description;
}
