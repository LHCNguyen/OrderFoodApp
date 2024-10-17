package com.example.orderfoodapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DangKyActivity extends AppCompatActivity {

    private EditText edtTenDN, edtMatKhau, edtNgaySinh, edtCCCD, edtXacNhanMatKhau;
    private Button btnDongY, btnThoat;
    private RadioButton radGioiTinhNam, radGioiTinhNu;
    private RadioGroup radGroupGioiTinh;
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
        edtXacNhanMatKhau = findViewById(R.id.edtXacNhanMatKhau);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtCCCD = findViewById(R.id.edtCCCD);
        radGroupGioiTinh = findViewById(R.id.radGroupGioiTinh);
        radGioiTinhNam = findViewById(R.id.radGioiTinhNam);
        radGioiTinhNu = findViewById(R.id.radGioiTinhNu);
        btnDongY = findViewById(R.id.btnDongY);
        btnThoat = findViewById(R.id.btnThoat);

        //Thêm kí tự "/" khi người dùng nhập ngày sinh. Điều này giúp người dùng không cần nhan dấu "/"
        edtNgaySinh.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "ddmmyyyy";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)){
                    String clean = s.toString().replaceAll("[^\\d]","");
                    StringBuilder formatted = new StringBuilder();
                    if (clean.length() > 0) {
                        formatted.append(clean.substring(0, Math.min(2, clean.length()))); // ngày
                    }
                    if (clean.length() >= 3) {
                        formatted.append("/").append(clean.substring(2, Math.min(4, clean.length()))); // tháng
                    }
                    if (clean.length() >= 5) {
                        formatted.append("/").append(clean.substring(4, Math.min(clean.length(), 8))); // năm
                    }

                    current = formatted.toString();
                    edtNgaySinh.setText(current);
                    edtNgaySinh.setSelection(current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDN = edtTenDN.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String xacNhanMatKhau = edtXacNhanMatKhau.getText().toString();
                String ngaySinh = edtNgaySinh.getText().toString();
                String cccd = edtCCCD.getText().toString();
                String gioiTinh = radGioiTinhNam.isChecked() ?"Nam" : "Nữ";

                //Kiểm tra xem nếu thiếu thông tin sẽ không được tiếp tục
                if (tenDN.isEmpty() || matKhau.isEmpty() || ngaySinh.isEmpty() || cccd.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                //Kiểm tra độ dài tên đăng nhập có từ 6 cho tới 20 ký tự khong
                else if (tenDN.length() < 6 || tenDN.length() > 20) {
                    Toast.makeText(DangKyActivity.this, "Tên đăng nhập phải từ 6 đến 20 ký tự", Toast.LENGTH_SHORT).show();
                }
                //Tên đăng nhập không được co khoảng trắng
                else if (tenDN.contains(" ")) {
                    Toast.makeText(DangKyActivity.this, "Tên đăng nhập không được có khoảng cách", Toast.LENGTH_SHORT).show();
                }
                //Kiểm tra xem mật khẩu có dưới 8 ky tự không
                else if (matKhau.length() < 8) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu phải có ít nhất 8 ký tự ", Toast.LENGTH_SHORT).show();
                }
                else if (matKhau.contains(" ")) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không được có khoảng cách", Toast.LENGTH_SHORT).show();
                }
                //Kiem tra xem nhap lại mật khẩu có chính xac khong
                else if (!matKhau.equals(xacNhanMatKhau)) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
                //Kiểm tra nhập lại mật khẩu có khoảng trắng không
                else if (xacNhanMatKhau.contains(" ")) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu xác nhận không được có khoảng cách", Toast.LENGTH_SHORT).show();
                }
                //Kiểm tra xem co chọn giới tính chưa
                else if (radGroupGioiTinh.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
                } else if (cccd.length() != 12 || cccd.matches("\\d+")) {
                    Toast.makeText(DangKyActivity.this, "CCCD phải là số và có đúng 12 ký tự", Toast.LENGTH_SHORT).show();
                }
                //Kiểm tra xem ngày sinh có đúng định dạng không
                else if (!isValidDate(ngaySinh)) {
                    Toast.makeText(DangKyActivity.this, "Ngày sinh không đúng định dạng (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
                }
                // Tất cả hợp lệ thì lưu vao database
                else {
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

    //Hàm kểm tra định dạng ngày sinh
    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // không cho phép các ngày không hợp lệ
        try {
            Date parsedDate = sdf.parse(date);
            return parsedDate != null;
        } catch (ParseException e) {
            return false;
        }
    }

}