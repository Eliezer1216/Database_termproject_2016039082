// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 Body_type모듈
// Body_type 모듈 기능 : 1. 체형 유형을 사용자에게 보여줌
//                      2. 인바디 검사시 체중 , 골격근 , 체지방률을 계산해 알맞은 체형 유형 아이디 반환
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)
package InBodyFunctions;

import java.sql.*;

public class Body_type {
    public int TypeID; // 체형 유형 아이디
    UserScreen userScreen=new UserScreen(); // 사용자 메뉴얼 객체 선언
    public int Decide_Body_type(String weight,String muscle,String body_fat_percentage) 
    { // 인바디 검사 결과 모듈에서 체중 , 골격근 , 체지방률을 인수로 가져옴
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            String sql="SELECT TypeID FROM 체형 WHERE 체중= '"+weight+ "' AND 골격근= '"+muscle+"' AND 체지방률= '"+body_fat_percentage+"' ;";
            //인바디 검사 결과 입력시 입력된 체중 , 골격근 , 체지방률에 맞는 체형 유형을 찾아 체형 유형 아이디를 반환
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
        return TypeID; //알맞는 체형 유형 아이디를 반환
    }
    public void print_BodyType_InFo(int usernum) // 체형 유형 정보 보여주는 함수
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM 체형"); // 체형 정보 릴레이션에서 정보를 가져옴
            while(rs.next())
            {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"=  체중: "+rs.getString(3)+"  골격근: "+rs.getString(4)+"  체지방률: "+rs.getString(5));

            }

            userScreen.F_UserScreen(usernum); // 사용자 메뉴얼 화면으로 다시 이동
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}
