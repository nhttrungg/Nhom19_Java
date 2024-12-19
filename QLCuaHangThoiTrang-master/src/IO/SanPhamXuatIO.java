package IO;

import Models.Product;
import Models.SanPhamXuat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SanPhamXuatIO {
    static String FILE_NAME = "SanPhamXuat.txt";
    public void writeFIleSPX(ArrayList<SanPhamXuat> danhsachsanphamxuat) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (SanPhamXuat sanPhamXuat : danhsachsanphamxuat) {
                pw.println(sanPhamXuat.toString());
            }
            pw.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ArrayList readFileSPX() {
            ArrayList<SanPhamXuat> listsanphamxuat = new ArrayList<>();
        try {
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String [] txt = line.split(";");
                String maSanPham = txt[0];
                String soLuong = txt[1];
                String thanhTien = txt[2];
                String maPhieuXuat = txt[3];
                SanPhamXuat sanphamxuat = new SanPhamXuat(maSanPham, Integer.parseInt(soLuong), Integer.parseInt(thanhTien), maPhieuXuat);
                if(!listsanphamxuat.contains(sanphamxuat)){
                    listsanphamxuat.add(sanphamxuat);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
            if(listsanphamxuat == null){
                listsanphamxuat = new ArrayList<SanPhamXuat>();
            }
        return listsanphamxuat;
    }
     
    public static ArrayList getListByID(String maPhieu) {
        ArrayList<SanPhamXuat> listsanphamxuat = new ArrayList<>();
        try {
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                SanPhamXuat value = new SanPhamXuat(txt[0], Integer.parseInt(txt[1]), Long.parseLong(txt[2]), txt[3]);
                if (value.getMaPhieuXuat().equalsIgnoreCase(maPhieu)) {
                    listsanphamxuat.add(value);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (listsanphamxuat == null) {
            listsanphamxuat = new ArrayList<SanPhamXuat>();
        }
        return listsanphamxuat;
    }
    public static Product getInfoProductById(String maSanPham,int soLuong,long giaBan) {
        Product result = new Product();
        try {
            FileReader fr = new FileReader("QuanLySanPham.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                Product value = new Product(txt[0], txt[1], txt[2], soLuong, giaBan);
                if (value.getProductID().equalsIgnoreCase(maSanPham)) {
                    result = value;
                    break;
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }   
    public void deleteByIdMaPhieuNhap(String id) {
        try {
            ArrayList<SanPhamXuat> list = readFileSPX();
            int index = 0;
            int size = list.size();
            while (index < size) {
                if (list.get(index).getMaPhieuXuat().equalsIgnoreCase(id)) {
                    list.remove(index);
                    if (index == 0) {
                        index = 0;
                    } else {
                        index = index - 1;
                    }
                    size = list.size();
                } else {
                    index = index + 1;
                }
            }
            FileWriter fw = new FileWriter(FILE_NAME);
            BufferedWriter bw = new BufferedWriter(fw);
            for (SanPhamXuat i : list) {
                bw.write(i.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void updateDataByIdMaPhieu(String maPhieu, ArrayList<SanPhamXuat> data) {
        try {
            deleteByIdMaPhieuNhap(maPhieu);
            FileWriter fw = new FileWriter(FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (SanPhamXuat i : data) {
                bw.write(i.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
