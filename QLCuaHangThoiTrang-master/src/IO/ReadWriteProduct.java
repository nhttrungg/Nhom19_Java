
package IO;

import Models.MatHang;
import Models.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReadWriteProduct {
     ListProduct listProduct = new ListProduct();
    public ReadWriteProduct() {
    }
       public void writeFile(Product product, String fileName, ArrayList<Product> danhsachsanpham) {
            ArrayList<MatHang> danhsachmathang = new ArrayList<>();
            danhsachmathang = readFromFile("MatHang.txt");
        try (FileWriter fw = new FileWriter(fileName, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < danhsachmathang.size(); i++) {
                if(product.getProductCategory().equalsIgnoreCase(danhsachmathang.get(i).getTen())){
                    product.setProductCategory(danhsachmathang.get(i).getMa());
                }
            }
            pw.println(product.toString());
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private ArrayList<MatHang> readFromFile(String url) {
        ArrayList<MatHang> list = new ArrayList<MatHang>();
        String FILE_NAME = "MatHang.txt";
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
    public ArrayList readFile(String fileName, ArrayList<Product> danhsachsanpham) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String txt[] = line.split(";");
                String id = txt[0];
                String name = txt[1];
                String category = txt[2];
                int quantity = Integer.parseInt(txt[3]);
                long price = Long.parseLong(txt[4]);
                Product p = new Product(id, name, category, quantity, price);
                if (!listProduct.correctProduct(txt[0], danhsachsanpham)) {
                    danhsachsanpham.add(p);
                }

            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        };
        return danhsachsanpham;
    }
}
