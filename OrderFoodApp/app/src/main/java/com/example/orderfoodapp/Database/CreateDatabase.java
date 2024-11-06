package com.example.orderfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_USER = "USER";
    public static String TB_MONAN = "MONAN";
    public static String TB_LOAIMONAN = "LOAIMONAN";
    public static String TB_BANAN = "BANAN";
    public static String TB_GOIMON = "GOIMON";
    public static String TB_CHITIETGOIMON = "CHITIETGOIMON";
    public static String TB_ROLE = "ROLE";
    public static String TB_NGUYENLIEU = "NGUYENLIEU";
    public static String TB_NGUYENLIEUMONAN = "NGUYENLIEUMONAN";
    public static String TB_PHUONGTHUCTHANHTOAN = "PHUONGTHUCTHANHTOAN";

    public static String TB_USER_MAUSER = "MAUSER";
    public static String TB_USER_TENDN = "TENDN";
    public static String TB_USER_MATKHAU = "MATKHAU";
    public static String TB_USER_GIOITINH = "GIOITINH";
    public static String TB_USER_NGAYSINH = "NGAYSINH";
    public static String TB_USER_EMAIL = "EMAIL";
    public static String TB_USER_MAROLE = "MAROLE";
    public static String TB_USER_SDT = "SDT";
    public static String TB_USER_FULLNAME = "FULLNAME";
    public static String TB_USER_NGAYTAO = "NGAYTAO";
    public static String TB_USER_DIEMTICHLUY = "DIEMTICHLUY";

    public static String TB_MONAN_MAMONAN = "MAMONAN";
    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";
    public static String TB_MONAN_HINHANH = "HINHANH";
    public static String TB_MONAN_MANGUYENLIEU = "MANGUYENLIEU";

    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";
    public static String TB_LOAIMONAN_MOTA = "MOTA";

    public static String TB_BANAN_MABAN = "MABAN";
    public static String TB_BANAN_TENBAN = "TENBAN";
    public static String TB_BANAN_TINHTRANG = "TINHTRANG";

    public static String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_GOIMON_MAUSER = "MAUSER";
    public static String TB_GOIMON_NGAYGOI = "NGAYGOI";
    public static String TB_GOIMON_MABAN = "MABAN";
    public static String TB_GOIMON_NGAYTHANHTOAN = "NGAYTHANHTOAN";
    public static String TB_GOIMON_TONGSOMON = "TONGSOMON";
    public static String TB_GOIMON_TONGTIEN = "TONGTIEN";
    public static String TB_GOIMON_THUAVAT = "THUEVAT";
    public static String TB_GOIMON_MAPHUONGTHUCTHANHTOAN = "MAPHUONGTHUCTHANHTOAN";

    public static String TB_CHITIETGOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_CHITIETGOIMON_MAMONAN = "MAMONAN";
    public static String TB_CHITIETGOIMON_SOLUONG = "SOLUONG";
    public static String TB_CHITIETGOIMON_TRANGTHAI = "TRANGTHAI";

    public static String TB_ROLE_MAROLE = "MAROLE";
    public static String TB_ROLE_CHUCVU = "CHUCVU";
    public static String TB_ROLE_MOTA = "MOTA";

    public static String TB_NGUYENLIEU_MANGUYENLIEU = "MANGUYENLIEU";
    public static String TB_NGUYENLIEU_TENNGUYENLIEU = "TENNGUYENLIEU";
    public static String TB_NGUYENLIEU_KHOILUONG = "KHOILUONG";
    public static String TB_NGUYENLIEU_MOTTA = "MOTA";

    public static String TB_NGUYENLIEUMONAN_MANLMA = "MANLMA";
    public static String TB_NGUYENLEUMONAN_MAMONAN = "MAMONAN";
    public static String TB_NGUYENLIEUMONAN_MANGUYENLIEU = "MANGUYENLIEU";

    public static String TB_PHUONGTHUCTHANHTOAN_MAPTTH = "MAPTTH";
    public static String TB_PHUONGTHUCTHANHTOAN_NAME = "NAME";


    public CreateDatabase(Context context) {
        super(context, "OrderFoodApp", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbUSER = "CREATE TABLE " + TB_USER + " ( "
                + TB_USER_MAUSER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_USER_TENDN + " TEXT, "
                + TB_USER_MATKHAU + " TEXT, "
                + TB_USER_GIOITINH + " TEXT, "
                + TB_USER_NGAYSINH + " TEXT, "
                + TB_USER_EMAIL + " TEXT , "
                + TB_USER_DIEMTICHLUY + " TEXT , "
                + TB_USER_NGAYTAO + " TEXT, "
                + TB_USER_FULLNAME + " TEXT, "
                + TB_USER_SDT + " TEXT, "
                + TB_USER_MAROLE + " INTEGER )";

        String tbBANAN = "CREATE TABLE " + TB_BANAN + " ( "
                + TB_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BANAN_TENBAN + " TEXT, "
                + TB_BANAN_TINHTRANG + " TEXT )";

        String tbMONAN = "CREATE TABLE " + TB_MONAN + " ( "
                + TB_MONAN_MAMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT, "
                + TB_MONAN_MALOAI + " INTEGER, "
                + TB_MONAN_GIATIEN + " TEXT,"
                + TB_MONAN_HINHANH + " TEXT, "
                + TB_MONAN_MANGUYENLIEU + " INTEGER)";

        String tbLOAIMONAN = "CREATE TABLE " + TB_LOAIMONAN + " ( "
                + TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_LOAIMONAN_TENLOAI + " TEXT ,"
                + TB_LOAIMONAN_MOTA + " TEXT )";

        String tbGOIMON = "CREATE TABLE " + TB_GOIMON + " ( "
                + TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABAN + " INTEGER, "
                + TB_GOIMON_MAUSER + " INTEGER, "
                + TB_GOIMON_NGAYGOI + " TEXT, "
                + TB_GOIMON_NGAYTHANHTOAN + " TEXT,"
                + TB_GOIMON_TONGSOMON + " TEXT ,"
                + TB_GOIMON_TONGTIEN + " TEXT, "
                + TB_GOIMON_THUAVAT + " TEXT, "
                + TB_GOIMON_MAPHUONGTHUCTHANHTOAN + " INTEGER )";

        String tbCHITETGOIMON = "CREATE TABLE " + TB_CHITIETGOIMON + " ( "
                + TB_CHITIETGOIMON_MAGOIMON + " INTEGER, "
                + TB_CHITIETGOIMON_MAMONAN + " INTEGER, "
                + TB_CHITIETGOIMON_SOLUONG + " INTEGER, "
                + TB_CHITIETGOIMON_TRANGTHAI + " TEXT,"
                + "PRIMARY KEY ( " + TB_CHITIETGOIMON_MAMONAN + "," + TB_CHITIETGOIMON_MAGOIMON + "))";

        String tbROLE = "CREATE TABLE " + TB_ROLE + " ( "
                + TB_ROLE_MAROLE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ROLE_CHUCVU + " TEXT, "
                + TB_ROLE_MOTA + " TEXT )";

        String tbNGUYENLIEU = "CREATE TABLE " + TB_NGUYENLIEU + " ( "
                + TB_NGUYENLIEU_MANGUYENLIEU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NGUYENLIEU_TENNGUYENLIEU + " TEXT ,"
                + TB_NGUYENLIEU_KHOILUONG + " TEXT, "
                + TB_NGUYENLIEU_MOTTA + " TETX )";

        String tbNGUYENLIEUMONAN = "CREATE TABLE " + TB_NGUYENLIEUMONAN + " ( "
                + TB_NGUYENLIEUMONAN_MANGUYENLIEU + " INTEGER, "
                + TB_NGUYENLEUMONAN_MAMONAN + " INTEGER, "
                + "PRIMARY KEY (" + TB_NGUYENLIEUMONAN_MANGUYENLIEU + ", " + TB_NGUYENLEUMONAN_MAMONAN + "))";

        String tbPHUONGTHUCTHANHTOAN = "CREATE TABLE " + TB_PHUONGTHUCTHANHTOAN + " ( "
                + TB_PHUONGTHUCTHANHTOAN_MAPTTH + " TEXT, "
                + TB_PHUONGTHUCTHANHTOAN_NAME + " TEXT )";

        db.execSQL(tbUSER);
        db.execSQL(tbROLE);
        db.execSQL(tbBANAN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbGOIMON);
        db.execSQL(tbCHITETGOIMON);
        db.execSQL(tbNGUYENLIEU);
        db.execSQL(tbNGUYENLIEUMONAN);
        db.execSQL(tbPHUONGTHUCTHANHTOAN);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TB_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TB_ROLE);
            db.execSQL("DROP TABLE IF EXISTS " + TB_BANAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_MONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_LOAIMONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_GOIMON);
            db.execSQL("DROP TABLE IF EXISTS " + TB_CHITIETGOIMON);
            db.execSQL("DROP TABLE IF EXISTS " + TB_NGUYENLIEU);
            db.execSQL("DROP TABLE IF EXISTS " + TB_NGUYENLIEUMONAN);
            db.execSQL("DROP TABLE IF EXISTS " + TB_PHUONGTHUCTHANHTOAN);
            onCreate(db);
        }

    }

    //Hàm thêm nhân viên
    public long insertUSER(String tenDN, String matKhau, String gioiTinh, String ngaySinh, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String matKhauDaBam = bamMatKhau(matKhau);

        values.put(TB_USER_TENDN, tenDN);
        values.put(TB_USER_MATKHAU, matKhauDaBam);
        values.put(TB_USER_GIOITINH, gioiTinh);
        values.put(TB_USER_NGAYSINH, ngaySinh);
        values.put(TB_USER_EMAIL, email);

        long result = db.insert(TB_USER, null, values);
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_USER + " WHERE " + TB_USER_TENDN + " =?", new String[]{tenDN});
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_USER + " WHERE " + TB_USER_TENDN +
                " =? AND " + TB_USER_MATKHAU + " =? ", new String[]{tenDangNhap,matKhauNDN});

        boolean tonTai = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return tonTai;
    }

    public  SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}
