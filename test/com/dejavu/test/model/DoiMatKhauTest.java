package com.dejavu.test.model;

import com.bar.model.TaiKhoan;
import org.junit.Test;
import static org.junit.Assert.*;


public class DoiMatKhauTest {
    
    @Test
    public void testChangePass(){
        TaiKhoan taiKhoan = new TaiKhoan();
        assertTrue(taiKhoan.checkChangePass("admin", "12345", "abcdef", "abcdef"));
    }
    
}
