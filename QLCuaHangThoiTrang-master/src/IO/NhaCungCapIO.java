
package IO;

import Models.NhaCungCap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NhaCungCapIO {
    private static final String FILE_NAME_NHACUNGCAP = "NhaCungCap.txt";

    public static ArrayList<NhaCungCap> readFromFile() {
        ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
        try {
            FileReader fr = new FileReader(FILE_NAME_NHACUNGCAP);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                list.add(new NhaCungCap(txt[0], txt[1], txt[2], txt[3]));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (list == null) {
            list = new ArrayList<NhaCungCap>();
        }
        return list;
    }
    
    public static NhaCungCap getInfoById(String id) {
        NhaCungCap data = new NhaCungCap();
        try {
            FileReader fr = new FileReader(FILE_NAME_NHACUNGCAP);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                NhaCungCap value = new NhaCungCap(txt[0], txt[1], txt[2], txt[3]);
                if (value.getMa().equalsIgnoreCase(id)) {
                    data = value;
                    break;
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
    
    public static void writeToFile(ArrayList<NhaCungCap> list) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME_NHACUNGCAP);
            BufferedWriter bw = new BufferedWriter(fw);
            for (NhaCungCap i : list) {
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
    
    public static void writeToFile(ArrayList<NhaCungCap> list, boolean nextLine) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME_NHACUNGCAP, nextLine);
            BufferedWriter bw = new BufferedWriter(fw);
            for (NhaCungCap i : list) {
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
    
    public static void deleteById(String id) {
        try {
            ArrayList<NhaCungCap> list = IO.NhaCungCapIO.readFromFile();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMa().equalsIgnoreCase(id)) {
                    list.remove(i);
                    break;
                }
            }
            FileWriter fw = new FileWriter(FILE_NAME_NHACUNGCAP);
            BufferedWriter bw = new BufferedWriter(fw);
            for (NhaCungCap i : list) {
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
    
    public static void updateInfoById(String id, NhaCungCap data) {
        try {
            ArrayList<NhaCungCap> list = IO.NhaCungCapIO.readFromFile();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMa().equalsIgnoreCase(id)) {
                    list.get(i).setDiaChi(data.getDiaChi());
                    list.get(i).setSoDienThoai(data.getSoDienThoai());
                    list.get(i).setTen(data.getTen());
                    break;
                }
            }
            FileWriter fw = new FileWriter(FILE_NAME_NHACUNGCAP);
            BufferedWriter bw = new BufferedWriter(fw);
            for (NhaCungCap i : list) {
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
