
package com.bar.model;

import com.bar.view.MainDejavu;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class HoaDon {
    private String maHD;
    private String ngayLapHD;
    private String trangThaiThanhToan;
    private String maNV;
    private boolean trangThai_ThongKe;
    private String GhiChu;
    private long TongTien;

    public HoaDon() {
    }

    public HoaDon(String maHD, String ngayLapHD, String trangThaiThanhToan, String maNV) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.maNV = maNV;
    }

    public HoaDon(String maHD, String ngayLapHD, String trangThaiThanhToan, String maNV, boolean trangThai_ThongKe) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.maNV = maNV;
        this.trangThai_ThongKe = trangThai_ThongKe;
    }

    public HoaDon(String maHD, String ngayLapHD, String trangThaiThanhToan, String maNV, boolean trangThai_ThongKe, String GhiChu, long TongTien) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.maNV = maNV;
        this.trangThai_ThongKe = trangThai_ThongKe;
        this.GhiChu = GhiChu;
        this.TongTien = TongTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
  

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(String ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThai) {
        this.trangThaiThanhToan = trangThai;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public boolean isTrangThai_ThongKe() {
        return trangThai_ThongKe;
    }

    public void setTrangThai_ThongKe(boolean trangThai_ThongKe) {
        this.trangThai_ThongKe = trangThai_ThongKe;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long TongTien) {
        this.TongTien = TongTien;
    }
    
    public String searchTheoTenNV(String maNV){
        List<HoaDon> listHD = Arrays.asList(new HoaDon[]{
            new HoaDon("HD001", "28/03/2023", "Đã thanh toán", "NV01", true, GhiChu, 500000),
            new HoaDon("HD002", "28/03/2023", "Đã thanh toán", "NV02", true, GhiChu, 600000),
            new HoaDon("HD003", "28/03/2023", "Đã thanh toán", "NV03", true, GhiChu, 800000),        
        });     
        
        for (int i = 0; i < listHD.size() - 1; i++) {
            if(maNV.equalsIgnoreCase(listHD.get(i).getMaNV())){
                return maNV;
            }
        }
        return "Invalid";
    }
    
    public boolean searchTheoTTTK(boolean TTTK){
        List<HoaDon> listHD = Arrays.asList(new HoaDon[]{
            new HoaDon("HD001", "28/03/2023", "Đã thanh toán", "NV01", true, GhiChu, 500000),
            new HoaDon("HD002", "28/03/2023", "Đã thanh toán", "NV02", true, GhiChu, 600000),
            new HoaDon("HD003", "28/03/2023", "Đã thanh toán", "NV03", true, GhiChu, 800000),        
        });     
        
        for (int i = 0; i < listHD.size() - 1; i++) {
            if(TTTK){
                return true;
            }
        }
        return false;
    }
    
    public String searchTheoNgay(String ngay){
        List<HoaDon> listHD = Arrays.asList(new HoaDon[]{
            new HoaDon("HD001", "28/03/2023", "Đã thanh toán", "NV01", true, GhiChu, 500000),
            new HoaDon("HD002", "28/03/2023", "Đã thanh toán", "NV02", true, GhiChu, 600000),
            new HoaDon("HD003", "28/03/2023", "Đã thanh toán", "NV03", true, GhiChu, 800000),        
        });     
        
        for (int i = 0; i < listHD.size() - 1; i++) {
            if(ngay.equalsIgnoreCase(listHD.get(i).getNgayLapHD())){
                return ngay;
            }
        }
        return "Invalid";
    }
    
    public String huyHoaDon(String id, boolean trangThai){
        List<HoaDon> listHD = Arrays.asList(new HoaDon[]{
            new HoaDon("HD001", "28/03/2023", "Đã thanh toán", "NV01", true, GhiChu, 500000),
            new HoaDon("HD002", "28/03/2023", "Đã thanh toán", "NV02", true, GhiChu, 600000),
            new HoaDon("HD003", "28/03/2023", "Đã thanh toán", "NV03", true, GhiChu, 800000),        
        });  
        
        for (int i = 0; i < listHD.size() - 1; i++) {
            if(id.equalsIgnoreCase(listHD.get(i).getMaHD())){
                listHD.get(i).setTrangThai_ThongKe(trangThai);
                return "Valid";
            }
        }
        return "Invalid";
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", trangThai=" + trangThaiThanhToan + ", maNV=" + maNV + ", trangThai_ThongKe=" + trangThai_ThongKe + '}';
    }
    
}