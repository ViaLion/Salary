import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Salary_Management extends JFrame implements ActionListener {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel panel;
    private JPanel panel1;
    private JRadioButton adminRadioButton;
    private JRadioButton staffRadioButton;

    public void adminlogin() {
        if (GetSQL.password.equals(passwordField1.getText())) {
            JOptionPane.showMessageDialog(null, "Login Successful", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
            //关闭当前页面
            dispose();
            //创建一个新页面
            Admin_UI admin_ui = new Admin_UI();
        } else if (textField1.getText().isEmpty() && passwordField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter username and password!", "Prompt", JOptionPane.WARNING_MESSAGE);
        } else if (textField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter username!", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
        } else if (passwordField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter password!", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect username or password! \n Please enter again!", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
        }
    }

    public void stafflogin() {
        if (GetSQL.password.equals(passwordField1.getText())) {
            JOptionPane.showMessageDialog(null, "Login Successful", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
            //关闭当前页面
            dispose();
            //创建一个新页面
            Staff_UI staff_ui = new Staff_UI();

            dispose();
        } else if (textField1.getText().isEmpty() && passwordField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter username and password!", "Prompt", JOptionPane.WARNING_MESSAGE);
        } else if (textField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter username!", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
        } else if (passwordField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter password!", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect username or password! \n Please enter again!", "Prompt", JOptionPane.WARNING_MESSAGE);
            clear();
        }
    }

    public void clear() {
        textField1.setText("");
        passwordField1.setText("");
    }

    public Salary_Management() {

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField1.getText().isEmpty() && !passwordField1.getText().isEmpty()) {
                    //先与数据库建立连接
                    GetSQL.ConnectSQL();
                    //如果是管理员登录
                    if (adminRadioButton.isSelected()) {
                        GetSQL.queryAdmin(textField1.getText());
                        //首先判断是否存在
                        if (GetSQL.password == null) {
                            clear();
                        } else {
                            //调用登录方法
                            adminlogin();
                        }
                    } else if (staffRadioButton.isSelected()) {
                        GetSQL.queryStaff(textField1.getText());
                        if (GetSQL.password == null) {
                            clear();
                        } else {
                            stafflogin();
                        }
                    } else if (textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter username!", "Propmt", JOptionPane.WARNING_MESSAGE);
                        clear();
                    } else if (passwordField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter password!", "Propmt", JOptionPane.WARNING_MESSAGE);
                        clear();
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Salary Manage");
        frame.setContentPane(new Salary_Management().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Font panelFont = this.$$$getFont$$$(null, -1, 24, panel.getFont());
        if (panelFont != null) panel.setFont(panelFont);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(350, 200), null, null, 0, false));
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(800, 600), 0, false));
        textField1 = new JTextField();
        textField1.setColumns(20);
        Font textField1Font = this.$$$getFont$$$(null, -1, 18, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        panel1.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("User:");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 18, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Password:");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        passwordField1 = new JPasswordField();
        Font passwordField1Font = this.$$$getFont$$$(null, -1, 18, passwordField1.getFont());
        if (passwordField1Font != null) passwordField1.setFont(passwordField1Font);
        panel1.add(passwordField1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        OKButton = new JButton();
        OKButton.setText("OK");
        panel1.add(OKButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 5, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 18, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Permission：");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 3, false));
        adminRadioButton = new JRadioButton();
        Font adminRadioButtonFont = this.$$$getFont$$$(null, -1, 18, adminRadioButton.getFont());
        if (adminRadioButtonFont != null) adminRadioButton.setFont(adminRadioButtonFont);
        adminRadioButton.setText("Admin");
        panel1.add(adminRadioButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        staffRadioButton = new JRadioButton();
        Font staffRadioButtonFont = this.$$$getFont$$$(null, -1, 18, staffRadioButton.getFont());
        if (staffRadioButtonFont != null) staffRadioButton.setFont(staffRadioButtonFont);
        staffRadioButton.setText("Staff");
        panel1.add(staffRadioButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(staffRadioButton);
        buttonGroup.add(adminRadioButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
