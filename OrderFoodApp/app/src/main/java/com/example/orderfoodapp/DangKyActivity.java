package com.example.orderfoodapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.orderfoodapp.Database.CreateDatabase;

public class DangKyActivity extends AppCompatActivity {

    private EditText edtTenDN, edtMatKhau, edtNgaySinh, edtCCCD;
    private Button btnDongY, btnThoat;
    private RadioButton radGioiTinhNam, radGioiTinhNu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_dangky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CreateDatabase createDatabase = new CreateDatabase(this);
        createDatabase.open();

        //liên kết các view bên layout
        edtTenDN = findViewById(R.id.edtTenDN);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtCCCD = findViewById(R.id.edtCCCD);
        radGioiTinhNam = findViewById(R.id.radGioiTinhNam);
        radGioiTinhNu = findViewById(R.id.radGioiTinhNu);
        btnDongY = findViewById(R.id.btnDongY);
        btnThoat = findViewById(R.id.btnThoat);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDN = edtTenDN.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String ngaySinh = edtNgaySinh.getText().toString();
                String cccd = edtCCCD.getText().toString();
                String gioiTinh = radGioiTinhNam.isChecked() ?"Nam" : "Nữ";
                if (tenDN.isEmpty() || matKhau.isEmpty() || ngaySinh.isEmpty() || cccd.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Tạo đối tượng ContentValues để lưu dữ liệu
                    ContentValues values = new ContentValues();
                    values.put(CreateDatabase.TB_NHANVIEN_TENDN, tenDN);
                    values.put(CreateDatabase.TB_NHANVIEN_MATKHAU, matKhau);
                    values.put(CreateDatabase.TB_NHANVIEN_GIOITINH, gioiTinh);
                    values.put(CreateDatabase.TB_NHANVIEN_NGAYSINH, ngaySinh);
                    values.put(CreateDatabase.TB_NHANVIEN_CCCD, cccd);

                    // Thêm dữ liệu vào bảng NHANVIEN
                    long result = createDatabase.insertNhanVien(tenDN, matKhau, gioiTinh, ngaySinh, cccd);
                    if (result != -1) {
                        Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DangKyActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}