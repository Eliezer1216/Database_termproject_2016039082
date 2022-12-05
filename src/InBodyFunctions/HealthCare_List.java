// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 HealthCare_List 모듈
// HealthCare_List 모듈 기능 :
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)
package InBodyFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class HealthCare_List {
    public int Health_mode;
    Scanner sc=new Scanner((System.in));
    public void All_Student(int usernum)
    {
        try
        {

            System.out.println("\n-----------------------------------------------------------");
            System.out.println("전체 학생 건강 정보\n");
            System.out.println("1. 비만형 리스트 보기\n2. 허약형 리스트 보기\n3. 학년별로 보기");
            System.out.println("------------------------------------------------------------");
            System.out.println("\n원하는 기능의 숫자를 입력하세요 :");
            Health_mode=sc.nextInt();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            if(Health_mode==1)
            {
                System.out.println("비만형 리스트 보기");
                // 회원에서 비밀번호 뺀 릴레이션과 건강관리 필요학생 릴레이션과 결합
                // 비만형 추출
            }
            else if(Health_mode==2)
            {
                System.out.println("허약형 리스트 보기");
                // 회원에서 비밀번호 뺀 릴레이션과 건강관리 필요학생 릴레이션과 결합
                // 허약형 추출
            }
            else if(Health_mode==3)
            {
                System.out.println("학년별로 보기");
                // 회원에서 비밀번호 뺀 릴레이션과 건강관리 필요학생 릴레이션과 결합
                //학년으로 검색
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
