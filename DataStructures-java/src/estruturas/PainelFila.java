package estruturas;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PainelFila extends JPanel {

    private LinkedList<Integer> fila;

    public PainelFila(LinkedList<Integer> fila) {
        this.fila = fila;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500,300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int largura = 60;
        int altura = 40;

        int x = 50;
        int y = getHeight()/2;

        for(int i = 0; i < fila.size(); i++) {

            int valor = fila.get(i);

            g.setColor(new Color(150,220,150));
            g.fillRect(x, y, largura, altura);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, largura, altura);

            g.drawString(String.valueOf(valor), x+25, y+25);

            x += largura + 10;
        }

        if(!fila.isEmpty()) {

            g.setColor(Color.RED);
            g.drawString("INÍCIO", 50, y-10);

            g.drawString("FIM", x-60, y+70);
        }
    }
}