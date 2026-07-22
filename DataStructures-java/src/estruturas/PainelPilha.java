package estruturas;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class PainelPilha extends JPanel {

    private Stack<Integer> pilha;

    public PainelPilha(Stack<Integer> pilha) {
        this.pilha = pilha;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400,400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int largura = 80;
        int altura = 40;

        int x = getWidth()/2 - largura/2;
        int yBase = getHeight() - 60;

        for(int i = 0; i < pilha.size(); i++) {

            int valor = pilha.get(i);

            int y = yBase - (i * altura);

            g.setColor(new Color(120,180,255));
            g.fillRect(x, y, largura, altura);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, largura, altura);

            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(String.valueOf(valor), x + 30, y + 25);
        }

        if(!pilha.isEmpty()) {

            int topoY = yBase - ((pilha.size()-1) * altura);

            g.setColor(Color.RED);
            g.drawString("TOPO", x + largura + 10, topoY + 25);
        }
    }
}