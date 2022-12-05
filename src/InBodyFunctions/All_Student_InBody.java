// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 All_Student_InBody 모듈
// All_Student_InBody 모듈 기능 :
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)
package InBodyFunctions;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class All_Student_InBody {
    public int A_mode;
    Scanner sc=new Scanner((System.in));
    public void All_Student(int usernum)
    {
        try
        {

            System.out.println("\n-----------------------------------------------------------");
            System.out.println("전체 학생 건강 정보\n");
            System.out.println("1. 학번으로 찾기\n2. 이름으로 찾기\n3. 학년별로 찾기");
            System.out.println("------------------------------------------------------------");
            System.out.println("\n원하는 기능의 숫자를 입력하세요 :");
            A_mode=sc.nextInt();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            if(A_mode==1)
            {
                System.out.println("학번으로 찾기");
                // 회원에서 비밀번호 뺀 릴레이션과 전체학생 건강상태에서 typeId를 빼낸 릴레이션과 결함
                //학번으로 검색
            }
            else if(A_mode==2)
            {
                System.out.println("이름으로 찾기");
                // 회원에서 비밀번호 뺀 릴레이션과 전체학생 건강상태에서 typeId를 빼낸 릴레이션과 결함
                //이름으로 검색
            }
            else if(A_mode==3)
            {
                System.out.println("학년별로 찾기");
                // 회원에서 비밀번호 뺀 릴레이션과 전체학생 건강상태에서 typeId를 빼낸 릴레이션과 결함
                //학년으로 검색
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
