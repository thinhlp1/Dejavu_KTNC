
package com.bar.util;

import com.bar.model.NhanVien;
import com.bar.model.TaiKhoan;



/**
 *
 * @author nguye
 */
public class Auth {
    public static NhanVien user = null;
    public static TaiKhoan account = null;
    
    public static void clear(){
        Auth.user = null;
        Auth.account = null;
    }
    
    public static String isLogin(){
        return Auth.user.getChucVu();
    }
    
    public static String userName(){
        return Auth.account.getTenTaiKhoan();
    }
    
    public static boolean isManager(){
        return user.getChucVu().equalsIgnoreCase("Quản lý");
    }
}
