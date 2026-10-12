import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Traslacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ancho, alto;
        BufferedImage imagen;
        BufferedImage imagenTrasladada;

        System.out.println("¿Ingresar imagen? (S/N): ");
        String opcion = scanner.nextLine();

        if(opcion.equals("s")) {
            Image aux = HerramientasImagen.abrirImagen();

            if(aux == null) {
                System.out.println("Operacion cancelada");
                return;
            }

            imagen = HerramientasImagen.toBufferedImage(aux);

            ancho = imagen.getWidth();
            alto = imagen.getHeight();

            imagenTrasladada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        }
        else {
            ancho = 400;
            alto = 400;

            imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
            imagenTrasladada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

            Graphics2D g = imagen.createGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, ancho, alto);

            g.setColor(Color.WHITE);
            g.fillRect(150, 150, 100, 100);
            g.dispose();

            Graphics2D g2 = imagenTrasladada.createGraphics();
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, ancho, alto);
            g2.dispose();
        }

        int tx = 50;
        int ty = 50;

        for(int x = 0; x < ancho; x++) {
            for(int y = 0; y < alto; y++) {
                int newx = x + tx;
                int newy = y + ty;

                if(newx >= 0 && newx < ancho && newy >= 0 && newy < alto) {
                    int rgb = imagen.getRGB(x, y);
                    imagenTrasladada.setRGB(newx, newy, rgb);
                }
            }
        }

        JFrame ventana = new JFrame("Traslacion");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(1, 2));

        ventana.add(new JLabel(new ImageIcon(imagen)));
        ventana.add(new JLabel(new ImageIcon(imagenTrasladada)));

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}