import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ModificarImagen {
    public static void main(String[] args) {
        // Cargar una imagen
        Image imagen = HerramientasImagen.abrirImagen();

        if(imagen == null) {
            System.out.print("Operacion cancelada.");
            return;
        }

        // Convertir la imagen a BufferedImage (copias modificables)
        BufferedImage aux = HerramientasImagen.toBufferedImage(imagen);
        BufferedImage aux2 = HerramientasImagen.toBufferedImage(imagen);

        int ancho = aux.getWidth();
        int alto = aux.getHeight();

        System.out.println("Ancho: " + ancho);
        System.out.println("Alto: " + alto);

        // Crear una variable para almacenar el color verde
        Color color = new Color(180, 240, 20);
        int colorRGB = color.getRGB();

        // Llenar de verde un area 20 x 20
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(i < ancho && j < alto) {
                    aux.setRGB(i, j, colorRGB);
                }
            }
        }

        // Crear variables para almacenar los colores de la bandera de Mexico
        Color verde = new Color(0, 255, 0);
        Color blanco = new Color(255, 255, 255);
        Color rojo = new Color(255, 0, 0);
        int verdeRGB = verde.getRGB();
        int blancoRGB = blanco.getRGB();
        int rojoRGB = rojo.getRGB();

        // Dibujar la bandera de Mexico (waos)
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 30; j++) {
                if(i < ancho && j < alto) {
                    if(i < 20) {
                        aux2.setRGB(i, j, verdeRGB);
                    }
                    else if(i < 40 ) {
                        aux2.setRGB(i, j, blancoRGB);
                    }
                    else {
                        aux2.setRGB(i, j, rojoRGB);
                    }
                }
            }
        }

        JFrameImage ventana = new JFrameImage(aux);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        JFrameImage ventana2 = new JFrameImage(aux2);
        ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana2.pack();
        ventana2.setLocationRelativeTo(null);
        ventana2.setVisible(true);
    }
}