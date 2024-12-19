package Models;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMuc {
    private String kind;
    private JPanel jpl;
    private JLabel jlb;

    public DanhMuc() {
    }

    public DanhMuc(String kind, JPanel jpl, JLabel jlb) {
        this.kind = kind;
        this.jpl = jpl;
        this.jlb = jlb;
    }
    
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpl() {
        return jpl;
    }

    public void setJpl(JPanel jpl) {
        this.jpl = jpl;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }
   
    
}
