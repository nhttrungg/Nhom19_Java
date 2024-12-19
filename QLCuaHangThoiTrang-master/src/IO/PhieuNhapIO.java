
package IO;

import Models.PhieuNhap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PhieuNhapIO {
    private static final String FILE_NAME_PHIEUNHAP = "PhieuNhap.txt";
    
    public static void writeToFile(PhieuNhap value) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME_PHIEUNHAP, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(value.toString());
            bw.newLine();
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void writeToFile(ArrayList<PhieuNhap> list) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME_PHIEUNHAP);
            BufferedWriter bw = new BufferedWriter(fw);
            for (PhieuNhap i : list) {
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
    
    public static void updateInfo(PhieuNhap data) {
        try {
            ArrayList<PhieuNhap> list = IO.PhieuNhapIO.readFromFile();
            for (int i = 0; i < list.size(); i++) {
                if (data.getMa().equalsIgnoreCase(list.get(i).getMa())) {
                    list.get(i).setNgayCapNhat(data.getNgayCapNhat());
                    list.get(i).setTien(data.getTien());
                    break;
                }
            }
            FileWriter fw = new FileWriter(FILE_NAME_PHIEUNHAP);
            BufferedWriter bw = new BufferedWriter(fw);
            for (PhieuNhap i : list) {
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
    
    public static PhieuNhap getInfoById(String id) {
        PhieuNhap data = new PhieuNhap();
        try {
            FileReader fr = new FileReader(FILE_NAME_PHIEUNHAP);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split("-");
                if (txt[0].equalsIgnoreCase(id)) {
                    data.setMa(txt[0]);
                    data.setMaNhaCungCap(txt[1]);
                    data.setNgayTao(txt[2]);
                    data.setNgayCapNhat(txt[3]);
                    data.setTien(Long.parseLong(txt[4]));
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
    
    public static void deleteById(String id) {
        try {
            ArrayList<PhieuNhap> list = IO.PhieuNhapIO.readFromFile();
            int index = 0;
            int size = list.size();
            while (index < size) {
                if (list.get(index).getMa().equalsIgnoreCase(id)) {
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
            FileWriter fw = new FileWriter(FILE_NAME_PHIEUNHAP);
            BufferedWriter bw = new BufferedWriter(fw);
            for (PhieuNhap i : list) {
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
    
    public static ArrayList<PhieuNhap> readFromFile() {
        ArrayList<PhieuNhap> list = new ArrayList<PhieuNhap>();
        try {
            FileReader fr = new FileReader(FILE_NAME_PHIEUNHAP);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split("-");
                PhieuNhap value = new PhieuNhap(txt[0], txt[1], Long.parseLong(txt[4]));
                value.setNgayTao(txt[2]);
                value.setNgayCapNhat(txt[3]);
                list.add(value);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (list == null) {
            list = new ArrayList<PhieuNhap>();
        }
        return list;
    }

}
