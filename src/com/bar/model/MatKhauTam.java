/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bar.model;

/**
 *
 * @author Lenovo
 */
public class MatKhauTam {
    String passTam;
    String tenTaiKhoanTam;

    public MatKhauTam() {
    }

    public MatKhauTam(String passTam, String tenTaiKhoanTam) {
        this.passTam = passTam;
        this.tenTaiKhoanTam = tenTaiKhoanTam;
    }

    public String getPassTam() {
        return passTam;
    }

    public void setPassTam(String passTam) {
        this.passTam = passTam;
    }

    public String getTenTaiKhoanTam() {
        return tenTaiKhoanTam;
    }

    public void setTenTaiKhoanTam(String tenTaiKhoanTam) {
        this.tenTaiKhoanTam = tenTaiKhoanTam;
    }

 
    
}
