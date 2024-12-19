
package IO;

import Models.Product;
import Models.SanPhamNhap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SanPhamNhapIO {
    private static final String FILE_NAME = "SanPhamNhap.txt";
    
    public static ArrayList<SanPhamNhap> readFromFile() {
        ArrayList<SanPhamNhap> list = new ArrayList<SanPhamNhap>();
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
                list.add(new SanPhamNhap(txt[0], Integer.parseInt(txt[1]), Long.parseLong(txt[2]), txt[3]));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (list == null) {
            list = new ArrayList<SanPhamNhap>();
        }
        return list;
    }
    
    public static ArrayList<SanPhamNhap> getListById(String maPhieu) {
        ArrayList<SanPhamNhap> list = new ArrayList<SanPhamNhap>();
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
                SanPhamNhap value = new SanPhamNhap(txt[0], Integer.parseInt(txt[1]), Long.parseLong(txt[2]), txt[3]);
                if (value.getMaPhieuNhap().equalsIgnoreCase(maPhieu)) {
                    list.add(value);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (list == null) {
            list = new ArrayList<SanPhamNhap>();
        }
        return list;
    }
    
    public static void deleteByIdMaPhieuNhap(String id) {
        try {
            ArrayList<SanPhamNhap> list = IO.SanPhamNhapIO.readFromFile();
            int index = 0;
            int size = list.size();
            while (index < size) {
                if (list.get(index).getMaPhieuNhap().equalsIgnoreCase(id)) {
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
            for (SanPhamNhap i : list) {
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
    
    public static void writeToFile(ArrayList<SanPhamNhap> list) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            BufferedWriter bw = new BufferedWriter(fw);
            for (SanPhamNhap i : list) {
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
    
    public static void writeToFile(ArrayList<SanPhamNhap> list, boolean next) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, next);
            BufferedWriter bw = new BufferedWriter(fw);
            for (SanPhamNhap i : list) {
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
    
    public static void updateDataByIdMaPhieu(String maPhieu, ArrayList<SanPhamNhap> data) {
        try {
            IO.SanPhamNhapIO.deleteByIdMaPhieuNhap(maPhieu);
            FileWriter fw = new FileWriter(FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (SanPhamNhap i : data) {
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
