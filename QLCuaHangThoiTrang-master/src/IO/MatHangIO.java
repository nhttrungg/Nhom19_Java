
package IO;

import Models.MatHang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MatHangIO {
    private static final String FILE_NAME_MATHANG = "MatHang.txt";
   
    public static ArrayList<MatHang> readFromFile() {
        ArrayList<MatHang> list = new ArrayList<MatHang>();
        try {
            FileReader fr = new FileReader(FILE_NAME_MATHANG);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                list.add(new MatHang(txt[0], txt[1]));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (list == null) {
            list = new ArrayList<MatHang>();
        }
        return list;
    }
    
    public static String getNameById(String id) {
        String result = "";
        try {
            FileReader fr = new FileReader(FILE_NAME_MATHANG);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                if (txt[0].equalsIgnoreCase(id)) {
                    result = txt[1];
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
    
    public static MatHang getInfoById(String id) {
        MatHang result = new MatHang();
        try {
            FileReader fr = new FileReader(FILE_NAME_MATHANG);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                if (txt[0].equalsIgnoreCase(id)) {
                    result = new MatHang(txt[0], txt[1]);
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
    
    public static void deteleById(String id) {
        try {
            ArrayList<MatHang> list = IO.MatHangIO.readFromFile();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMa().equalsIgnoreCase(id)) {
                    list.remove(i);
                    break;
                }
            }
            FileWriter fw = new FileWriter(FILE_NAME_MATHANG);
            BufferedWriter bw = new BufferedWriter(fw);
            for (MatHang i : list) {
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
    
    public static void updateInfoById(String id, MatHang data) {
        try {
            ArrayList<MatHang> list = IO.MatHangIO.readFromFile();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getMa().equalsIgnoreCase(id)) {
                    list.get(i).setTen(data.getTen());
                    break;
                }
            }
            FileWriter fw = new FileWriter(FILE_NAME_MATHANG);
            BufferedWriter bw = new BufferedWriter(fw);
            for (MatHang i : list) {
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
    
    public static void writeToFile(ArrayList<MatHang> list) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME_MATHANG);
            BufferedWriter bw = new BufferedWriter(fw);
            for (MatHang i : list) {
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
    
    public static void writeToFile(ArrayList<MatHang> list, boolean nextLine) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME_MATHANG, nextLine);
            BufferedWriter bw = new BufferedWriter(fw);
            for (MatHang i : list) {
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
