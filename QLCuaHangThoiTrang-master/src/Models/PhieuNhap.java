
package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PhieuNhap {
   private String ma; 
   private String maNhaCungCap;
   private String ngayTao;
   private String ngayCapNhat;
   private Long tien;

    public PhieuNhap(String ma, String maNhaCungCap, Long tien) {
        this.ma = ma;
        this.maNhaCungCap = maNhaCungCap;
        this.tien = tien;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.ngayTao = formatter.format(new Date());
        this.ngayCapNhat = formatter.format(new Date());
    }

    public PhieuNhap() {
    }

    public String getMa() {
        return ma;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public Long getTien() {
        return tien;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setTien(Long tien) {
        this.tien = tien;
    }

    @Override
    public String toString() {
        return ma + "-" + maNhaCungCap + "-" + ngayTao + "-" + ngayCapNhat + "-" + tien;
    }
    
   
   
}
