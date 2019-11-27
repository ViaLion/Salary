import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetSQL {
    static String username;
    static String password;

    static String ID;
    static String Name;
    static String Sex;
    static String Branch;
    static String Department;
    static String Tel;
    static String BasicSalary;
    static String Reward;
    static String Punishment;
    static String Salary;

    static String RewID;
    static String RewSalary;
    static String RewRecord;

    static String PunID;
    static String PunSalary;
    static String PunRecord;

    static Connection connect = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    public static void ConnectSQL(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;DatabaseName=SalaryManagement","sa","king990711");
            System.out.println("The SQL is connected!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void queryAdmin(String name){
        try {
            ps = connect.prepareStatement("select * from Admin where AdminName=? ");
            // 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
            ps.setString(1, name);
            // ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
            rs = ps.executeQuery();
            // 循环取出
            if (rs.next()) {
                // 将用户的用户名和密码取出
                username = rs.getString(2);
                password = rs.getString(3);
                System.out.println("Successfully retrieved username and password from database!");
                System.out.println(username + "\t" + password + "\t");
            }else
            {
                JOptionPane.showMessageDialog(null, "There is no such afministrator.\nPlease enter again！", "Prompt", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void queryStaff(String name){
        try {
            ps = connect.prepareStatement("select * from Employee where EmpName=? ");
            // 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
            ps.setString(1, name);
            // ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
            rs = ps.executeQuery();
            // 循环取出
            if (rs.next()) {
                // 将用户的用户名和密码取出
                username = rs.getString(2);
                password = rs.getString(7);
                System.out.println("Successfully retrieved username and password from database!");
                System.out.println(username + "\t" + password + "\t");
            }else
            {
                JOptionPane.showMessageDialog(null, "There is no such staff.\nPlease enter again！", "Prompt", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void queryID(String id){
        try {
            ps = connect.prepareStatement("select * from Salary where EmpID =? ");
            // 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
            ps.setString(1, id);
            // ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
            rs = ps.executeQuery();
            if(rs.next())
            {
                    ID = rs.getString(1);
                    Name = rs.getString(2);
                    Sex = rs.getString(3);
                    Tel = rs.getString(4);
                    Department = rs.getString(5);
                    Branch = rs.getString(6);
                    BasicSalary = rs.getString(7);
                    Reward = rs.getString(8);
                    Punishment = rs.getString(9);
                    Salary = rs.getString(10);
            }else
            {
                JOptionPane.showMessageDialog(null, "There is no such staff!\nPlease enter again!", "Prompt", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void queryIDPun(String id){
        try {
            ps = connect.prepareStatement("select * from Punishment where EmpID =? ");
            // 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
            ps.setString(1, id);
            // ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
            rs = ps.executeQuery();
            if(rs.next())
            {
                PunID = rs.getString(1);
                PunSalary = rs.getString(3);
                PunRecord = rs.getString(4);
            }else
            {
                JOptionPane.showMessageDialog(null, "There is no such staff!\nPlease enter again!", "Prompt", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void queryIDRew(String id){
        try {
            ps = connect.prepareStatement("select * from Reward where EmpID =? ");
            // 给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
            ps.setString(1, id);
            // ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
            rs = ps.executeQuery();
            if(rs.next())
            {
                RewID = rs.getString(1);
                RewSalary = rs.getString(3);
                RewRecord = rs.getString(4);
            }else
            {
                JOptionPane.showMessageDialog(null, "There is no such staff!\nPlease enter again!", "Prompt", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

