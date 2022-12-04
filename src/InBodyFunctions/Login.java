package InBodyFunctions;


import java.sql.*;
import java.util.Scanner;

public class Login {
    public int Studentnum;
    //public int password;
    public int backmode;
    Scanner sc=new Scanner((System.in));

    public int Login()
    {

        int Password;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            System.out.println("\n*******************************************");
            System.out.println("로그인 화면");
            System.out.println("*******************************************");
            System.out.println("\n학번을 입력하세요:");
            Studentnum=sc.nextInt();
            System.out.println("비밀번호를 입력하세요:");
            Password=sc.nextInt();
            ResultSet rs=stmt.executeQuery("SELECT 학번,비밀번호 FROM 회원 WHERE 학번="+Studentnum);
            rs.next();
            if(Password==rs.getInt(2))
            {
                System.out.println("\n로그인에 성공하셨습니다!!");
                UserScreen userScreen=new UserScreen();
                UserScreen.F_UserScreen(Studentnum);
            }
            else {
                System.out.println("\n로그인에 실패하셨습니다!!");
            }
            //while(rs.next())
            //System.out.println(rs.getInt(1)+" "+rs.getInt(2));
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        return 0;
    }
    // 회원테이블에 정보 비교 함수 리턴 값
    // 로그인에 실패하면 계속 실패했다고 알려주기
    // 뒤로가기는 3

}

