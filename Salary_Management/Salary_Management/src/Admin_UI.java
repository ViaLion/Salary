import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Admin_UI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JFrame f = new JFrame("Salary Management -- Admin");
    JLabel label = new JLabel("ADMIN",JLabel.CENTER);
    JButton b1 = new JButton("Staff");
    JButton b2 = new JButton("Reward");
    JButton b3 = new JButton("Punish");
    JButton b4 = new JButton("Return");
    JPanel panel = new JPanel();
    JPanel p = new JPanel();

    public Admin_UI() {


        label.setFont(new Font("",1,96));
        panel.setLayout(new BorderLayout());
        panel.add(label,BorderLayout.CENTER);

        p.setLayout(new FlowLayout());
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);

        p.setBackground(Color.gray);
        p.setVisible(true);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        f.setBounds(500, 300, 500, 200);
        f.setLayout(new BorderLayout());
        f.add(panel,BorderLayout.NORTH);
        f.add(p);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (b4.equals(e.getSource())) {// 返回
            Salary_Management d = new Salary_Management();

            f.dispose();
        }

        if (b1.equals(e.getSource())) {
            AddStaff yg = new AddStaff();

            f.dispose();
        }
        if (b2.equals(e.getSource())) {
            Reward gz = new Reward();

            f.dispose();
        }
        if(b3.equals(e.getSource())){
            Punish punish = new Punish();

            f.dispose();
        }

    }
}
 