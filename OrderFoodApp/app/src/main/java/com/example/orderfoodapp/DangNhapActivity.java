package com.example.orderfoodapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoodapp.Database.CreateDatabase;

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

        btnDongYDN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tenDangNhap = edtTaiKhoan.getText().toString();
                String matKhau = editMatKhauDN.getText().toString();

                CreateDatabase db = new CreateDatabase(getApplicationContext());
                if (db.kiemTraDangNhap(tenDangNhap, matKhau)) {
                    // Đăng nhập thành công
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(DangNhapActivity.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
