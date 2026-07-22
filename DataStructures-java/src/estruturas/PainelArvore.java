package estruturas;

import javax.swing.*;
import java.awt.*;

public class PainelArvore extends JPanel {

    private NoArvore raiz;

    public PainelArvore(NoArvore raiz) {

        this.raiz = raiz;

        setBackground(Color.WHITE);

        // garante espaço para desenhar
        setPreferredSize(new Dimension(800,500));
    }

    public void atualizarRaiz(NoArvore raiz) {

        this.raiz = raiz;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if(raiz != null) {

            desenharArvore(g, raiz, getWidth()/2, 50, getWidth()/4);

        }

    }

    private void desenharArvore(Graphics g, NoArvore no, int x, int y, int espaco) {

        if(no == null) return;

        // desenha o nó
        g.setColor(new Color(180,140,255));
        g.fillOval(x-20, y-20, 40, 40);

        g.setColor(Color.BLACK);
        g.drawOval(x-20, y-20, 40, 40);

        g.drawString(String.valueOf(no.valor), x-6, y+5);

        // desenha filho esquerdo
        if(no.esquerda != null) {

            int novoX = x - espaco;
            int novoY = y + 80;

            g.drawLine(x, y, novoX, novoY);

            desenharArvore(g, no.esquerda, novoX, novoY, espaco/2);
        }

        // desenha filho direito
        if(no.direita != null) {

            int novoX = x + espaco;
            int novoY = y + 80;

            g.drawLine(x, y, novoX, novoY);

            desenharArvore(g, no.direita, novoX, novoY, espaco/2);
        }
    }
}