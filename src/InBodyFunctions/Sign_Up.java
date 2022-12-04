package InBodyFunctions;

import java.sql.*;
import java.util.Scanner;

public class Sign_Up {
    public int Studentnum;
    public String name;
    public int grade;
    public String gender;
    public int Password;
    public int mode;
    Scanner sc=new Scanner((System.in));
    public void sign_up()
    {
        PreparedStatement preparedStatement=null;
        int complete=0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            System.out.println("\n\n*******************************************");
            System.out.println("회원가입 화면");
            System.out.println("*******************************************");
            System.out.println("학번:");
            Studentnum=sc.nextInt();
            System.out.println("이름:");
            name=sc.next();
            System.out.println("학년:");
            grade=sc.nextInt();
            System.out.println("성별:");
            gender=sc.next();
            System.out.println("비밀번호:");
            Password=sc.nextInt();
            ResultSet rs=stmt.executeQuery("SELECT 학번 FROM 회원 WHERE 학번="+Studentnum);
            while(rs.next())
            {
                if(Studentnum==rs.getInt(1))
                {
                    System.out.println("\n이미 가입이 되어 있습니다!!");
                    goback_FirstScreen();
                    break;
                }
            }
            if((Studentnum>=2010000000)&&(Studentnum<=2050000000))
            {
                complete+=1;
            }
            else
            {
                System.out.println("\n학번 자리수는 10자리입니다!!");
                goback_FirstScreen();
            }

            if((grade>=1)&&(grade<=4))
            {
                complete+=1;
            }
            else
            {
                System.out.println("\n학년 잘못 입력!!");
                goback_FirstScreen();
            }
            if((gender.equals("남자"))||(gender.equals("여자")))
            {
                complete+=1;
            }
            else
            {
                System.out.println("\n성별 잘못 입력!!");
                goback_FirstScreen();
            }
            if((Password>=1000)&&(Password<=9999))
            {
                complete+=1;
            }
            else
            {
                System.out.println("\n비밀번호는 숫자로 4자리 입력해주세요!!");
                goback_FirstScreen();
            }
            if(complete==4)
            {
                System.out.println("회원가입 완료를 원하시면 1 을 입력해주세요!!");
                System.out.println("회원가입 취소를 원하시면 2 을 입력해주세요!!");
                complete=sc.nextInt();
                if(complete==1)
                {
                    String sql="INSERT INTO 회원 VALUES(?,?,?,?,?)";
                    preparedStatement=con.prepareStatement(sql);
                    preparedStatement.setInt(1,Studentnum);
                    preparedStatement.setString(2,name);
                    preparedStatement.setInt(3,grade);
                    preparedStatement.setString(4,gender);
                    preparedStatement.setInt(5,Password);
                    int count=preparedStatement.executeUpdate();
                    if(count==0)
                    {
                        System.out.println("회원가입 실패");
                        goback_FirstScreen();
                    }
                    else
                    {
                        System.out.println("회원가입 완료");
                        goback_FirstScreen();
                    }
                }
                if(complete==2)
                {
                    System.out.println("회원가입 취소");
                    goback_FirstScreen();
                }
            }

            


            //while(rs.next())
            //System.out.println(rs.getInt(1)+" "+rs.getInt(2));
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void goback_FirstScreen()
    {
        Main main=new Main();
        mode=main.FirstScreen();
        main.FirstScreen_Function(mode);
    }
}
