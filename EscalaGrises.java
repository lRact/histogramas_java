import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EscalaGrises {
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

        Color aux;

        for(int x = 0; x < ancho; x++) {
            for(int y = 0; y < alto; y++) {
                int rgb = bufferedImage.getRGB(x, y);
                aux = new Color(rgb);

                int r = aux.getRed();
                int g = aux.getGreen();
                int b = aux.getBlue();

                int gris = (int) Math.round(0.299 * r + 0.587 * g + 0.114 * b);

                Color nuevo = new Color(gris, gris, gris);

                resultado.setRGB(x, y, nuevo.getRGB());
            }
        }

        JFrame frame = new JFrame("Escala de Grises");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));

        frame.add(new JLabel(new ImageIcon(resultado)));
        frame.add(new JLabel(new ImageIcon(bufferedImage)));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}