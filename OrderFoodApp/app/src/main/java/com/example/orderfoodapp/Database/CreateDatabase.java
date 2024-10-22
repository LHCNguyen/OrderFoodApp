package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_NHANVIEN = "NHANVIEN";
    public static String TB_MONAN = "MONAN";
    public static String TB_LOAIMONAN = "LOAIMONAN";
    public static String TB_BANAN = "BANAN";
    public static String TB_GOIMON = "GOIMON";
    public static String TB_CHITIETGOIMON = "CHITIETGOIMON";

    public static String TB_NHANVIEN_MANV = "MANV";
    public static String TB_NHANVIEN_TENDN = "TENDN";
    public static String TB_NHANVIEN_MATKHAU = "MATKHAU";
    public static String TB_NHANVIEN_GIOITINH = "GIOITINH";
    public static String TB_NHANVIEN_NGAYSINH = "NGAYSINH";
    public static String TB_NHANVIEN_EMAIL = "EMAIL";

    public static String TB_MONAN_MAMONAN = "MAMONAN";
    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";

    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";

    public static String TB_BANAN_MABAN = "MABAN";
    public static String TB_BANAN_TENBAN = "TENBAN";
    public static String TB_BANAN_TINHTRANG = "TINHTRANG";

    public static String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_GOIMON_MANV = "MANV";
    public static String TB_GOIMON_NGAYGOI = "NGAYGOI";
    public static String TB_GOIMON_TINHTRANG = "TINHTRANG";
    public static String TB_GOIMON_MABAN = "MABAN";

    public static String TB_CHITIETGOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_CHITIETGOIMON_MAMONAN = "MAMONAN";
    public static String TB_CHITIETGOIMON_SOLUONG = "SOLUONG";

    public CreateDatabase(Context context) {
        super(context, "OrderFoodApp", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNHANVIEN = "CREATE TABLE " + TB_NHANVIEN + " ( " + TB_NHANVIEN_MANV + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NHANVIEN_TENDN + " TEXT, " + TB_NHANVIEN_MATKHAU + " TEXT, " + TB_NHANVIEN_GIOITINH + " TEXT, "
                + TB_NHANVIEN_NGAYSINH + " TEXT, " + TB_NHANVIEN_EMAIL + " TEXT )";

        String tbBANAN = "CREATE TABLE " + TB_BANAN + " ( " + TB_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BANAN_TENBAN + " TEXT, " + TB_BANAN_TINHTRANG + " TEXT )";

        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( " + TB_MONAN_MAMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT, " + TB_MONAN_MALOAI + " INTEGER, " + TB_MONAN_GIATIEN + " TEXT )";

        String tbLOAIMONAN = "CREATE TABLE " + TB_LOAIMONAN + " ( " + TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_LOAIMONAN_TENLOAI + " TEXT )";

        String tbGOIMON = "CREATE TABLE " + TB_GOIMON + " ( " + TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABAN + " INTEGER, " + TB_GOIMON_MANV + " INTEGER, " + TB_GOIMON_NGAYGOI + " TEXT, "
                + TB_GOIMON_TINHTRANG + "TEXT )";

        String tbCHITETGOIMON = "CREATE TABLE " + TB_CHITIETGOIMON + " ( " + TB_CHITIETGOIMON_MAGOIMON + " INTEGER, "
                + TB_CHITIETGOIMON_MAMONAN + " INTEGER, " + TB_CHITIETGOIMON_SOLUONG + " INTEGER, "
                + "PRIMARY KEY ( " + TB_CHITIETGOIMON_MAMONAN + "," + TB_CHITIETGOIMON_MAGOIMON + "))";

        db.execSQL(tbNHANVIEN);
        db.execSQL(tbBANAN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbGOIMON);
        db.execSQL(tbCHITETGOIMON);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < newVersion) {
            // Tạo bảng tạm để lưu dữ liệu
            db.execSQL("ALTER TABLE " + TB_NHANVIEN + " RENAME TO NHANVIEN_TEMP");

            // Tạo bảng mới với cấu trúc mới (thay CCCD bằng EMAIL)
            String tbNHANVIEN = "CREATE TABLE " + TB_NHANVIEN + " ( "
                    + TB_NHANVIEN_MANV + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TB_NHANVIEN_TENDN + " TEXT, "
                    + TB_NHANVIEN_MATKHAU + " TEXT, "
                    + TB_NHANVIEN_GIOITINH + " TEXT, "
                    + TB_NHANVIEN_NGAYSINH + " TEXT, "
                    + TB_NHANVIEN_EMAIL + " TEXT )";
            db.execSQL(tbNHANVIEN);

            // Sao chép dữ liệu từ bảng tạm sang bảng mới (thay thế cột CCCD bằng giá trị NULL cho EMAIL)
            db.execSQL("INSERT INTO " + TB_NHANVIEN + " ("
                    + TB_NHANVIEN_MANV + ", "
                    + TB_NHANVIEN_TENDN + ", "
                    + TB_NHANVIEN_MATKHAU + ", "
                    + TB_NHANVIEN_GIOITINH + ", "
                    + TB_NHANVIEN_NGAYSINH + ", "
                    + TB_NHANVIEN_EMAIL + ") SELECT "
                    + TB_NHANVIEN_MANV + ", "
                    + TB_NHANVIEN_TENDN + ", "
                    + TB_NHANVIEN_MATKHAU + ", "
                    + TB_NHANVIEN_GIOITINH + ", "
                    + TB_NHANVIEN_NGAYSINH + ", NULL FROM NHANVIEN_TEMP");

            // Xóa bảng tạm
            db.execSQL("DROP TABLE NHANVIEN_TEMP");
        }

    }

    //Hàm thêm nhân viên
    public long insertNhanVien(String tenDN, String matKhau, String gioiTinh, String ngaySinh, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String matKhauDaBam = bamMatKhau(matKhau);
        
        values.put(TB_NHANVIEN_TENDN, tenDN);
        values.put(TB_NHANVIEN_MATKHAU, matKhauDaBam);
        values.put(TB_NHANVIEN_GIOITINH, gioiTinh);
        values.put(TB_NHANVIEN_NGAYSINH, ngaySinh);
        values.put(TB_NHANVIEN_EMAIL, email);

        long result = db.insert(TB_NHANVIEN, null, values);
        db.close();
        return result;
    }

    //Hàm băm mật khẩu
    private String bamMatKhau(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Hàm này dùng để kiểm tra tài khoản này có tồn tại ở lúc đăng ký không
    public boolean kiemTraTenDangNhap(String tenDN) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_NHANVIEN + " WHERE " + TB_NHANVIEN_TENDN + " =?", new String[]{tenDN});
        boolean tonTai = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return tonTai;
    }

    //Hàm kiểm tra đăng nhập xem có đúng tài khoản và mật khẩu không
    public boolean kiemTraDangNhap(String tenDangNhap, String matKhau){
        SQLiteDatabase db = this.getReadableDatabase();

        //Đây là mật khẩu người dùng nhập. Lấy mật khẩu người dùng nhập đi băm để so sánh dưới database
        String matKhauNDN = bamMatKhau(matKhau);

        //Sau khi băm ta bắt đầu truy vấn để kiểm tra tên ĐN và mật khẩu
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_NHANVIEN + " WHERE " + TB_NHANVIEN_TENDN +
                " =? AND " + TB_NHANVIEN_MATKHAU + " =? ", new String[]{tenDangNhap,matKhauNDN});

        boolean tonTai = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return tonTai;
    }

    public  SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}
