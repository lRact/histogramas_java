import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ExpansionHistograma {
    public static void main(String[] args) {
        Image image = HerramientasImagen.abrirImagen();

        if(image == null) {
            System.out.println("Operacion cancelada");
            return;
        }

        BufferedImage bufferedImage = HerramientasImagen.toBufferedImage(image);

        int ancho = bufferedImage.getWidth();
        int alto = bufferedImage.getHeight();

        BufferedImage resultado = new BufferedImage(
                ancho, alto, BufferedImage.TYPE_INT_RGB
        );

        int minR = 255, minG = 255, minB = 255;
        int maxR = 0, maxG = 0, maxB = 0;

        for(int x = 0; x < ancho; x++) {
            for(int y = 0; y < alto; y++) {
                Color aux = new Color(bufferedImage.getRGB(x, y));

                int r = aux.getRed();
                int g = aux.getGreen();
                int b = aux.getBlue();

                minR = Math.min(minR, r);
                minG = Math.min(minG, g);
                minB = Math.min(minB, b);

                maxR = Math.max(maxR, r);
                maxG = Math.max(maxG, g);
                maxB = Math.max(maxB, b);
            }
        }

        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < alto; y++) {
                Color aux = new Color(bufferedImage.getRGB(x, y));

                int r = expandir(aux.getRed(), minR, maxR);
                int g = expandir(aux.getGreen(), minG, maxG);
                int b = expandir(aux.getBlue(), minB, maxB);

                Color nuevo = new Color(r, g, b);
                resultado.setRGB(x, y, nuevo.getRGB());
            }
        }

        JFrame ventana = new JFrame("Expansion de histograma");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(1, 2));

        ventana.add(new JLabel(new ImageIcon(bufferedImage)));
        ventana.add(new JLabel(new ImageIcon(resultado)));

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static int expandir(int valor, int min, int max) {
        if(max == min) {
            return valor;
        }

        return (int) Math.round(
                (valor - min) * 255.0 / (max - min)
        );
    }
}