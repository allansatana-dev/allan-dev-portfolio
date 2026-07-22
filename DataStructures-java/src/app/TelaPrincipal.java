package app;

import javax.swing.*;
import java.awt.*;

import estruturas.TelaPilha;
import estruturas.TelaFila;
import estruturas.TelaLista;
import estruturas.TelaArvore;

public class TelaPrincipal extends JFrame {

    private JPanel areaSimulacao;

    private JLabel labelEstrutura;
    private JLabel labelOperacao;
    private JLabel labelDica;

    public TelaPrincipal() {

        setTitle("Simulador de Estruturas de Dados");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== TÍTULO =====

        JLabel titulo = new JLabel("Simulador de Estruturas de Dados", JLabel.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        titulo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        add(titulo, BorderLayout.NORTH);

        // ===== MENU LATERAL =====

        JPanel menu = new JPanel();

        menu.setLayout(new GridLayout(6,1,10,10));

        menu.setPreferredSize(new Dimension(200,0));

        menu.setBackground(new Color(40,40,40));

        menu.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        JButton btnPilha = criarBotao("📚 Pilha");
        JButton btnFila = criarBotao("📥 Fila");
        JButton btnLista = criarBotao("📋 Lista");
        JButton btnArvore = criarBotao("🌳 Árvore");

        menu.add(btnPilha);
        menu.add(btnFila);
        menu.add(btnLista);
        menu.add(btnArvore);

        add(menu, BorderLayout.WEST);

        // ===== ÁREA DE SIMULAÇÃO =====

        areaSimulacao = new JPanel(new BorderLayout());

        JLabel mensagemInicial = new JLabel("Escolha uma estrutura no menu", JLabel.CENTER);

        mensagemInicial.setFont(new Font("Arial", Font.PLAIN, 18));

        areaSimulacao.add(mensagemInicial, BorderLayout.CENTER);

        add(areaSimulacao, BorderLayout.CENTER);

        // ===== PAINEL DE INFORMAÇÕES =====

        JPanel painelInfo = new JPanel(new GridLayout(3,1));

        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações"));

        // altura aumentada
        painelInfo.setPreferredSize(new Dimension(0,170));

        labelEstrutura = new JLabel("Estrutura: -");
        labelOperacao = new JLabel("Operação: -");
        labelDica = new JLabel("Dica: Escolha uma estrutura para começar");

        // fonte maior para melhor leitura
        Font fonteInfo = new Font("Arial", Font.BOLD, 14);

        labelEstrutura.setFont(fonteInfo);
        labelOperacao.setFont(fonteInfo);
        labelDica.setFont(fonteInfo);

        painelInfo.add(labelEstrutura);
        painelInfo.add(labelOperacao);
        painelInfo.add(labelDica);

        add(painelInfo, BorderLayout.SOUTH);

        // ===== AÇÕES DOS BOTÕES =====

        btnPilha.addActionListener(e -> {

            mostrarTela(new TelaPilha());

            labelEstrutura.setText("Estrutura: Pilha (LIFO)");
            labelOperacao.setText("Operação: Push / Pop");
            labelDica.setText("Dica: O último elemento inserido é o primeiro a sair.");

        });

        btnFila.addActionListener(e -> {

            mostrarTela(new TelaFila());

            labelEstrutura.setText("Estrutura: Fila (FIFO)");
            labelOperacao.setText("Operação: Enqueue / Dequeue");
            labelDica.setText("Dica: O primeiro elemento inserido é o primeiro a sair.");

        });

        btnLista.addActionListener(e -> {

            mostrarTela(new TelaLista());

            labelEstrutura.setText("Estrutura: Lista");
            labelOperacao.setText("Operação: Inserir / Remover");
            labelDica.setText("Dica: Listas permitem acessar qualquer posição.");

        });

        btnArvore.addActionListener(e -> {

            mostrarTela(new TelaArvore());

            labelEstrutura.setText("Estrutura: Árvore Binária de Busca");
            labelOperacao.setText("Operação: Inserção");
            labelDica.setText("Dica: Valores menores vão para esquerda e maiores para direita.");

        });

        setVisible(true);
    }

    // ===== CRIAR BOTÃO PADRÃO =====

    private JButton criarBotao(String texto) {

        JButton botao = new JButton(texto);

        botao.setFocusPainted(false);

        botao.setFont(new Font("Arial", Font.BOLD, 16));

        botao.setBackground(new Color(70,130,180));

        botao.setForeground(Color.WHITE);

        return botao;
    }

    // ===== TROCAR TELA =====

    private void mostrarTela(JPanel tela) {

        areaSimulacao.removeAll();

        areaSimulacao.add(tela, BorderLayout.CENTER);

        areaSimulacao.revalidate();
        areaSimulacao.repaint();
    }

}