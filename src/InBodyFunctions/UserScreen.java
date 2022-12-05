// 충북대학교 소프트웨어학부 학생 건강 데이터베이스 UserScreen 모듈
// UserScreen 모듈 기능 :
// 사용자 메뉴얼 모드
// 1. 인바디 결과 입력 , 2.전체 학생 건강정보 , 3.건강 관리 필요 학생 리스트 ,4. 체형 유형 보기 , 5. 로그아웃
// 작성자 : 2016039082 신효민 소프트웨어학부(Database-Term_Project)
package InBodyFunctions;

import java.util.Scanner;

public class UserScreen {

    public static int backmode; // 뒤로 가기를 위한 변수
    public static void F_UserScreen(int usernum) // 로그인한 사용자를 인수로 받아옴
    {


        System.out.println("\n**************************************************");
        System.out.println("\n사용자 메뉴얼 화면 입니다!!\n");
        System.out.println("1. 인바디 결과 입력\n2. 전체 학생 건강 정보\n3. 건강 관리 필요 학생 리스트\n4. 체형 보기\n5. 로그아웃");
        System.out.println("**************************************************\n");
        System.out.println("원하는 기능을 입력하세요!!");
        int Manual_mode; // 메뉴얼 모드 변수
        Scanner sc=new Scanner((System.in)); // 입력을 위한 스캐너 객체 선언
        Manual_mode=sc.nextInt(); // 메뉴얼 모드 입력
        if(Manual_mode==1) // 메뉴얼 모드가 1일때
        {
            Input_InBody_Result InBody=new Input_InBody_Result(); // 인바디 결과 입력으로 이동
            InBody.Input_InBody(usernum); 
        }
        else if(Manual_mode==2) // 메뉴얼 모드가 2일때 
        {
            System.out.println("전체 학생 건강 정보");
            All_Student_InBody all=new All_Student_InBody(); // 전체학생 건강 정보 화면으로 이동
            all.All_Student(usernum);
        }
        else if(Manual_mode==3) // 메뉴얼 모드가 3일때
        {
            System.out.println("건강 관리 필요 학생 리스트"); // 건강관리 필요학생 화면으로 이동
        }
        else if(Manual_mode==4) // 메뉴얼 모드가 4일때
        {
           Body_type body=new Body_type(); // 체형 유형 보기 화면으로 이동
           body.print_BodyType_InFo(usernum);
        }
        else if(Manual_mode==5) // 메뉴얼 모드가 5일때
        {
            System.out.println("로그아웃"); 
            Main main=new Main(); // 로그아웃 하고 프로그램 첫 화면으로 이동
            backmode=main.FirstScreen();
            main.FirstScreen_Function(backmode);
        }
        
    }
}
