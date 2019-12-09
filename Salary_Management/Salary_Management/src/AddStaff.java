import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddStaff extends JFrame implements ActionListener {
    JFrame f = new JFrame("教职工管理");
    JButton b1 = new JButton("录入");
    JButton b3 = new JButton("删除");
    JButton b4 = new JButton("查询所有");
    JButton b5 = new JButton("返回");
    JTextField tf1 = new JTextField(7);
    JTextField tf2 = new JTextField(7);
    JTextField tf3 = new JTextField(7);
    JTextField tf4 = new JTextField(7);
    JTextField tf5 = new JTextField(6);
    JTextField tf6 = new JTextField(6);
    JTextField tf7 = new JTextField(6);

    String[] cloum = { "职工号", "姓名", "电话", "性别","学院编号","职业编号","密码"};
    Object[][] row = new Object[10][7];
    JTable table = new JTable(row, cloum);
    JScrollPane scrollpane = new JScrollPane(table);
    JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    public AddStaff() {
        JPanel p = (JPanel) f.getContentPane();
        p.setLayout(new FlowLayout());
        p.add(scrollpane);
        p.add(splitpane);
        JPanel p1 = new JPanel();
        p1.add(b1);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.gray);
        p2.add(scrollpane);
        @SuppressWarnings("unused")
        JPanel p3 = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(new JLabel("职工号"));
        p.add(tf1);
        p.add(new JLabel("姓名"));
        p.add(tf2);
        p.add(new JLabel("电话"));
        p.add(tf3);
        p.add(new JLabel("性别"));
        p.add(tf4);
        p.add(new JLabel("学院编号"));
        p.add(tf5);
        p.add(new JLabel("职业编号"));
        p.add(tf6);
        p.add(new JLabel("密码"));
        p.add(tf7);
        splitpane.add(p1, JSplitPane.TOP);
        splitpane.add(p2, JSplitPane.BOTTOM);
        splitpane.setDividerLocation(50);
        p.setBackground(Color.gray);
        b1.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        f.setLocationRelativeTo(null);
        f.setSize(500,600);
        f.setResizable(false);// 可以调整界面大小
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /*录入*/
        if(b1.equals(e.getSource())){
            GetSQL.ConnectSQL();
            try (Statement statement = GetSQL.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                statement.executeUpdate("INSERT INTO Employee VALUES ('"+tf1.getText()+"','"+tf2.getText()+"','"+tf3.getText()+"','"+tf4.getText()+"','"+tf5.getText()+"','"+tf6.getText()+"','"+tf7.getText()+"');");
                GetSQL.connect.close();
                JOptionPane.showMessageDialog(this,"Insert Successful!");
            }catch (SQLException e1){
                JOptionPane.showMessageDialog(this,"Insert Failed");
            }
        }
        /*删除*/
        else if(b3.equals(e.getSource())){
            GetSQL.ConnectSQL();
            try (Statement statement = GetSQL.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                statement.executeUpdate("DELETE FROM Employee where EmpID = '"+tf1.getText()+"';");
                JOptionPane.showMessageDialog(this,"Delete Successful!");
                GetSQL.connect.close();
            }catch (SQLException e1){
                JOptionPane.showMessageDialog(this,"Delete Failed");
            }
        }
        /*查询全部*/
        else if(b4.equals(e.getSource())){
            GetSQL.ConnectSQL();
            try (Statement statement = GetSQL.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                if(b4.equals(e.getSource())){
//                    for (int i = 0; i < 50; i++)
//                        for (int j = 0; j < 4; j++)
//                            table.setValueAt("", i, j);
                    GetSQL.rs = statement.executeQuery("select * from Employee");
                    int k = -1;
                    while (GetSQL.rs.next()) {

                        ++k;
                        String no = GetSQL.rs.getString(1);
                        String jb = GetSQL.rs.getString(2);
                        String jt = GetSQL.rs.getString(3);
                        String jj = GetSQL.rs.getString(4);
                        String bx = GetSQL.rs.getString(5);
                        String zf = GetSQL.rs.getString(6);
                        String sum = GetSQL.rs.getString(7);


                        table.setValueAt(no, k, 0);
                        table.setValueAt(jb, k, 1);
                        table.setValueAt(jt, k, 2);
                        table.setValueAt(jj, k, 3);
                        table.setValueAt(bx, k, 4);
                        table.setValueAt(zf, k, 5);
                        table.setValueAt(sum, k, 6);
                    }
                }
            }catch (SQLException e1){
                JOptionPane.showMessageDialog(this,"Query Failed");
            }
        }else if(b5.equals(e.getSource())){
            Admin_UI admin_ui = new Admin_UI();

            f.dispose();
        }
    }
}
