import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Histograma {
    public static void main(String []args) {
        Image image = HerramientasImagen.abrirImagen();

        if(image == null) {
            System.out.print("Operacion cancelada.");
            return;
        }

        BufferedImage image_aux = HerramientasImagen.toBufferedImage(image);

        int ancho = image_aux.getWidth();
        int alto = image_aux.getHeight();

        int[] rojos = new int[256];
        int[] verdes = new int[256];
        int[] azules = new int[256];

        Color aux;

        for(int x = 0; x < ancho; x++) {
            for(int y = 0; y< alto; y++) {
                int rgb = image_aux.getRGB(x, y);
                aux = new Color(rgb);

                int r = aux.getRed();
                rojos[r] += 1;

                int g = aux.getGreen();
                verdes[g] += 1;

                int b = aux.getBlue();
                azules[b] += 1;
            }
        }

        JFrame ventana = new JFrame("Histograma");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new PanelHistograma(rojos, verdes, azules));
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}