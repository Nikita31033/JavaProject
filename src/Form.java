import Classes.FileWorker;
import Classes.Solutions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {
    private JPanel mainContainer;
    private JTextField inputFileField;
    private JButton importBooleanButton;
    private JTextField outputFIleField;
    private JButton exportButton;
    private JPanel tableContainer;
    private JButton addColButton;
    private JButton addRowButton;
    private JButton delRowButton;
    private JButton delColButton;
    private JButton rectangleButton;
    private JButton whoWonButton;
    private JButton importGameFieldButton;

    public Form() {
        setContentPane(mainContainer);
        setSize(800, 500);

        importBooleanButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (inputFileField.getText().isEmpty() || outputFIleField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Заполните путь к входному/выходному файлам");
                } else {
                    FileWorker fileWorker = new FileWorker(inputFileField.getText(), outputFIleField.getText());
                    UpdateDataTable(fileWorker.getTwoDimensionalArray());
                }
            }
        });

        importGameFieldButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (inputFileField.getText().isEmpty() || outputFIleField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Заполните путь к входному/выходному файлам");
                } else {
                    FileWorker fileWorker = new FileWorker(inputFileField.getText(), outputFIleField.getText());
                    UpdateDataTable(fileWorker.getGameField());
                }
            }
        });

        exportButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                FileWorker fileWorker = new FileWorker(inputFileField.getText(), outputFIleField.getText());
                StringBuilder output;
                if(tableContainer.getComponentCount() > 0) {
                    int columnsCount, rowsCount;
                    JTable table = (JTable) tableContainer.getComponent(0);
                    columnsCount = table.getColumnCount();
                    rowsCount = table.getRowCount();
                    output = new StringBuilder(rowsCount + " " + columnsCount + "\n");
                    for (int i = 0; i < rowsCount; i++)
                        for (int j = 0; j < columnsCount; j++)
                            output.append(table.getValueAt(i, j)).append(j == columnsCount - 1 ? i == rowsCount - 1 ? "" : "\n" : " ");
                    fileWorker.WriteInFile(output.toString());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Нет загруженой таблицы");
                }
            }
        });

        addRowButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (tableContainer.getComponentCount() == 0) {
                    JTable table = new JTable();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{"row" + table.getRowCount()});
                    model.addColumn("col" + table.getRowCount());
                    table.setModel(model);
                    tableContainer.add(table);
                } else {
                    JTable table = (JTable) tableContainer.getComponent(0);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{"row" + table.getRowCount()});
                    table.setModel(model);
                }
                tableContainer.updateUI();
            }
        });

        delRowButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (tableContainer.getComponentCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Нет загруженой таблицы");
                } else {
                    JTable table = (JTable) tableContainer.getComponent(0);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    if (model.getRowCount() > 0) {
                        model.removeRow(table.getRowCount() - 1);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Нет строк для удаления");
                    }
                    table.setModel(model);
                }
                tableContainer.updateUI();
            }
        });

        addColButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (tableContainer.getComponentCount() == 0) {
                    JTable table = new JTable();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{"row" + table.getRowCount()});
                    model.addColumn("col" + table.getRowCount());
                    table.setModel(model);
                    tableContainer.add(table);
                } else {
                    JTable table = (JTable) tableContainer.getComponent(0);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addColumn("col" + table.getRowCount());
                    table.setModel(model);
                }
                tableContainer.updateUI();
            }
        });

        delColButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (tableContainer.getComponentCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Нет загруженой таблицы");
                } else {
                    JTable table = (JTable) tableContainer.getComponent(0);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    if (model.getColumnCount() > 0) {
                        model.setColumnCount(model.getColumnCount() - 1);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Нет колонок для удаления");
                    }
                    table.setModel(model);
                }
                tableContainer.updateUI();
            }
        });

        rectangleButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (inputFileField.getText().isEmpty() || outputFIleField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Заполните путь к входному/выходному файлам");
                } else {
                    FileWorker fileWorker = new FileWorker(inputFileField.getText(), outputFIleField.getText());
                    Solutions rectangleSolution = new Solutions();
                    String answer =
                            rectangleSolution.FindTheBiggestTrueRectangles(fileWorker.getTwoDimensionalArray());
                    fileWorker.WriteInFile(answer);
                    JOptionPane.showMessageDialog(null, "Ответ записан в файл:" + outputFIleField.getText());
                }
            }
        });

        whoWonButton.addActionListener(new ListenerAction() {
            public void actionPerformed(ActionEvent e) {
                if (inputFileField.getText().isEmpty() || outputFIleField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Заполните путь к входному/выходному файлам");
                } else {
                    FileWorker fileWorker = new FileWorker(inputFileField.getText(), outputFIleField.getText());
                    Solutions whoWon = new Solutions();
                    String answer = "" +
                            whoWon.WhoWon(fileWorker.getGameField());
                    fileWorker.WriteInFile(answer);
                    JOptionPane.showMessageDialog(null, "Ответ записан в файл:" + outputFIleField.getText());
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String GetInputFIlePath() {
        return inputFileField.getText();
    }

    public String GetOutputFilePath() {
        return outputFIleField.getText();
    }

    private void UpdateDataTable(boolean[][] Array) {
        if(tableContainer.getComponentCount() > 0)
            tableContainer.remove(0);
        Object[][] elements = new String[Array.length][Array[0].length];
        Object[] titles = new String[Array[0].length];

        for (int i = 0; i < elements.length; i++)
            for (int j = 0; j < elements[i].length; j++)
                elements[i][j] = "" + Array[i][j] + "";

        for (int i = 0; i < titles.length; i++)
            titles[i] = "" + i + "";

        JTable table = new JTable(elements, titles);

        tableContainer.add(table);
        tableContainer.updateUI();
    }

    private void UpdateDataTable(int[][] Array) {
        if(tableContainer.getComponentCount() > 0)
            tableContainer.remove(0);
        Object[][] elements = new String[Array.length][Array[0].length];
        Object[] titles = new String[Array[0].length];

        for (int i = 0; i < elements.length; i++)
            for (int j = 0; j < elements[i].length; j++)
                elements[i][j] = "" + Array[i][j] + "";

        for (int i = 0; i < titles.length; i++)
            titles[i] = "" + i + "";

        JTable table = new JTable(elements, titles);

        tableContainer.add(table);
        tableContainer.updateUI();
    }
}

class ListenerAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Нажатие кнопки! От - "+
                e.getActionCommand() + "\n");
    }
}