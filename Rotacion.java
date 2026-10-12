import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Rotacion {
    public static void main(String[] args) {
        int ancho = 400;
        int alto = 400;

        double grados = 45.0;
        double rad = Math.toRadians(grados);

        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        BufferedImage imagenRotada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = imagen.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ancho, alto);
        g.setColor(Color.WHITE);
        g.fillRect(150, 150, 100, 100);
        g.dispose();

        Graphics2D g2 = imagenRotada.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, ancho, alto);
        g2.rotate(rad, ancho / 2, alto / 2);
        g2.drawImage(imagen, 0, 0, null);
        g2.dispose();

        JFrame frame = new JFrame("Rotacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));

        frame.add(new JLabel(new ImageIcon(imagen)));
        frame.add(new JLabel(new ImageIcon(imagenRotada)));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}