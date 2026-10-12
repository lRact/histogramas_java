
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Escalamiento {
    public static void main(String[] args) {

        int ancho = 600;
        int alto = 400;

        BufferedImage original = new BufferedImage(
                ancho, alto, BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g = original.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ancho, alto);

        g.setColor(Color.WHITE);
        g.fillRect(200, 100, 200, 200);
        g.dispose();

        double sx = 1.5;
        double sy = 1.5;

        int nuevoAncho = (int) (ancho * sx);
        int nuevoAlto = (int) (alto * sy);

        BufferedImage resultado = new BufferedImage(
                nuevoAncho, nuevoAlto, BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g2 = resultado.createGraphics();

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, nuevoAncho, nuevoAlto);

        g2.scale(sx, sy);
        g2.drawImage(original, 0, 0, null);

        g2.dispose();

        JFrame ventana = new JFrame("Escalamiento");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(1, 2));

        ventana.add(new JLabel(new ImageIcon(original)));
        ventana.add(new JLabel(new ImageIcon(resultado)));

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
