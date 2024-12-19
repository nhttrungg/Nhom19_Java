package IO;

import Models.NhaCungCap;
import Models.PhieuNhap;
import Models.PhieuXuat;
import Models.Product;
import Models.SanPhamNhap;
import Models.SanPhamXuat;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PdfIO {
    private static File fontFile = new File("fonts/vuTimes.ttf");
    public static void handleExportPdfFile(JPanel mainView, String maPhieu) {
        File defaultFile = new File("PhieuNhap.pdf");
        JFileChooser fChooser = new JFileChooser();
        fChooser.setSelectedFile(defaultFile);
        int result = fChooser.showSaveDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fChooser.getSelectedFile().getAbsolutePath();
                createPdf(filePath, maPhieu);
                JOptionPane.showMessageDialog(null, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | DocumentException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }     
    };
        
    public static void createPdf(String filePath, String inputMaPhieu) throws IOException, DocumentException{
        PhieuNhap phieuNhap = IO.PhieuNhapIO.getInfoById(inputMaPhieu);
        NhaCungCap nhaCungCap = IO.NhaCungCapIO.getInfoById(phieuNhap.getMaNhaCungCap());
        if (nhaCungCap.getMa() == null) {
            nhaCungCap = new NhaCungCap(phieuNhap.getMaNhaCungCap(), "Không tồn tại", "Không tồn tại", "Không tồn tại");
        }
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontTitle = new Font(bf, 18, Font.BOLD);
        Font font = new Font(bf, 12);
        Font font2 = new Font(bf, 12, Font.BOLD);
        document.open(); 
        String title = "PHIẾU NHẬP HÀNG\n";
        String ngay[] = phieuNhap.getNgayTao().split(" ");
        String ngayTao[] = ngay[0].split("/");
        String ngayTaoPhieu = "Ngày " + ngayTao[0] + " tháng " + ngayTao[1] + " năm " + ngayTao[2];
        String maPhieu = "Mã phiếu: " + phieuNhap.getMa();
        String thongTin = "Nhà cung cấp: " + nhaCungCap.getTen() + "\nĐiện thoại: " + nhaCungCap.getSoDienThoai() + "\nĐịa chỉ: " + nhaCungCap.getDiaChi();
        Paragraph pNgayTao = new Paragraph(ngayTaoPhieu, font);
        pNgayTao.setAlignment(Element.ALIGN_LEFT);
        Paragraph pMaPhieu = new Paragraph(maPhieu,font);
        pMaPhieu.setAlignment(Element.ALIGN_LEFT);
        Paragraph pTitle = new Paragraph(title, fontTitle);
        pTitle.setAlignment(Element.ALIGN_CENTER);
        Paragraph pDanhSach = new Paragraph("DANH SÁCH SẢN PHẨM\n\n", font2);
        pDanhSach.setAlignment(Element.ALIGN_CENTER);
        document.add(pTitle);
        document.add(pNgayTao);
        document.add(pMaPhieu);
        document.add(new Paragraph(thongTin, font));
        document.add(pDanhSach);
        PdfPTable tb = new PdfPTable(6);
        tb.addCell(new Paragraph("Mã sản phẩm", font));
        tb.addCell(new Paragraph("Tên sản phẩm", font));
        tb.addCell(new Paragraph("Loại", font));
        tb.addCell(new Paragraph("Số lượng", font));
        tb.addCell(new Paragraph("Giá bán", font));
        tb.addCell(new Paragraph("Thành tiền", font));
        ArrayList<Product> listSP = new ArrayList<Product>();
        ArrayList<SanPhamNhap> listSPNhap = IO.SanPhamNhapIO.getListById(inputMaPhieu);
            for (int i = 0; i < listSPNhap.size(); i++) {
                Product value = IO.ProductIO.getInfoProductById(listSPNhap.get(i).getMaSanPham());
                String category = "";
                if (value.getProductID() == null) {
                    value.setProductName("Không tồn tại");
                    category = "Không tồn tại";
                    long price = listSPNhap.get(i).getThanhTien() / listSPNhap.get(i).getSoLuong();
                    value.setProductPrice(price);
                } else {
                    category = IO.MatHangIO.getNameById(value.getProductCategory());
                }
                listSP.add(new Product(listSPNhap.get(i).getMaSanPham(), value.getProductName(), category, listSPNhap.get(i).getSoLuong(), value.getProductPrice()));
            }
        for (int i = 0; i < listSP.size(); i++) {
            String Ma = listSP.get(i).getProductID();
            String Ten = listSP.get(i).getProductName();
            String Loai = listSP.get(i).getProductCategory();
            int soLuong = listSP.get(i).getProductQuantity();
            long giaBan = listSP.get(i).getProductPrice();
            long thanhTien = listSPNhap.get(i).getThanhTien();
            tb.addCell(new Paragraph(Ma, font));
            tb.addCell(new Paragraph(Ten, font));
            tb.addCell(new Paragraph(Loai, font));
            tb.addCell(new Paragraph(Integer.toString(soLuong), font));
            tb.addCell(new Paragraph(String.format("%,d", giaBan), font));
            tb.addCell(new Paragraph(String.format("%,d", thanhTien), font));
        }
        document.add(tb);
        Paragraph pTongTien = new Paragraph("\nTổng tiền: " + String.format("%,d", phieuNhap.getTien()) + " VND", font);
        document.add(pTongTien);
        document.close();
    }
    public static void handleExportPdfFilePX(JPanel mainView, String maPhieu) {
        File defaultFile = new File("PhieuXuat.pdf");
        JFileChooser fChooser = new JFileChooser();
        fChooser.setSelectedFile(defaultFile);
        int result = fChooser.showSaveDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fChooser.getSelectedFile().getAbsolutePath();
                createPhieuXuatPDF(maPhieu, filePath);
                JOptionPane.showMessageDialog(null, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | DocumentException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Xuất file không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }  
    }
    public static void createPhieuXuatPDF(String maPhieu,String filePath) throws FileNotFoundException, DocumentException, IOException {
            PhieuXuat phieuxuat = IO.PhieuXuatIO.getInfoById(maPhieu);
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();
            BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontTitle = new Font(bf, 18, Font.BOLD);
            Font fontContent = new Font(bf, 12, Font.NORMAL);
            
            Paragraph p1 = new Paragraph("THÔNG TIN PHIẾU XUẤT\n\n", fontTitle);
            p1.setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);
            
            Paragraph p2 = new Paragraph("Mã phiếu: " + phieuxuat.getMaPhieu() + "\nTên khách hàng: " + phieuxuat.getTenKH()
                    + "\nSố điện thoại: " + phieuxuat.getSdtKH()
                    + "\nĐịa chỉ: " + phieuxuat.getDiaChi() + "\n\n", fontContent);
            doc.add(p2);
            
            PdfPTable tb = new PdfPTable(5);
            
            tb.addCell(new Paragraph("Mã sản phẩm", fontContent));
            tb.addCell(new Paragraph("Tên sản phẩm", fontContent));
            tb.addCell(new Paragraph("Loại", fontContent));
            tb.addCell(new Paragraph("Số lượng", fontContent));
            tb.addCell(new Paragraph("Giá bán", fontContent));
            System.out.println(maPhieu);
            ArrayList<SanPhamXuat> listSPX = IO.SanPhamXuatIO.getListByID(maPhieu);
            long tongTien = 0;
            for (SanPhamXuat spx : listSPX) {
                Product product = IO.SanPhamXuatIO.getInfoProductById(spx.getMaSanPham(), spx.getSoLuong(), spx.getThanhTien());
                String Ma = product.getProductID();
                String Ten = product.getProductName();
                String Loai = IO.MatHangIO.getNameById(product.getProductCategory());
                String soLuong = String.valueOf(product.getProductQuantity());
                String giaBan = String.valueOf(product.getProductPrice());
                tb.addCell(new Paragraph(Ma, fontContent));
                tb.addCell(new Paragraph(Ten, fontContent));
                tb.addCell(new Paragraph(Loai, fontContent));
                tb.addCell(new Paragraph(soLuong, fontContent));
                tb.addCell(new Paragraph(giaBan, fontContent));
                tongTien += product.getProductPrice();
            }
            doc.add(tb);
            Paragraph p3 = new Paragraph("\nTổng tiền: " + String.valueOf(tongTien), fontContent);
            doc.add(p3);
            doc.close();
            
    }
    
}
