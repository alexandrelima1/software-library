package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import model.Software;

public class SoftwareView extends JFrame {

    private JTable softwareTable;
    private DefaultTableModel tableModel;

    public SoftwareView(List<Software> softwares) {
        setTitle("Lista de Softwares");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colunas da tabela, incluindo a nova coluna "Ver Mais"
        String[] columnNames = {"Nome", "Licença", "Tipo de Licença", "Unidade", "Data Aquisição", "Data Vencimento", "Patrimônio", "Usuários", "Informações Gerais", "Ações"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Adiciona os softwares à tabela
        for (Software software : softwares) {
            Object[] rowData = {
                    software.getNome(),
                    software.getLicenca(),
                    software.getTipoLicenca(),
                    software.getUnidade(),
                    software.getDataAquisicaoFormatada(),
                    software.getDataVencimentoFormatada(),
                    software.getPatrimonio(),
                    software.getUsuarios(),
                    software.getInformacoesGerais(),
                    "Ver Mais" // Texto do botão "Ver Mais"
            };
            tableModel.addRow(rowData);
        }

        // Criação da JTable com um TableCellRenderer para os botões
        softwareTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Apenas a última coluna com o botão é editável
                return column == 9;
            }
        };

        // Adicionando o botão "Ver Mais" na última coluna
        softwareTable.getColumn("Ações").setCellRenderer(new ButtonRenderer());
        softwareTable.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox(), softwares));

        // Configuração para melhorar o layout da tabela
        softwareTable.setFillsViewportHeight(true);
        softwareTable.setRowHeight(30); // Altura das linhas

        JScrollPane scrollPane = new JScrollPane(softwareTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}

// Renderer para exibir o botão "Ver Mais"
class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// Editor para o botão "Ver Mais"
class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private List<Software> softwares;
    private int row;

    public ButtonEditor(JCheckBox checkBox, List<Software> softwares) {
        super(checkBox);
        this.softwares = softwares;
        button = new JButton();
        button.setOpaque(true);

        // Ação ao clicar no botão "Ver Mais"
        button.addActionListener((ActionEvent e) -> fireEditingStopped());
    }

    // Retorna o componente para a célula (botão)
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        this.row = row;
        clicked = true;
        return button;
    }

    // Ação após o clique no botão
    public Object getCellEditorValue() {
        if (clicked) {
            // Abrir a janela de detalhes do software
            Software software = softwares.get(row);
            new SoftwareDetailsView(software).setVisible(true);
        }
        clicked = false;
        return label;
    }

    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}

// Janela de detalhes do software
class SoftwareDetailsView extends JFrame {

    public SoftwareDetailsView(Software software) {
        setTitle("Detalhes do Software: " + software.getNome());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Área de texto para exibir as informações detalhadas do software
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setText("Nome: " + software.getNome() +
                "\nLicença: " + software.getLicenca() +
                "\nTipo de Licença: " + software.getTipoLicenca() +
                "\nUnidade: " + software.getUnidade() +
                "\nData de Aquisição: " + software.getDataAquisicaoFormatada() +
                "\nData de Vencimento: " + software.getDataVencimentoFormatada() +
                "\nPatrimônio: " + software.getPatrimonio() +
                "\nUsuários: " + software.getUsuarios() +
                "\nInformações Gerais: " + software.getInformacoesGerais());

        // Adicionar o JTextArea à janela
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }
}
