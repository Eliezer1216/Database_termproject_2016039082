package InBodyFunctions;

import java.sql.*;

public class Body_type {
    public int TypeID;
    public int Decide_Body_type(String weight,String muscle,String body_fat_percentage)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            String sql="SELECT TypeID FROM 체형 WHERE 체중="+weight+ "AND 골격근="+muscle+"AND 체지방률="+body_fat_percentage;
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                TypeID=rs.getInt(1);
                return TypeID;
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }

}
