// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 Sign_Up 모듈
// Sign_Up 모듈 기능 : 회원가입 기능을 통해 회원 릴레이션에 신규 회원 정보를 넣는다(+ 중복 여부 검사 )
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)
package InBodyFunctions;

import java.sql.*; // sql import
import java.util.Scanner; // 입력을 위한 스캐너 import

public class Sign_Up {
    public int Studentnum; // 학번
    public String name; // 이름
    public int grade; // 학년
    public String gender; // 성별
    public int Password; // 비밀번호
    public int mode;
    Scanner sc=new Scanner((System.in)); // 입력을 위한 Scanner 객체 선언
    public void sign_up() // 회원가입 메소드
    {
        PreparedStatement preparedStatement=null;
        int complete=0; // 입력한 정보들이 다 조건에 맞는지 검사하는 변수
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            System.out.println("\n\n*******************************************");
            System.out.println("회원가입 화면");
            System.out.println("*******************************************");
            System.out.println("학번:");
            Studentnum=sc.nextInt(); // 학번 입력
            System.out.println("이름:");
            name=sc.next(); // 이름 입력
            System.out.println("학년:");
            grade=sc.nextInt(); // 학년 입력
            System.out.println("성별:");
            gender=sc.next(); // 성별 입력
            System.out.println("비밀번호:");
            Password=sc.nextInt(); // 비밀번호 입력
            ResultSet rs=stmt.executeQuery("SELECT 학번 FROM 회원 WHERE 학번="+Studentnum);
            // 이미 가입했는지 검사 -> 중복 데이터 방지
            while(rs.next())
            {
                if(Studentnum==rs.getInt(1)) // 입력한 학번이 이미 회원 릴레이션에 있다면
                {
                    System.out.println("\n이미 가입이 되어 있습니다!!"); 
                    goback_FirstScreen(); // 프로그램 첫 화면으로 이동
                    break;
                }
            }
            if((Studentnum>=2010000000)&&(Studentnum<=2050000000)) // 학번 제약사항 : 정수 10자리
            {
                complete+=1; // 입력한 학번이 제약사항을 만족한다면 1추가
            }
            else // 만족하지 않는다면
            {
                System.out.println("\n학번 자리수는 10자리입니다!!"); 
                goback_FirstScreen(); // 프로그램 첫 화면으로 이동
            }

            if((grade>=1)&&(grade<=4)) // 학년은 1,2,3,4중 하나여야 한다.
            {
                complete+=1; // 만족한다면 complete 변수에 1 추가
            }
            else 
            {
                System.out.println("\n학년 잘못 입력!!");
                goback_FirstScreen(); // 프로그램 첫 화면으로 이동
            }
            if((gender.equals("남자"))||(gender.equals("여자"))) // 성별은 '남자' or '여자'로 입력해야 한다.
            {
                complete+=1;
            }
            else
            {
                System.out.println("\n성별 잘못 입력!!");
                goback_FirstScreen(); // 프로그램 첫 화면으로 이동
            }
            if((Password>=1000)&&(Password<=9999)) // 비밀번호 제약사항 : 정수 4자리
            {
                complete+=1; 
            }
            else
            {
                System.out.println("\n비밀번호는 숫자로 4자리 입력해주세요!!");
                goback_FirstScreen(); // 프로그램 첫 화면으로 이동
            }
            if(complete==4) // 입력한 모든 정보들이 제약사항을 다 만족한다면
            {
                System.out.println("회원가입 완료를 원하시면 1 을 입력해주세요!!");
                System.out.println("회원가입 취소를 원하시면 2 을 입력해주세요!!");
                complete=sc.nextInt();
                if(complete==1) // 회원가입 완료를 원할시
                {
                    String sql="INSERT INTO 회원 VALUES(?,?,?,?,?)"; // 회원 릴레이션에 데이터 값들을 집어넣음
                    preparedStatement=con.prepareStatement(sql);
                    preparedStatement.setInt(1,Studentnum);
                    preparedStatement.setString(2,name);
                    preparedStatement.setInt(3,grade);
                    preparedStatement.setString(4,gender);
                    preparedStatement.setInt(5,Password);
                    int count=preparedStatement.executeUpdate(); // 잘 값들이 들어갔는지 검사
                    if(count==0) // 회원 릴레이션에 값들이 들어가지 못했다면
                    {
                        System.out.println("회원가입 실패");
                        goback_FirstScreen();
                    }
                    else // 회원 릴레이션에 값들이 잘 들어갔다면
                    {
                        System.out.println("회원가입 완료"); 
                        goback_FirstScreen();
                    }
                }
                if(complete==2) // 회원가입을 원하지 않을시
                {
                    System.out.println("회원가입 취소");
                    goback_FirstScreen();
                }
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void goback_FirstScreen() // 뒤로가기 기능 , 프로그램 첫 화면으로 이동
    {
        Main main=new Main();
        mode=main.FirstScreen();
        main.FirstScreen_Function(mode);
    }
}
