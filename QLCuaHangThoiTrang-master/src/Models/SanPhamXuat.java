package Models;

import java.io.Serializable;

public class SanPhamXuat implements Serializable{
    private String maSanPham;
    private int soLuong;
    private long thanhTien;
    private String maPhieuXuat;

    public SanPhamXuat() {
    }

    public SanPhamXuat(String maSanPham, int soLuong, long thanhTien, String maPhieuXuat) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.maPhieuXuat = maPhieuXuat;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    @Override
    public String toString() {
        return maSanPham + ";" + soLuong + ";" + thanhTien + ";" + maPhieuXuat;
    }
    
}
