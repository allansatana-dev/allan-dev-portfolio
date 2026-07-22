package estruturas;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PainelLista extends JPanel {

    private LinkedList<Integer> lista;

    public PainelLista(LinkedList<Integer> lista) {

        this.lista = lista;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600,300));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int largura = 60;
        int altura = 40;

        int x = 50;
        int y = getHeight()/2;

        for(int i = 0; i < lista.size(); i++) {

            int valor = lista.get(i);

            g.setColor(new Color(255,200,120));
            g.fillRect(x,y,largura,altura);

            g.setColor(Color.BLACK);
            g.drawRect(x,y,largura,altura);

            g.drawString(String.valueOf(valor), x+25, y+25);

            if(i < lista.size()-1) {

                g.drawString("→", x+largura+5, y+25);
            }

            x += largura + 40;
        }

        if(!lista.isEmpty()) {

            g.drawString("null", x, y+25);
        }
    }
}