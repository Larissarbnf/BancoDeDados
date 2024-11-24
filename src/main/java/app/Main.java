package app;

import DAO.AlunoDAO;
import Model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().criarInterface();
        });
    }

    public void criarInterface() {
        System.out.println("Iniciando a aplicação...");

        // Criar a tela principal
        JFrame frame = new JFrame("Sistema de Gerenciamento de Alunos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel de Entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel("Nome do Aluno:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel raLabel = new JLabel("RA do Aluno:");
        JTextField raField = new JTextField();
        inputPanel.add(raLabel);
        inputPanel.add(raField);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Painel de Ações
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());

        JButton btnSave = new JButton("Salvar");
        JButton btnList = new JButton("Listar");
        JButton btnDelete = new JButton("Excluir");

        actionPanel.add(btnSave);
        actionPanel.add(btnList);
        actionPanel.add(btnDelete);

        frame.add(actionPanel, BorderLayout.CENTER);

        // Tabela para exibir alunos
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        frame.add(new JScrollPane(list), BorderLayout.SOUTH);

        // Criando a instância do DAO
        AlunoDAO alunoDAO = new AlunoDAO();

        // Ação de Salvar
        btnSave.addActionListener(e -> {
            String nome = nameField.getText();
            String ra = raField.getText();

            if (nome.isEmpty() || ra.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
                return;
            }

            try {
                Aluno aluno = new Aluno();
                aluno.setNome(nome);
                aluno.setId(Integer.parseInt(ra));

                alunoDAO.salvar(aluno);
                JOptionPane.showMessageDialog(frame, "Aluno salvo com sucesso!");

                nameField.setText("");
                raField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "RA inválido!");
            }
        });

        // Ação de Listar
        btnList.addActionListener(e -> {
            List<Aluno> alunos = alunoDAO.listarTodos();
            listModel.clear();

            for (Aluno aluno : alunos) {
                listModel.addElement("Nome: " + aluno.getNome() + " - RA: " + aluno.getId());
            }
        });

        // Ação de Excluir
        btnDelete.addActionListener(e -> {
            String raStr = JOptionPane.showInputDialog(frame, "Digite o RA do aluno a ser excluído:");
            if (raStr != null && !raStr.isEmpty()) {
                try {
                    int ra = Integer.parseInt(raStr);
                    alunoDAO.deletar(ra);
                    JOptionPane.showMessageDialog(frame, "Aluno excluído com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "RA inválido.");
                }
            }
        });

        // Exibir a tela
        frame.setVisible(true);
    }
}
