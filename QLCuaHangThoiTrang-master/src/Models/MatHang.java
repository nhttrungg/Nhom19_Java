
package Models;

import java.io.Serializable;

public class MatHang implements Serializable{
    private String ma;
    private String ten;

    public MatHang() {
    }

    public MatHang(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return ma + ";" + ten;
    }
    
    
}
