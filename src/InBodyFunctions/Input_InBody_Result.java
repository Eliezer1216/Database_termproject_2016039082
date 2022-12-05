// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 Input_InBody_Result 모듈
// Input_InBody_Result 모듈 기능 : 체중 , 골격근 , 체지방률을 입력 -> 인바디 결과를 전체학생InBody 릴레이션에 저장 
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)
package InBodyFunctions;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Input_InBody_Result {
    public String weight; // 체중
    public String muscle; // 골격근
    public String body_fat_percentage; // 체지방률
    public int TypeID; // 체형 유형 ID
    Scanner sc=new Scanner((System.in)); // 입력을 위한 스캐너 객체 선언
    UserScreen userScreen=new UserScreen(); // 사용자 메뉴얼 객체 선언
    public void Input_InBody(int usernum) // 로그인한 사용자 본인의 인바디 결과를 입력한다.
    {
        try{
            int complete=0; // 체중 , 골격근 , 체지방률이 조건에 맞으면 검사할때마다 1씩 증가
            PreparedStatement preparedStatement=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            System.out.println("\n\n*******************************************");
            System.out.println("인바디 결과 입력");
            System.out.println("*******************************************");
            System.out.println("체중:");
            weight=sc.next(); // 체중 입력
            System.out.println("골격근:");
            muscle=sc.next(); // 골격근 입력
            System.out.println("체지방률:");
            body_fat_percentage=sc.next(); // 체지방률 입력
            if((weight.equals("표준이하"))||(weight.equals("표준"))||(weight.equals("표준이상")))
            {
                complete+=1; // 체중이 '표준이하','표준','표준이상' 중 하나이면 1 추가
            }
            else
            {
                System.out.println("체중을 입력할때 표준 , 표준이상 , 표준이하 중 하나를 입력하세요!!");
            }
            if((muscle.equals("표준이하"))||(muscle.equals("표준"))||(muscle.equals("표준이상")))
            {
                complete+=1;   // 골격근이 '표준이하','표준','표준이상' 중 하나이면 1 추가
            }
            else
            {
                System.out.println("골격근을 입력할때 표준 , 표준이상 , 표준이하 중 하나를 입력하세요!!");
            }
            if((body_fat_percentage.equals("표준이하"))||(body_fat_percentage.equals("표준"))||(body_fat_percentage.equals("표준이상")))
            {
                complete+=1;  // 체지방률이 '표준이하','표준','표준이상' 중 하나이면 1 추가
            }
            else
            {
                System.out.println("체지방률을 입력할때 표준 , 표준이상 , 표준이하 중 하나를 입력하세요!!");
            }
            if(complete==3) // 체중 , 골격근 , 체지방률 입력값이 다 조건에 만족할때
            {
                System.out.println("인바디 결과 저장을 원하시면 1 을 입력해주세요!!");
                System.out.println("뒤로 가기를 원하시면 2 을 입력해주세요!!");
                complete=sc.nextInt();
                if(complete==1) // 인바디 결과 저장을 원할때
                {
                    Body_type body=new Body_type(); // Body_type 객체 선언
                    TypeID=body.Decide_Body_type(weight,muscle,body_fat_percentage);//체형 유형 계산 후 체형 유형 아이디 반환
                    ResultSet rs=stmt.executeQuery("SELECT 학번 FROM 회원 WHERE 학번="+usernum);
                    while(rs.next())
                    {
                        if(usernum==rs.getInt(1))
                        {
                            try{
                                stmt.executeUpdate("DELETE FROM 전체학생InBody WHERE 학번="+usernum); 
                                // 최신 업데이트를 위한 기존의 데이터 삭제
                                break;
                            }catch (SQLException e) {
                                System.out.println(e);
                            }

                        }
                    }

                    LocalDateTime now = LocalDateTime.now(); // 현 시간 계산
                    String Input_data= now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")); 
                    //시간을 형식에 맞춤
                    String sql="INSERT INTO 전체학생InBody VALUES(?,?,?,?,?,?)";
                    // 전체학생InBody 릴레이션에 최신 인바디 결과를 집어넣음. -> 입력한 결과 값을 집어넣는다.
                    preparedStatement=con.prepareStatement(sql);
                    preparedStatement.setInt(1,usernum);
                    preparedStatement.setString(2,weight);
                    preparedStatement.setString(3,muscle);
                    preparedStatement.setString(4,body_fat_percentage);
                    preparedStatement.setInt(5,TypeID);
                    preparedStatement.setString(6,Input_data);
                    int count=preparedStatement.executeUpdate(); 
                    // 전체학생InBody 릴레이션에 잘 들어갔는지 확인 
                    if(count==0) // 잘 들어가지 못했다면
                    {
                        System.out.println("\n인바디 결과 저장 실패");
                        userScreen.F_UserScreen(usernum); // 사용자 메뉴얼 화면으로 이동
                    }
                    else // 잘 들어갔다면
                    {
                        System.out.println("인바디 결과 저장 완료"); 
                        userScreen.F_UserScreen(usernum);// 사용자 메뉴얼 화면으로 이동
                    }
                }
                if(complete==2) // 인바디 검사 결과 저장하지 않음
                {
                    userScreen.F_UserScreen(usernum); // 사용자 메뉴얼 화면으로 이동
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }
}
