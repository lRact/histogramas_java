import javax.swing.*;
import java.awt.*;

public class JFrameImage extends JFrame {
    private JLabel etiqueta;

    public JFrameImage(Image aux){
        etiqueta = new JLabel();
        ImageIcon ii = new ImageIcon(aux);
        etiqueta.setIcon(ii);
        add(etiqueta);
    }
}