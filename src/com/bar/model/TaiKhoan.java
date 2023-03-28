package com.bar.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TaiKhoan implements Serializable {

    private String tenTaiKhoan;
    private String matKhau;
    private String maNV;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, String maNV) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.maNV = maNV;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
//    _______________________LOGIN TEST____________________

//    CHECK NULL 
    public boolean checkNullUsername(String username) {
        this.setTenTaiKhoan(username);
        return this.getTenTaiKhoan().equals("");
    }

    public boolean checkNullPass(String pass) {
        this.setMatKhau(pass);
        return this.getMatKhau().equals("");
    }

//  CHECK USERNAME VALID
    public String checkValidUsername(String username) {
        this.setTenTaiKhoan(username);
        String specialCharacters = " !#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        String str2[] = username.split("");
        int count = 0;
        for (int i = 0; i < str2.length; i++) {
            if (specialCharacters.contains(str2[i])) {
                count++;
            }
        }
        return count == 0 ? "Valid" : "Invalid";
    }
    
//  CHECK PASS VALID
    public String checkValidPass(String pass){
        this.setMatKhau(pass);
        return this.getMatKhau().length() < 5 || this.getMatKhau().length() > 20 ? "Invalid" : "Valid";
    }
    
//    CHECK ACCOUNT EXIST
    public boolean checkLogin(String name, String pass){
        boolean isTrue = false;
        this.setTenTaiKhoan(name);
        this.setMatKhau(pass);
        List<TaiKhoan> list = Arrays.asList(
                new TaiKhoan("admin", "12345", "NV01"),
                new TaiKhoan("annt", "11111", "NV02"),
                new TaiKhoan("binhnv", "22222", "NV03")
        
        );
        for (TaiKhoan taiKhoan : list) {
            if(name.equals(taiKhoan.getTenTaiKhoan()) && pass.equals(taiKhoan.getMatKhau())){
                isTrue = true;
            }
        }
        return isTrue;
    }
    
    //    _______________________CHANGEPASS TEST____________________
   public boolean checkChangePass(String name, String passold, String passnew, String confirm){
        boolean isTrue = false;
        this.setTenTaiKhoan(name);
        this.setMatKhau(passold);
        List<TaiKhoan> list = Arrays.asList(
                new TaiKhoan("admin", "12345", "NV01"),
                new TaiKhoan("annt", "11111", "NV02"),
                new TaiKhoan("binhnv", "22222", "NV03")
        
        );
        for (TaiKhoan taiKhoan : list) {
            if(name.equals(taiKhoan.getTenTaiKhoan()) && passold.equals(taiKhoan.getMatKhau())){
                if(passnew.equals(confirm)){
                    this.setMatKhau(passnew);
                    isTrue = true;
                }
            }
        }
        return isTrue;
    }
   
    //  _____________ADD ACCOUNT TEST____________   
   public void checkAddAccount(String name, String user, String pass, String confirm){
       List<NhanVien> list = Arrays.asList(
               new NhanVien("NV01", "Nguyễn Thị", "An", "Nhân viên"),
               new NhanVien("NV02", "Nguyễn Văn", "Bình", "Quản lý"),
               new NhanVien("NV03", "Trần Văn", "Cê", "Nhân viên"),
               new NhanVien("NV04", "Vương Công", "Đê", "Nhân viên"));
   }
   
}
