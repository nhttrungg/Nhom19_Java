
package IO;

import Models.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ProductIO {
    private static final String FILE_NAME_PRODUCT = "QuanLySanPham.txt";
    
    public static ArrayList<Product> readFromFile() {
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            FileReader fr = new FileReader(FILE_NAME_PRODUCT);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                list.add(new Product(txt[0], txt[1], txt[2], Integer.parseInt(txt[3]), Long.parseLong(txt[4])));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (list == null) {
            list = new ArrayList<Product>();
        }
        return list;
    }    
    
    public static void writeToFile(ArrayList<Product> list) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME_PRODUCT);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Product i : list) {
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
    public static ArrayList deleteByID(String id) {
        ArrayList<Product> listSP = new ArrayList<>();
        listSP = IO.ProductIO.readFromFile();
        for (int i = 0; i < listSP.size(); i++) {
            if(listSP.get(i).getProductID().equalsIgnoreCase(id)){
                listSP.remove(i);
            }
        }
        return listSP;
    }
    
 public static void updateInfoById(String id, Product data) {
        try {
            ArrayList<Product> list = IO.ProductIO.readFromFile();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getProductID().equalsIgnoreCase(id)) {
                    list.get(i).setProductName(data.getProductName());
                    list.get(i).setProductCategory(data.getProductCategory());
                    list.get(i).setProductQuantity(data.getProductQuantity());
                    list.get(i).setProductPrice(data.getProductPrice());
                    break;
                }
            }
            FileWriter fw = new FileWriter(FILE_NAME_PRODUCT);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Product i : list) {
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
 
 public static Product getInfoProductById(String maSanPham) {
        Product result = new Product();
        try {
            FileReader fr = new FileReader(FILE_NAME_PRODUCT);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                Product value = new Product(txt[0], txt[1], txt[2], Integer.parseInt(txt[3]), Long.parseLong(txt[4]));
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
 
    public static void deleteByIdMatHang(String id) {
        try {
            ArrayList<Product> list = IO.ProductIO.readFromFile();
            int index = 0;
            int size = list.size();
            while (index < size) {
                if (list.get(index).getProductCategory().equalsIgnoreCase(id)) {
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
            FileWriter fw = new FileWriter(FILE_NAME_PRODUCT);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Product i : list) {
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
