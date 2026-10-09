import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Umbralizacion {
    public static void main(String[] args) {
        Image image = HerramientasImagen.abrirImagen();

        if(image == null) {
            System.out.println("Operacion cancelada");
            return;
        }

        BufferedImage bufferedImage = HerramientasImagen.toBufferedImage(image);

        int ancho = bufferedImage.getWidth();
        int alto = bufferedImage.getHeight();

        BufferedImage resultadoGrises = new BufferedImage(
                ancho, alto, BufferedImage.TYPE_INT_RGB
        );

        BufferedImage resultadoUmbralizacion = new BufferedImage(
                ancho, alto, BufferedImage.TYPE_INT_RGB
        );

        int k = 150;

        Color aux;

        for(int x = 0; x < ancho; x++) {
            for(int y = 0; y < alto; y++) {
                int rgb = bufferedImage.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int gris = (int) Math.round(0.299 * r + 0.587 * g + 0.114 * b);
                int umbral = gris > k ? 255 : 0;

                int rgbGris = (gris << 16) | (gris << 8) | gris;
                int rgbUmbral = (umbral << 16) | (umbral << 8) | umbral;

                resultadoGrises.setRGB(x, y, rgbGris);
                resultadoUmbralizacion.setRGB(x, y, rgbUmbral);
            }
        }

        JFrame frame = new JFrame("Escala de Grises");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 3));

        frame.add(new JLabel(new ImageIcon(bufferedImage)));
        frame.add(new JLabel(new ImageIcon(resultadoGrises)));
        frame.add(new JLabel(new ImageIcon(resultadoUmbralizacion)));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}