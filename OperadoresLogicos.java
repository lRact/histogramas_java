import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OperadoresLogicos {
    public static void main(String[] args) {
        int ancho = 400;
        int alto = 400;

        BufferedImage circulo = new BufferedImage(ancho, alto, BufferedImage.TYPE_BYTE_BINARY);
        BufferedImage cuadrado = new BufferedImage(ancho, alto, BufferedImage.TYPE_BYTE_BINARY);

        BufferedImage resultadoOr = new BufferedImage(ancho, alto, BufferedImage.TYPE_BYTE_BINARY);
        BufferedImage resultadoXor = new BufferedImage(ancho, alto, BufferedImage.TYPE_BYTE_BINARY);
        BufferedImage resultadoAnd = new BufferedImage(ancho, alto, BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D gCirculo = circulo.createGraphics();
        gCirculo.setColor(Color.WHITE);
        gCirculo.fillOval(50, 50, 300, 300);
        gCirculo.dispose();

        Graphics2D gCuadrado = cuadrado.createGraphics();
        gCuadrado.setColor(Color.WHITE);
        gCuadrado.fillRect(75, 75, 250, 250);
        gCuadrado.dispose();

        for(int x = 0; x < ancho; x++) {
            for(int y = 0; y < alto; y++) {
                int pixel1 = circulo.getRaster().getSample(x, y, 0);
                int pixel2 = cuadrado.getRaster().getSample(x, y, 0);

                int pixelOr = pixel1 | pixel2;
                int pixelXor = pixel1 ^ pixel2;
                int pixelAnd = pixel1 & pixel2;

                resultadoOr.getRaster().setSample(x, y, 0, pixelOr);
                resultadoXor.getRaster().setSample(x, y, 0, pixelXor);
                resultadoAnd.getRaster().setSample(x, y, 0, pixelAnd);
            }
        }

        JFrame frame = new JFrame("Imagenes originales");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));

        frame.add(new JLabel(new ImageIcon(circulo)));
        frame.add(new JLabel(new ImageIcon(cuadrado)));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JFrame frame2 = new JFrame("Operadores");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(new GridLayout(1, 3));

        frame2.add(new JLabel(new ImageIcon(resultadoOr)));
        frame2.add(new JLabel(new ImageIcon(resultadoXor)));
        frame2.add(new JLabel(new ImageIcon(resultadoAnd)));

        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }
}