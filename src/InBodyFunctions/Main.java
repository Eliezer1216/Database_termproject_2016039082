package InBodyFunctions;

import InBodyFunctions.Login;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int mode;
        mode=FirstScreen();
        FirstScreen_Function(mode);

    }
    public static int FirstScreen()
    {

        int F_mode;
        Scanner sc=new Scanner((System.in));
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("Welcome to Software Inbody database~~~!!!!!");
        System.out.println("1. 로그인 2. 회원가입 3. 프로그램 종료");
        System.out.println("------------------------------------------------------------");
        System.out.println("\n원하는 기능의 숫자를 입력하세요 :");
        F_mode=sc.nextInt();
        return F_mode;
    }
    public static void FirstScreen_Function(int mode)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            /*do {
                mode=FirstScreen();
                if((mode!=1)&&(mode!=2)&&(mode!=3))
                    System.out.println("\n다시 입력하세요!!");
            }while((mode!=1)&&(mode!=2)&&(mode!=3));*/
            while((mode!=1)&&(mode!=2)&&(mode!=3))
            {
                mode=FirstScreen();
                if((mode!=1)&&(mode!=2)&&(mode!=3))
                    System.out.println("\n다시 입력하세요!!");
            }
            if((mode==1))
            {
                Login login=new Login();
                login.Login();

            }
            else if((mode==2))
            {
                Sign_Up signup=new Sign_Up();
                signup.sign_up();
            }
            else if((mode==3))
            {
                //프로그램 종료
            }
            // ResultSet rs=stmt.executeQuery("SELECT * FROM 회원");
            // while(rs.next())
            //System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));
            //con.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

}