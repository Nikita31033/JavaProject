import javax.swing.*;

public class Form extends JFrame {
    private JPanel mainContainer;
    private JTable table1;
    private JTextField textField1;
    private JButton загрузитьДанныеВТаблицуButton;
    private JTextField textField2;
    private JButton экспортДанныхИзТаблицыButton;

    public Form() {
        setContentPane(mainContainer);
        setVisible(true);
    }
}
