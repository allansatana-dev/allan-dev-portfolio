package estruturas;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class TelaFila extends JPanel {

    private Queue<Integer> fila;

    private JPanel painelFila;
    private JTextField campoValor;
    private JLabel labelTamanho;
    private JTextArea historico;

    private int tamanhoMaximo = 10;

    public TelaFila() {

        fila = new LinkedList<>();

        setLayout(new BorderLayout());

        //----------------------------------
        // CONTROLES
        //----------------------------------

        JPanel painelControles = new JPanel();

        campoValor = new JTextField(6);

        JButton btnEnqueue = new JButton("Enqueue");
        JButton btnDequeue = new JButton("Dequeue");
        JButton btnPeek = new JButton("Peek");
        JButton btnIsEmpty = new JButton("IsEmpty");
        JButton btnIsFull = new JButton("IsFull");
        JButton btnLimpar = new JButton("Limpar");

        painelControles.add(new JLabel("Valor:"));
        painelControles.add(campoValor);
        painelControles.add(btnEnqueue);
        painelControles.add(btnDequeue);
        painelControles.add(btnPeek);
        painelControles.add(btnIsEmpty);
        painelControles.add(btnIsFull);
        painelControles.add(btnLimpar);

        add(painelControles, BorderLayout.NORTH);

        //----------------------------------
        // VISUALIZAÇÃO DA FILA
        //----------------------------------

        painelFila = new JPanel();

        painelFila.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));

        painelFila.setBorder(
                BorderFactory.createTitledBorder("Visualização da Fila")
        );

        JScrollPane scroll = new JScrollPane(painelFila);

        add(scroll, BorderLayout.CENTER);

        //----------------------------------
        // HISTÓRICO
        //----------------------------------

        JPanel painelDireita = new JPanel(new BorderLayout());
        painelDireita.setPreferredSize(new Dimension(220,0));

        labelTamanho = new JLabel("Tamanho: 0 / " + tamanhoMaximo);
        labelTamanho.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        historico = new JTextArea();
        historico.setEditable(false);

        JScrollPane scrollHistorico = new JScrollPane(historico);

        painelDireita.add(labelTamanho, BorderLayout.NORTH);
        painelDireita.add(scrollHistorico, BorderLayout.CENTER);

        painelDireita.setBorder(
                BorderFactory.createTitledBorder("Histórico")
        );

        add(painelDireita, BorderLayout.EAST);

        //----------------------------------
        // AÇÕES
        //----------------------------------

        btnEnqueue.addActionListener(e -> enqueue());
        btnDequeue.addActionListener(e -> dequeue());
        btnPeek.addActionListener(e -> peek());
        btnIsEmpty.addActionListener(e -> isEmpty());
        btnIsFull.addActionListener(e -> isFull());
        btnLimpar.addActionListener(e -> limpar());

        campoValor.addActionListener(e -> enqueue());

    }

    //----------------------------------
    // ENQUEUE
    //----------------------------------

    private void enqueue(){

        if(fila.size() == tamanhoMaximo){

            JOptionPane.showMessageDialog(this,"Fila cheia!");
            return;

        }

        try{

            int valor = Integer.parseInt(campoValor.getText());

            fila.add(valor);

            historico.append("Enqueue: " + valor + "\n");

            campoValor.setText("");

            atualizarVisual();

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,"Digite um número válido");

        }

    }

    //----------------------------------
    // DEQUEUE
    //----------------------------------

    private void dequeue(){

        if(fila.isEmpty()){

            JOptionPane.showMessageDialog(this,"Fila vazia!");
            return;

        }

        int valor = fila.poll();

        historico.append("Dequeue: " + valor + "\n");

        atualizarVisual();

    }

    //----------------------------------
    // PEEK
    //----------------------------------

    private void peek(){

        if(fila.isEmpty()){

            JOptionPane.showMessageDialog(this,"Fila vazia!");
            return;

        }

        int valor = fila.peek();

        JOptionPane.showMessageDialog(this,"Primeiro da fila: " + valor);

        historico.append("Peek: " + valor + "\n");

    }

    //----------------------------------
    // ISEMPTY
    //----------------------------------

    private void isEmpty(){

        boolean vazio = fila.isEmpty();

        JOptionPane.showMessageDialog(this,
                vazio ? "A fila está vazia" : "A fila NÃO está vazia");

        historico.append("IsEmpty: " + vazio + "\n");

    }

    //----------------------------------
    // ISFULL
    //----------------------------------

    private void isFull(){

        boolean cheia = fila.size() == tamanhoMaximo;

        JOptionPane.showMessageDialog(this,
                cheia ? "A fila está cheia" : "A fila NÃO está cheia");

        historico.append("IsFull: " + cheia + "\n");

    }

    //----------------------------------
    // LIMPAR
    //----------------------------------

    private void limpar(){

        fila.clear();

        historico.append("Fila limpa\n");

        atualizarVisual();

    }

    //----------------------------------
    // ATUALIZA VISUAL
    //----------------------------------

    private void atualizarVisual(){

        painelFila.removeAll();

        int index = 0;

        for(Integer valor : fila){

            JPanel bloco = new JPanel(new BorderLayout());

            JLabel numero = new JLabel(String.valueOf(valor), JLabel.CENTER);

            numero.setFont(new Font("Arial",Font.BOLD,18));

            bloco.setPreferredSize(new Dimension(70,60));

            bloco.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            bloco.add(numero,BorderLayout.CENTER);

            //----------------------------------
            // INICIO
            //----------------------------------

            if(index == 0){

                JLabel inicio = new JLabel("INÍCIO",JLabel.CENTER);
                inicio.setFont(new Font("Arial",Font.BOLD,10));

                bloco.add(inicio,BorderLayout.NORTH);

                bloco.setBackground(new Color(150,220,150));

            }

            //----------------------------------
            // FIM
            //----------------------------------

            else if(index == fila.size()-1){

                JLabel fim = new JLabel("FIM",JLabel.CENTER);
                fim.setFont(new Font("Arial",Font.BOLD,10));

                bloco.add(fim,BorderLayout.NORTH);

                bloco.setBackground(new Color(255,200,120));

            }

            else{

                bloco.setBackground(new Color(220,220,220));

            }

            bloco.setOpaque(true);

            painelFila.add(bloco);

            index++;

        }

        //----------------------------------
        // TAMANHO
        //----------------------------------

        labelTamanho.setText(
                "Tamanho: " + fila.size() + " / " + tamanhoMaximo
        );

        painelFila.revalidate();
        painelFila.repaint();

    }

}