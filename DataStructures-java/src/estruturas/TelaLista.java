package estruturas;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class TelaLista extends JPanel {

    private LinkedList<Integer> lista;

    private JPanel painelLista;
    private JTextField campoValor;
    private JLabel labelTamanho;
    private JTextArea historico;

    public TelaLista(){

        lista = new LinkedList<>();

        setLayout(new BorderLayout());

        //---------------------------------
        // CONTROLES
        //---------------------------------

        JPanel painelControles = new JPanel();

        campoValor = new JTextField(6);

        JButton btnInserir = new JButton("Inserir");
        JButton btnRemover = new JButton("Remover");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnIsEmpty = new JButton("IsEmpty");
        JButton btnLimpar = new JButton("Limpar");

        painelControles.add(new JLabel("Valor:"));
        painelControles.add(campoValor);
        painelControles.add(btnInserir);
        painelControles.add(btnRemover);
        painelControles.add(btnBuscar);
        painelControles.add(btnIsEmpty);
        painelControles.add(btnLimpar);

        add(painelControles, BorderLayout.NORTH);

        //---------------------------------
        // VISUALIZAÇÃO DA LISTA
        //---------------------------------

        painelLista = new JPanel(new FlowLayout(FlowLayout.LEFT,15,25));

        painelLista.setBorder(
                BorderFactory.createTitledBorder("Visualização da Lista")
        );

        JScrollPane scroll = new JScrollPane(painelLista);

        add(scroll, BorderLayout.CENTER);

        //---------------------------------
        // HISTÓRICO
        //---------------------------------

        JPanel painelDireita = new JPanel(new BorderLayout());

        painelDireita.setPreferredSize(new Dimension(220,0));

        labelTamanho = new JLabel("Tamanho: 0");

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

        //---------------------------------
        // AÇÕES
        //---------------------------------

        btnInserir.addActionListener(e -> inserir());
        btnRemover.addActionListener(e -> remover());
        btnBuscar.addActionListener(e -> buscar());
        btnIsEmpty.addActionListener(e -> isEmpty());
        btnLimpar.addActionListener(e -> limpar());

        campoValor.addActionListener(e -> inserir());

    }

    //---------------------------------
    // INSERIR
    //---------------------------------

    private void inserir(){

        try{

            int valor = Integer.parseInt(campoValor.getText());

            lista.add(valor);

            historico.append("Insert: " + valor + "\n");

            campoValor.setText("");

            atualizarVisual();

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,"Digite um número válido");

        }

    }

    //---------------------------------
    // REMOVER
    //---------------------------------

    private void remover(){

        try{

            int valor = Integer.parseInt(campoValor.getText());

            if(lista.remove((Integer)valor)){

                historico.append("Remove: " + valor + "\n");

            }else{

                JOptionPane.showMessageDialog(this,"Valor não encontrado");

            }

            atualizarVisual();

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,"Digite um número válido");

        }

    }

    //---------------------------------
    // BUSCAR
    //---------------------------------

    private void buscar(){

        try{

            int valor = Integer.parseInt(campoValor.getText());

            if(lista.contains(valor)){

                JOptionPane.showMessageDialog(this,"Valor encontrado na lista");

                historico.append("Search: " + valor + " (encontrado)\n");

            }else{

                JOptionPane.showMessageDialog(this,"Valor NÃO encontrado");

                historico.append("Search: " + valor + " (não encontrado)\n");

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,"Digite um número válido");

        }

    }

    //---------------------------------
    // ISEMPTY
    //---------------------------------

    private void isEmpty(){

        boolean vazio = lista.isEmpty();

        JOptionPane.showMessageDialog(this,
                vazio ? "Lista vazia" : "Lista NÃO está vazia");

        historico.append("IsEmpty: " + vazio + "\n");

    }

    //---------------------------------
    // LIMPAR
    //---------------------------------

    private void limpar(){

        lista.clear();

        historico.append("Lista limpa\n");

        atualizarVisual();

    }

    //---------------------------------
    // ATUALIZA VISUAL
    //---------------------------------

    private void atualizarVisual(){

        painelLista.removeAll();

        int index = 0;

        for(Integer valor : lista){

            //---------------------------------
            // BLOCO DO NÓ
            //---------------------------------

            JPanel bloco = new JPanel(new BorderLayout());

            JLabel numero = new JLabel(String.valueOf(valor),JLabel.CENTER);

            numero.setFont(new Font("Arial",Font.BOLD,18));

            bloco.setPreferredSize(new Dimension(70,60));

            bloco.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            bloco.setBackground(new Color(200,220,255));

            bloco.setOpaque(true);

            bloco.add(numero,BorderLayout.CENTER);

            //---------------------------------
            // HEAD
            //---------------------------------

            if(index == 0){

                JLabel head = new JLabel("HEAD",JLabel.CENTER);

                head.setFont(new Font("Arial",Font.BOLD,10));

                bloco.add(head,BorderLayout.NORTH);

            }

            painelLista.add(bloco);

            //---------------------------------
            // SETA
            //---------------------------------

            if(index < lista.size()-1){

                JLabel seta = new JLabel("→");

                seta.setFont(new Font("Arial",Font.BOLD,22));

                painelLista.add(seta);

            }

            index++;

        }

        //---------------------------------
        // TAMANHO
        //---------------------------------

        labelTamanho.setText("Tamanho: " + lista.size());

        painelLista.revalidate();
        painelLista.repaint();

    }

}

