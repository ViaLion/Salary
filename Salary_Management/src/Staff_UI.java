import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class Staff_UI {
    JPanel panel;
    JPanel panel1;
    JPanel panel2;
    JTextField textField1;
    JButton inquireButton;
    JTable table1;
    JScrollPane jsp;
    JPanel panel3;
    JScrollPane jsp1;
    JTable table2;
    JTable table3;
    JButton rewardButton;
    JButton punishmentButton;
    JButton back = new JButton("Back");
    JFrame frame = new JFrame("Salary Manage——Staff");

    String[] col1 = {"教工号", "姓名", "性别", "电话", "学院", "职称", "基本工资", "奖金", "罚款", "工资"};
    DefaultTableModel model1 = new DefaultTableModel(col1, 10);
    String[] col2 = {"单号", "奖金", "备注"};
    DefaultTableModel model2 = new DefaultTableModel(col2, 10);
    String[] col3 = {"单号", "罚款", "备注"};
    DefaultTableModel model3 = new DefaultTableModel(col3, 10);

    public Staff_UI() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(800, 1), null, null, 0, false));
        panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(800,1), null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 96, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("STAFF");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel2.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(800,50), null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 24, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("ID:");
        panel3.add(label2);
        textField1 = new JTextField();
        textField1.setColumns(15);
        Font textField1Font = this.$$$getFont$$$(null, -1, 24, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setHorizontalAlignment(10);
        textField1.setText("");
        panel3.add(textField1);
        inquireButton = new JButton();
        inquireButton.setText("Salary");
        panel3.add(inquireButton);
        rewardButton = new JButton();
        rewardButton.setText("Reward");
        panel3.add(rewardButton);
        punishmentButton = new JButton();
        punishmentButton.setText("Punishment");
        panel3.add(punishmentButton);
        panel3.add(back);
        jsp = new JScrollPane();
        panel2.add(jsp, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(800,1), null, null, 0, false));
        jsp.setBorder(BorderFactory.createTitledBorder("工资表"));
        table1 = new JTable(model1);
        table1.setFillsViewportHeight(true);
        jsp.setViewportView(table1);
        jsp1 = new JScrollPane();
        panel2.add(jsp1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(800,1),null, null, 0, false));
        jsp1.setBorder(BorderFactory.createTitledBorder("津贴表"));
        table2 = new JTable(model2);
        table2.setFillsViewportHeight(true);
        jsp1.setViewportView(table2);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(800,1), null, null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder("处罚表"));
        table3 = new JTable(model3);
        table3.setFillsViewportHeight(true);
        scrollPane1.setViewportView(table3);
        label1.setLabelFor(textField1);

        inquireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetSQL.ConnectSQL();
                GetSQL.queryID(textField1.getText());
                textField1.setText("");

                table1.setValueAt(GetSQL.ID, 0, 0);
                table1.setValueAt(GetSQL.Name, 0, 1);
                table1.setValueAt(GetSQL.Sex, 0, 2);
                table1.setValueAt(GetSQL.Tel, 0, 3);
                table1.setValueAt(GetSQL.Department, 0, 4);
                table1.setValueAt(GetSQL.Branch, 0, 5);
                table1.setValueAt(GetSQL.BasicSalary, 0, 6);
                table1.setValueAt(GetSQL.Reward, 0, 7);
                table1.setValueAt(GetSQL.Punishment, 0, 8);
                table1.setValueAt(GetSQL.Salary, 0, 9);
            }
        });

        rewardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetSQL.ConnectSQL();
                GetSQL.queryIDRew(textField1.getText());
                textField1.setText("");

                table2.setValueAt(GetSQL.RewID, 0, 0);
                table2.setValueAt(GetSQL.RewSalary, 0, 1);
                table2.setValueAt(GetSQL.RewRecord, 0, 2);
            }
        });

        punishmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetSQL.ConnectSQL();
                GetSQL.queryIDPun(textField1.getText());
                textField1.setText("");

                table3.setValueAt(GetSQL.PunID,0,0);
                table3.setValueAt(GetSQL.PunSalary,0,1);
                table3.setValueAt(GetSQL.PunRecord,0,2);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }

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
}
