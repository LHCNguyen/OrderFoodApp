package com.example.orderfoodapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {

    private Button btnDongYDN, btnDangKyDN;
    private EditText edtTaiKhoan, editMatKhauDN;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDongYDN = findViewById(R.id.btnDongYDN);
        btnDangKyDN = findViewById(R.id.btnDangKyDN);
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        editMatKhauDN = findViewById(R.id.edtMatKhauDN);

        btnDangKyDN.setVisibility(View.GONE);

    }
}
