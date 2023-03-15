package com.tqt.WebBasic;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

public class test {
    public static void main(String[] args) {
        //Kiểm tra độ mạnh của password thang điểm 0 ---> 4
        String password = "";
        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(password);
        System.out.println("Password strength score: " + strength.getScore());
    }
}