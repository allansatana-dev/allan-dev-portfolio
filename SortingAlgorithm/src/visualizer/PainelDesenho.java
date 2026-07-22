package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PainelDesenho extends JPanel {

    int[] array;
    int atual = -1;
    int comparando = -1;

    private boolean pausado = false;
    private int velocidade = 20;

    public PainelDesenho() {
        gerarArray(20);
        setBackground(Color.BLACK);
    }

    public void gerarArray(int tamanho) {
        array = new int[tamanho];
        Random r = new Random();

        for (int i = 0; i < tamanho; i++) {
            array[i] = r.nextInt(300) + 10;
        }

        repaint();
    }

    public void setArrayManual(int[] novoArray) {
        array = novoArray;
        repaint();
    }

    public void resetar() {
        gerarArray(array.length);
    }

    public void pausarOuContinuar() {
        pausado = !pausado;
    }

    public void atualizar(int i, int j) {
        atual = i;
        comparando = j;
        repaint();

        try {
            Thread.sleep(velocidade);
            while (pausado) Thread.sleep(50);
        } catch (Exception e) {}
    }

    public void iniciarOrdenacao(String algoritmo, Tela tela) {

        new Thread(() -> {

            long inicio = System.currentTimeMillis();

            switch (algoritmo) {
                case "Bubble Sort": Algoritmos.bubbleSort(array, this); break;
                case "Selection Sort": Algoritmos.selectionSort(array, this); break;
                case "Insertion Sort": Algoritmos.insertionSort(array, this); break;
                case "Quick Sort": Algoritmos.quickSort(array,0,array.length-1,this); break;
                case "Merge Sort": Algoritmos.mergeSort(array,0,array.length-1,this); break;
            }

            long fim = System.currentTimeMillis();
            long tempo = fim - inicio;

            tela.adicionarHistorico(algoritmo, tempo);

        }).start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (array == null) return;

        int largura = getWidth() / array.length;

        int max = 1;
        for (int v : array) if (v > max) max = v;

        for (int i = 0; i < array.length; i++) {

            int altura = (int)((array[i] / (double) max) * (getHeight() - 40));

            if (i == atual)
                g.setColor(Color.RED);
            else if (i == comparando)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.CYAN);

            g.fillRect(i * largura, getHeight() - altura, largura - 2, altura);

            // 🔥 VALOR NA BARRA
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(String.valueOf(array[i]),
                    i * largura + 5,
                    getHeight() - altura - 5);
        }
    }
}