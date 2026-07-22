package estruturas;

import javax.swing.*;
import java.awt.*;

public class TelaArvore extends JPanel {

    class No {

        int valor;
        No esquerda;
        No direita;

        No(int valor){
            this.valor = valor;
        }

    }

    private No raiz;

    private JTextField campoValor;
    private JTextArea historico;
    private JLabel labelTamanho;

    private PainelDesenho painelDesenho;

    private int quantidade = 0;

    public TelaArvore(){

        setLayout(new BorderLayout());

        //----------------------------------
        // CONTROLES
        //----------------------------------

        JPanel painelControles = new JPanel();

        campoValor = new JTextField(6);

        JButton btnInserir = new JButton("Inserir");
        JButton btnLimpar = new JButton("Limpar");

        painelControles.add(new JLabel("Valor:"));
        painelControles.add(campoValor);
        painelControles.add(btnInserir);
        painelControles.add(btnLimpar);

        add(painelControles,BorderLayout.NORTH);

        //----------------------------------
        // ÁREA DE DESENHO
        //----------------------------------

        painelDesenho = new PainelDesenho();

        painelDesenho.setBorder(
                BorderFactory.createTitledBorder("Visualização da Árvore")
        );

        add(painelDesenho,BorderLayout.CENTER);

        //----------------------------------
        // HISTÓRICO
        //----------------------------------

        JPanel painelDireita = new JPanel(new BorderLayout());

        painelDireita.setPreferredSize(new Dimension(220,0));

        labelTamanho = new JLabel("Nós: 0");

        labelTamanho.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        historico = new JTextArea();
        historico.setEditable(false);

        JScrollPane scrollHistorico = new JScrollPane(historico);

        painelDireita.add(labelTamanho,BorderLayout.NORTH);
        painelDireita.add(scrollHistorico,BorderLayout.CENTER);

        painelDireita.setBorder(
                BorderFactory.createTitledBorder("Histórico")
        );

        add(painelDireita,BorderLayout.EAST);

        //----------------------------------
        // AÇÕES
        //----------------------------------

        btnInserir.addActionListener(e -> inserir());

        btnLimpar.addActionListener(e -> limpar());

        campoValor.addActionListener(e -> inserir());

    }

    //----------------------------------
    // INSERIR
    //----------------------------------

    private void inserir(){

        try{

            int valor = Integer.parseInt(campoValor.getText());

            raiz = inserirRec(raiz,valor);

            quantidade++;

            historico.append("Insert: " + valor + "\n");

            campoValor.setText("");

            atualizar();

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,"Digite um número válido");

        }

    }

    private No inserirRec(No atual,int valor){

        if(atual == null){
            return new No(valor);
        }

        if(valor < atual.valor){
            atual.esquerda = inserirRec(atual.esquerda,valor);
        }
        else if(valor > atual.valor){
            atual.direita = inserirRec(atual.direita,valor);
        }

        return atual;

    }

    //----------------------------------
    // LIMPAR
    //----------------------------------

    private void limpar(){

        raiz = null;

        quantidade = 0;

        historico.append("Árvore limpa\n");

        atualizar();

    }

    //----------------------------------
    // ATUALIZA
    //----------------------------------

    private void atualizar(){

        labelTamanho.setText("Nós: " + quantidade);

        painelDesenho.repaint();

    }

    //----------------------------------
    // PAINEL DE DESENHO
    //----------------------------------

    class PainelDesenho extends JPanel{

        protected void paintComponent(Graphics g){

            super.paintComponent(g);

            if(raiz != null){

                desenhar(g,raiz,getWidth()/2,40,getWidth()/4);

            }

        }

        private void desenhar(Graphics g,No no,int x,int y,int espacamento){

            g.setColor(Color.BLACK);

            //----------------------------------
            // FILHO ESQUERDA
            //----------------------------------

            if(no.esquerda != null){

                g.drawLine(x,y,x-espacamento,y+60);

                desenhar(g,no.esquerda,x-espacamento,y+60,espacamento/2);

            }

            //----------------------------------
            // FILHO DIREITA
            //----------------------------------

            if(no.direita != null){

                g.drawLine(x,y,x+espacamento,y+60);

                desenhar(g,no.direita,x+espacamento,y+60,espacamento/2);

            }

            //----------------------------------
            // NÓ
            //----------------------------------

            g.setColor(new Color(180,220,255));

            g.fillOval(x-20,y-20,40,40);

            g.setColor(Color.BLACK);

            g.drawOval(x-20,y-20,40,40);

            String valor = String.valueOf(no.valor);

            FontMetrics fm = g.getFontMetrics();

            int largura = fm.stringWidth(valor);

            g.drawString(valor,x-largura/2,y+5);

        }

    }

}