// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 Main 모듈
// Main 모듈 기능 : 1. 프로그램 시작시 첫 화면 띄움 2. 로그인 , 회원가입 , 프로그램 종료 증 하나를 선택해 실행
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)

package InBodyFunctions;
import InBodyFunctions.Login;
import java.sql.*; // MYSQL import
import java.util.Scanner; // 입력을 받기 위한 Scanner 모듈 Import

public class Main {
    public static void main(String[] args){

        int mode; // 1. 로그인 , 2. 회원가입 , 3. 프로그램 종료 중 하나를 선택할수 있는 mode 번수 선언
        mode=FirstScreen(); // 프로그램 시작시 첫 화면을 띄움
        if((mode!=1)&&(mode!=2)&&(mode!=3)) // 모드가 1,2,3 이 아닐때
        {
            System.out.println("\n다시 입력하세요!!");
        }
        FirstScreen_Function(mode);

    }
    public static int FirstScreen() //프로그램 시작시 첫 화면을 띄움 , 그리고 mode를 반환함.
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
    public static void FirstScreen_Function(int mode) // mode 값을 받아 어떤 모드로 실행할지 결정하는 함수.
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.56.102:4567/software_InBody ", "root",
                    "7632"); // software_InBody 데이터베이스에 접근
            Statement stmt = (Statement) con.createStatement();
            while((mode!=1)&&(mode!=2)&&(mode!=3))
            {
                mode=FirstScreen();
                if((mode!=1)&&(mode!=2)&&(mode!=3)) // mode가 1,2,3아닌 잘못된 값이 입력되었다면
                {
                    System.out.println("\n다시 입력하세요!!");
                }
            }
            if((mode==1)) //1.로그인 Mode
            {
                Login login=new Login();
                login.Login();

            }
            else if((mode==2)) //2.회원가입 Mode
            {
                Sign_Up signup=new Sign_Up();
                signup.sign_up();
            }
            else if((mode==3)) //3. 프로그램 종료
            {
                con.close();
                
            }
        }catch(Exception e) // 예외 처리
        {
            System.out.println(e);
        }
    }

}