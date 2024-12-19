package IO;

import java.io.Serializable;
import java.util.ArrayList;
import Models.Product;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

public class ListProduct implements Serializable {

    public void displayData(DefaultTableModel dtm,ArrayList<Product> danhsachsanpham) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            danhsachsanpham.stream().forEach((product) -> {
            String formattedNumber = format.format(product.getProductPrice());
            String price = formattedNumber;
                dtm.addRow(new Object[]{product.getProductID(),product.getProductName(), product.getProductCategory(),product.getProductQuantity(), price});
            });
          
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList findAll(ArrayList<Product> danhsachsanpham, String name){
        ArrayList<Product> danhsachtatca = new ArrayList<>();
            for (int i = 0; i < danhsachsanpham.size(); i++) {
            danhsachtatca= danhsachsanpham;
        }
            return danhsachtatca;
    }

    public boolean correctProduct(String id,ArrayList<Product> danhsachsanpham) {
        try {
            for (Product product : danhsachsanpham) {
                if (product.getProductID().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean addProduct(Product p,ArrayList<Product> danhsachsanpham) {
        try {
            if (danhsachsanpham.contains(p)) {
                return false;
            } else {
                danhsachsanpham.add(p);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public void delete(int index,ArrayList<Product> danhsachsanpham) {
        danhsachsanpham.remove(index);
    }

    public boolean checkIDSame(String id, ArrayList<Product> danhsachsanpham) {
        int count = 0;
        for (int i = 0; i < danhsachsanpham.size(); i++) {
            if (danhsachsanpham.get(i).getProductID().toUpperCase().equalsIgnoreCase(id.toUpperCase())) {
                count++;
            }
        }
        if(count != 0) return true;
        else return false;
    }
    public ArrayList searchProduct(ArrayList<Product> danhsachsanpham,String name) {
        ArrayList<Product> danhsachtimkiem = new ArrayList<>();
        for (int i = 0; i < danhsachsanpham.size(); i++) {
            if(danhsachsanpham.get(i).getProductName().toUpperCase().equalsIgnoreCase(name.toUpperCase())){
                Product productSearch = new Product();
                productSearch = danhsachsanpham.get(i);
                danhsachtimkiem.add(productSearch);
            }
        }
        return danhsachtimkiem;
    }
}
