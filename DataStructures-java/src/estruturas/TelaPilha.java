package estruturas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class TelaPilha extends JPanel {

    private Stack<Integer> pilha;

    private JPanel painelPilha;
    private JTextField campoValor;
    private JLabel labelTamanho;
    private JTextArea historico;

    private int tamanhoMaximo = 10;

    public TelaPilha() {

        pilha = new Stack<>();

        setLayout(new BorderLayout());

        //----------------------------------
        // PAINEL SUPERIOR (CONTROLES)
        //----------------------------------

        JPanel painelControles = new JPanel();

        campoValor = new JTextField(6);

        JButton btnPush = new JButton("Push");
        JButton btnPop = new JButton("Pop");
        JButton btnPeek = new JButton("Peek");
        JButton btnIsEmpty = new JButton("IsEmpty");
        JButton btnIsFull = new JButton("IsFull");
        JButton btnLimpar = new JButton("Limpar");

        painelControles.add(new JLabel("Valor:"));
        painelControles.add(campoValor);
        painelControles.add(btnPush);
        painelControles.add(btnPop);
        painelControles.add(btnPeek);
        painelControles.add(btnIsEmpty);
        painelControles.add(btnIsFull);
        painelControles.add(btnLimpar);

        add(painelControles, BorderLayout.NORTH);

        //----------------------------------
        // ÁREA CENTRAL (PILHA)
        //----------------------------------

        painelPilha = new JPanel();

        painelPilha.setLayout(new BoxLayout(painelPilha, BoxLayout.Y_AXIS));

        painelPilha.setBorder(
                BorderFactory.createTitledBorder("Visualização da Pilha")
        );

        JScrollPane scroll = new JScrollPane(painelPilha);

        add(scroll, BorderLayout.CENTER);

        //----------------------------------
        // LADO DIREITO (INFO + HISTÓRICO)
        //----------------------------------

        JPanel painelDireita = new JPanel();
        painelDireita.setLayout(new BorderLayout());
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
        // AÇÕES DOS BOTÕES
        //----------------------------------

        btnPush.addActionListener(e -> push());
        btnPop.addActionListener(e -> pop());
        btnPeek.addActionListener(e -> peek());
        btnIsEmpty.addActionListener(e -> isEmpty());
        btnIsFull.addActionListener(e -> isFull());
        btnLimpar.addActionListener(e -> limpar());

        //----------------------------------
        // ENTER = PUSH
        //----------------------------------

        campoValor.addActionListener(e -> push());

    }

    //----------------------------------
    // PUSH
    //----------------------------------

    private void push() {

        if(pilha.size() == tamanhoMaximo){

            JOptionPane.showMessageDialog(this,"Pilha cheia!");
            return;

        }

        try{

            int valor = Integer.parseInt(campoValor.getText());

            pilha.push(valor);

            historico.append("Push: " + valor + "\n");

            campoValor.setText("");

            atualizarVisual();

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,"Digite um número válido");

        }

    }

    //----------------------------------
    // POP
    //----------------------------------

    private void pop(){

        if(pilha.isEmpty()){

            JOptionPane.showMessageDialog(this,"Pilha vazia!");
            return;

        }

        int valor = pilha.pop();

        historico.append("Pop: " + valor + "\n");

        atualizarVisual();

    }

    //----------------------------------
    // PEEK
    //----------------------------------

    private void peek(){

        if(pilha.isEmpty()){

            JOptionPane.showMessageDialog(this,"Pilha vazia!");
            return;

        }

        int topo = pilha.peek();

        JOptionPane.showMessageDialog(this,"Topo da pilha: " + topo);

        historico.append("Peek: " + topo + "\n");

    }

    //----------------------------------
    // ISEMPTY
    //----------------------------------

    private void isEmpty(){

        boolean vazio = pilha.isEmpty();

        JOptionPane.showMessageDialog(this,
                vazio ? "A pilha está vazia" : "A pilha NÃO está vazia");

        historico.append("IsEmpty: " + vazio + "\n");

    }

    //----------------------------------
    // ISFULL
    //----------------------------------

    private void isFull(){

        boolean cheia = pilha.size() == tamanhoMaximo;

        JOptionPane.showMessageDialog(this,
                cheia ? "A pilha está cheia" : "A pilha NÃO está cheia");

        historico.append("IsFull: " + cheia + "\n");

    }

    //----------------------------------
    // LIMPAR
    //----------------------------------

    private void limpar(){

        pilha.clear();

        historico.append("Pilha limpa\n");

        atualizarVisual();

    }

    //----------------------------------
    // ATUALIZA VISUAL
    //----------------------------------

    private void atualizarVisual(){

        painelPilha.removeAll();

        for(int i = pilha.size()-1; i >= 0; i--){

            int valor = pilha.get(i);

            JPanel bloco = new JPanel(new BorderLayout());

            JLabel numero = new JLabel(String.valueOf(valor), JLabel.CENTER);

            numero.setFont(new Font("Arial", Font.BOLD, 18));

            bloco.setPreferredSize(new Dimension(120,50));
            bloco.setMaximumSize(new Dimension(120,50));

            bloco.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            bloco.add(numero, BorderLayout.CENTER);

            //----------------------------------
            // TOPO
            //----------------------------------

            if(i == pilha.size()-1){

                bloco.setBackground(new Color(255,180,80));

                JLabel topo = new JLabel("TOPO", JLabel.CENTER);

                topo.setFont(new Font("Arial",Font.BOLD,12));

                bloco.add(topo, BorderLayout.NORTH);

            }

            else{

                bloco.setBackground(new Color(255,220,150));

            }

            bloco.setOpaque(true);

            painelPilha.add(bloco);

        }

        //----------------------------------
        // TAMANHO
        //----------------------------------

        labelTamanho.setText(
                "Tamanho: " + pilha.size() + " / " + tamanhoMaximo
        );

        painelPilha.revalidate();
        painelPilha.repaint();

    }

}