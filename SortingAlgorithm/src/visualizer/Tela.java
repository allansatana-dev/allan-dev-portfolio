package visualizer;

import javax.swing.*;
import java.awt.*;

public class Tela extends JFrame {

    PainelDesenho painel;
    JTextArea infoArea;

    String algoritmoAtual = "Bubble Sort";

    public Tela() {

        setTitle("Sorting Visualizer PRO");
        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        getContentPane().setBackground(new Color(30,30,30));

        JLabel titulo = new JLabel("SORTING VISUALIZER", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        add(titulo, BorderLayout.NORTH);

        painel = new PainelDesenho();
        add(painel, BorderLayout.CENTER);

        // ===== ESQUERDA =====
        JPanel esquerda = new JPanel(new GridLayout(0,1,10,10));
        esquerda.setPreferredSize(new Dimension(280,0));
        esquerda.setBackground(new Color(40,40,40));
        esquerda.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JComboBox<String> algoritmoBox = new JComboBox<>(new String[]{
                "Bubble Sort","Selection Sort","Insertion Sort","Quick Sort","Merge Sort"
        });

        JButton iniciar = new JButton("▶ Iniciar");
        JButton pausar = new JButton("⏸ Pausar");
        JButton resetar = new JButton("⟲ Resetar");
        JButton limparHistorico = new JButton("🧹 Limpar Histórico");

        JSlider tamanhoArray = new JSlider(5, 80, 20);

        JTextField campoArray = new JTextField();
        JButton aplicar = new JButton("Aplicar");

        esquerda.add(new JLabel("Algoritmo"));
        esquerda.add(algoritmoBox);


        esquerda.add(iniciar);
        esquerda.add(pausar);
        esquerda.add(resetar);
        esquerda.add(limparHistorico);
        esquerda.add(new JLabel("Tamanho"));
        esquerda.add(tamanhoArray);
        esquerda.add(new JLabel("Array manual"));
        esquerda.add(campoArray);
        esquerda.add(aplicar);

        add(esquerda, BorderLayout.WEST);

        // ===== DIREITA =====
        JPanel direita = new JPanel(new BorderLayout());
        direita.setPreferredSize(new Dimension(320,0));
        direita.setBackground(new Color(35,35,35));

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setBackground(new Color(35,35,35));
        infoArea.setForeground(Color.LIGHT_GRAY);
        infoArea.setFont(new Font("Consolas", Font.PLAIN, 13));

        direita.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        add(direita, BorderLayout.EAST);

        // ===== EVENTOS =====

        algoritmoBox.addActionListener(e -> {
            algoritmoAtual = (String) algoritmoBox.getSelectedItem();
            atualizarInfoBase();
        });

        iniciar.addActionListener(e ->
                painel.iniciarOrdenacao(algoritmoAtual, this));

        pausar.addActionListener(e -> painel.pausarOuContinuar());
        resetar.addActionListener(e -> painel.resetar());

        limparHistorico.addActionListener(e -> atualizarInfoBase());

        tamanhoArray.addChangeListener(e ->
                painel.gerarArray(tamanhoArray.getValue()));

        aplicar.addActionListener(e -> {
            try {
                String[] partes = campoArray.getText().split(",");
                int[] valores = new int[partes.length];

                for (int i = 0; i < partes.length; i++) {
                    valores[i] = Integer.parseInt(partes[i].trim());
                }

                painel.setArrayManual(valores);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Formato: 5,3,8");
            }
        });

        atualizarInfoBase();

        setVisible(true);
    }

    // 🔥 INFORMAÇÕES BASE (COMPLEXIDADE)
    private void atualizarInfoBase() {

        String texto = "";

        switch (algoritmoAtual) {

            case "Bubble Sort":
                texto = "Bubble Sort\n\nMelhor: O(n)\nMédio: O(n²)\nPior: O(n²)\n\n";
                break;

            case "Selection Sort":
                texto = "Selection Sort\n\nMelhor: O(n²)\nMédio: O(n²)\nPior: O(n²)\n\n";
                break;

            case "Insertion Sort":
                texto = "Insertion Sort\n\nMelhor: O(n)\nMédio: O(n²)\nPior: O(n²)\n\n";
                break;

            case "Quick Sort":
                texto = "Quick Sort\n\nMelhor: O(n log n)\nMédio: O(n log n)\nPior: O(n²)\n\n";
                break;

            case "Merge Sort":
                texto = "Merge Sort\n\nMelhor: O(n log n)\nMédio: O(n log n)\nPior: O(n log n)\n\n";
                break;
        }

        texto += "------------------------\nHISTÓRICO\n\n";

        infoArea.setText(texto);
    }

    // 🔥 ADICIONAR HISTÓRICO
    public void adicionarHistorico(String algoritmo, long tempo) {

        SwingUtilities.invokeLater(() -> {
            infoArea.append("Algoritmo: " + algoritmo + "\n");
            infoArea.append("Tempo: " + tempo + " ms\n");
            infoArea.append("------------------------\n");
        });
    }
}