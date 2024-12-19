
package Models;

public class SanPhamNhap {
    private String maSanPham;
    private int soLuong;
    private long thanhTien;
    private String maPhieuNhap;

    public SanPhamNhap() {
    }

    public SanPhamNhap(String maSanPham, int soLuong, long thanhTien, String maPhieuNhap) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    @Override
    public String toString() {
        return maSanPham + ";" + soLuong + ";" + thanhTien + ";" + maPhieuNhap;
    }
}
