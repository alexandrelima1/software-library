package view;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Software;

public class MainWindow extends JFrame {

    private JPanel notificationPanel; // Painel de notificações

    public MainWindow() {
        // Aplicar o tema FlatLaf
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 15); // Arredondar botões

        setTitle("Biblioteca de Softwares");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Exemplo de lista de softwares com datas formatadas
        List<Software> softwareList = new ArrayList<>();
        softwareList.add(new Software("Windows 10", "Licenciado", "Perpétua", "TI", LocalDate.of(2021, 5, 10), null, "ABC123", "Usuário1", "Sistema Operacional"));
        softwareList.add(new Software("Microsoft Office", "Licenciado", "Anual", "Financeiro", LocalDate.of(2022, 7, 12), LocalDate.of(2024, 10, 25), "DEF456", "Usuário2", "Pacote Office"));
        softwareList.add(new Software("Antivirus XYZ", "Licenciado", "Anual", "TI", LocalDate.of(2023, 1, 1), LocalDate.of(2024, 10, 15), "XYZ789", "Usuário3", "Antivírus"));

        // Painel de navegação
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton softwareButton = new JButton("Gerenciar Softwares");
        softwareButton.setPreferredSize(new Dimension(200, 40)); // Tamanho maior e uniforme
        softwareButton.addActionListener(e -> {
            SoftwareView softwareView = new SoftwareView(softwareList);
            softwareView.setVisible(true);
        });

        navPanel.add(softwareButton);
        add(navPanel, BorderLayout.NORTH);

        // Painel de notificações
        notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        notificationPanel.setBackground(new Color(245, 245, 245)); // Fundo mais claro e suave
        notificationPanel.setBorder(BorderFactory.createTitledBorder("Notificações"));
        add(notificationPanel, BorderLayout.SOUTH);

        // Verificar vencimentos próximos ao iniciar
        verificarVencimentosProximos(softwareList);
    }

    // Método para verificar vencimentos próximos e exibir notificações dentro da interface
    private void verificarVencimentosProximos(List<Software> softwares) {
        LocalDate hoje = LocalDate.now();

        // Filtrar softwares com vencimento nos próximos 30 dias
        List<Software> vencendoEmBreve = softwares.stream()
                .filter(software -> software.getDataVencimento() != null &&
                        software.getDataVencimento().isAfter(hoje) &&
                        software.getDataVencimento().isBefore(hoje.plusDays(15)))
                .collect(Collectors.toList());

        // Se houver softwares prestes a vencer, mostrar as notificações
        if (!vencendoEmBreve.isEmpty()) {
            for (Software software : vencendoEmBreve) {
                JLabel notificationLabel = new JLabel(
                        "⚠️ O software " + software.getNome() + " está prestes a vencer em " + software.getDataVencimentoFormatada() + ".");
                notificationLabel.setForeground(new Color(0xD32F2F)); // Cor vermelha para alertas
                notificationLabel.setFont(new Font("SansSerif", Font.BOLD, 12)); // Fonte profissional
                notificationPanel.add(notificationLabel);
            }

            // Atualiza a interface
            notificationPanel.revalidate();
            notificationPanel.repaint();
        } else {
            JLabel noNotificationLabel = new JLabel("Nenhum software com vencimento próximo.");
            noNotificationLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            notificationPanel.add(noNotificationLabel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
