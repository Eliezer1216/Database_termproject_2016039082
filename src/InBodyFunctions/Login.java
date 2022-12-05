// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 Login 모듈
// Login 모듈 기능 : 1. 학번과 비밀번호를 입력받아 회원 릴레이션에 입력한 학번 , 비밀번호가 있는지 검사 후 로그인 여부 결정
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)
package InBodyFunctions;
import java.sql.*; //sql import
import java.util.Scanner; // 입력을 위한 스캐너 import

public class Login {
    public int Studentnum; // 로그인시 입력하는 학번
    //public int password;
    public int backmode; // 뒤로 가기 변수
    Scanner sc=new Scanner((System.in)); // 입력을 위한 Scanner 객체 선언

    public int Login()
    {

        int Password; // 비밀번호 변수 선언
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            System.out.println("\n*******************************************");
            System.out.println("로그인 화면");
            System.out.println("*******************************************");
            System.out.println("\n학번을 입력하세요:");
            Studentnum=sc.nextInt(); // 학번 입력
            System.out.println("비밀번호를 입력하세요:");
            Password=sc.nextInt(); // 비밀번호 입력
            ResultSet rs=stmt.executeQuery("SELECT 학번,비밀번호 FROM 회원 WHERE 학번="+Studentnum);
            // 쿼리를 날려서 입력한 학번이 회원 릴레이션에 있는지 검사
            while(rs.next()) 
            {
                if(Password==rs.getInt(2))
                {   // 회원 릴레이션에 있는 비밀번호와 입력한 비밀번호가 일치하는지 검사
                    System.out.println("\n로그인에 성공하셨습니다!!"); // 일치하면
                    UserScreen userScreen=new UserScreen(); // 사용자 메뉴얼 화면으로 이동
                    UserScreen.F_UserScreen(Studentnum); // 로그인한 사용자를 인수로 전달함
                    break;
                }
                else
                {
                    System.out.println("\n로그인에 실패하셨습니다!!"); // 비밀번호가 일치하지 않으면
                    Main main=new Main(); // 다시 첫 화면으로 돌아감
                    backmode=main.FirstScreen();
                    main.FirstScreen_Function(backmode);
                }
            }


            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


        return 0;
    }

}

