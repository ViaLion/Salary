import java.sql.*;
public class Test {

    public static void squery(String name,String pass) {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Shopping";
        String userName="sa";
        String userPwd="king990711";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try
        {
            Class.forName(driverName);
            Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("success!");
            String sqlString = "select * from car where uname=? and gid=?";
            statement = dbConn.prepareStatement(sqlString);
            statement.setString(1,name);
            statement.setString(2,pass);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("yeah，成功了！");
            }
            else {
                System.out.println("没有这个人啊亲");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.print("fail!");
        }
    }

    public static void main(String[] args) {
        squery("li","1");
    }
}
