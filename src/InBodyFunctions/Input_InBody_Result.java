package InBodyFunctions;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Input_InBody_Result {
    public String weight;
    public String muscle;
    public String body_fat_percentage;
    Scanner sc=new Scanner((System.in));
    UserScreen userScreen=new UserScreen();
    public void Input_InBody(int usernum)
    {
        try{
            int complete=0;
            PreparedStatement preparedStatement=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            System.out.println("\n\n*******************************************");
            System.out.println("인바디 결과 입력");
            System.out.println("*******************************************");
            System.out.println("체중:");
            weight=sc.next();
            System.out.println("골격근:");
            muscle=sc.next();
            System.out.println("체지방률:");
            body_fat_percentage=sc.next();
            if((weight.equals("표준이하"))||(weight.equals("표준"))||(weight.equals("표준이상")))
            {
                complete+=1;
            }
            else
            {
                System.out.println("체중을 입력할때 표준 , 표준이상 , 표준이하 중 하나를 입력하세요!!");
            }
            if((muscle.equals("표준이하"))||(muscle.equals("표준"))||(muscle.equals("표준이상")))
            {
                complete+=1;
            }
            else
            {
                System.out.println("골격근을 입력할때 표준 , 표준이상 , 표준이하 중 하나를 입력하세요!!");
            }
            if((body_fat_percentage.equals("표준이하"))||(body_fat_percentage.equals("표준"))||(body_fat_percentage.equals("표준이상")))
            {
                complete+=1;
            }
            else
            {
                System.out.println("체지방률을 입력할때 표준 , 표준이상 , 표준이하 중 하나를 입력하세요!!");
            }
            if(complete==3)
            {
                System.out.println("인바디 결과 저장을 원하시면 1 을 입력해주세요!!");
                System.out.println("뒤로 가기를 원하시면 2 을 입력해주세요!!");
                complete=sc.nextInt();
                if(complete==1)
                {
                    ResultSet rs=stmt.executeQuery("SELECT 학번 FROM 회원 WHERE 학번="+usernum);
                    while(rs.next())
                    {
                        if(usernum==rs.getInt(1))
                        {
                            try{
                                stmt.executeUpdate("DELETE FROM 전체학생InBody WHERE 학번="+usernum);
                                break;
                            }catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    LocalDateTime now = LocalDateTime.now();
                    String Input_data= now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
                    String sql="INSERT INTO 전체학생InBody VALUES(?,?,?,?,?,?)";
                    preparedStatement=con.prepareStatement(sql);
                    preparedStatement.setInt(1,usernum);
                    preparedStatement.setString(2,weight);
                    preparedStatement.setString(3,muscle);
                    preparedStatement.setString(4,body_fat_percentage);
                    preparedStatement.setInt(5,3);
                    preparedStatement.setString(6,Input_data);
                    int count=preparedStatement.executeUpdate();
                    if(count==0)
                    {
                        System.out.println("\n인바디 결과 저장 실패");
                        userScreen.F_UserScreen(usernum);
                    }
                    else
                    {
                        System.out.println("인바디 결과 저장 완료");
                        userScreen.F_UserScreen(usernum);
                    }
                }
                if(complete==2)
                {
                    userScreen.F_UserScreen(usernum);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }
}
