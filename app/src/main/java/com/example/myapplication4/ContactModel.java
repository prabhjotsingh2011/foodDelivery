package com.example.myapplication4;

public class ContactModel {
    int img;
    String name,number;

    public ContactModel(int img,String name,String number){
        this.name=name;
        this.img=img;
        this.number=number;
    }

    public ContactModel(String name,String number){
        this.name=name;
        this.number=number;
    }

}
