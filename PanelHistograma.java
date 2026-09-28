import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class PanelHistograma extends JPanel {
    private final int[] rojos;
    private final int[] verdes;
    private final int[] azules;

    public PanelHistograma(int[] rojos, int[] verdes, int[] azules) {
        this.rojos = rojos;
        this.verdes = verdes;
        this.azules = azules;

        setBackground(new Color(24, 24, 27));
        setPreferredSize(new Dimension(600, 380));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = 40;
        int ancho = getWidth() - pad * 2;
        int alto = getHeight() - pad * 2;

        if(ancho <= 0 || alto <= 0) {
            return;
        }

        int maxFrecuencia = 1;
        for (int i = 0; i < 256; i++) {
            if (rojos[i] > maxFrecuencia) maxFrecuencia = rojos[i];
            if (verdes[i] > maxFrecuencia) maxFrecuencia = verdes[i];
            if (azules[i] > maxFrecuencia) maxFrecuencia = azules[i];
        }

        // Marco y cuadrícula base
        g2.setColor(new Color(60, 60, 65));
        g2.drawRect(pad, pad, ancho, alto);

        // Curvas con mezcla semitransparente
        dibujarCurvaCanal(g2, rojos, new Color(248, 113, 113, 190), pad, ancho, alto, maxFrecuencia);
        dibujarCurvaCanal(g2, verdes, new Color(74, 222, 128, 190), pad, ancho, alto, maxFrecuencia);
        dibujarCurvaCanal(g2, azules, new Color(96, 165, 250, 190), pad, ancho, alto, maxFrecuencia);

        // Leyendas de los ejes
        g2.setColor(new Color(160, 160, 160));
        g2.drawString("0", pad, getHeight() - 15);
        g2.drawString("255", pad + ancho - 20, getHeight() - 15);
        g2.drawString("Frecuencia máx: " + maxFrecuencia, pad, pad - 12);
    }

    private void dibujarCurvaCanal(Graphics2D g2, int[] canal, Color color, int pad, int ancho, int alto, int max) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(1.8f));
        Path2D path = new Path2D.Double();

        for (int i = 0; i < 256; i++) {
            double x = pad + ((double) i / 255.0) * ancho;
            double y = (pad + alto) - (((double) canal[i] / max) * alto);

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        g2.draw(path);
    }
}