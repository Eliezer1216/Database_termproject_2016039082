package InBodyFunctions;

import java.sql.*;

public class Body_type {
    public int TypeID;
    UserScreen userScreen=new UserScreen();
    public int Decide_Body_type(String weight,String muscle,String body_fat_percentage)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            String sql="SELECT TypeID FROM 체형 WHERE 체중= '"+weight+ "' AND 골격근= '"+muscle+"' AND 체지방률= '"+body_fat_percentage+"' ;";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                TypeID=rs.getInt(1);

            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return TypeID;
    }
    public void print_BodyType_InFo(int usernum)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM 체형");
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"=  체중: "+rs.getString(3)+"  골격근: "+rs.getString(4)+"  체지방률: "+rs.getString(5));

            }

            userScreen.F_UserScreen(usernum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}
