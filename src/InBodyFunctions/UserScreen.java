package InBodyFunctions;

import java.util.Scanner;

public class UserScreen {


    public static void F_UserScreen(int usernum)
    {


        System.out.println("\n**************************************************");
        System.out.println("\n사용자 메뉴얼 화면 입니다!!\n");
        System.out.println("1. 인바디 결과 입력\n2. 전체 학생 건강 정보\n3. 건강 관리 필요 학생 리스트\n4. 체형별 비율 보기\n5. 로그아웃");
        System.out.println("**************************************************\n");
        System.out.println("원하는 기능을 입력하세요!!");
        int Manual_mode;
        Scanner sc=new Scanner((System.in));
        Manual_mode=sc.nextInt();
        if(Manual_mode==1)
        {
            Input_InBody_Result InBody=new Input_InBody_Result();
            InBody.Input_InBody(usernum);
        }
        else if(Manual_mode==2)
        {
            System.out.println("전체 학생 건강 정보");
        }
        else if(Manual_mode==3)
        {
            System.out.println("건강 관리 필요 학생 리스트");
        }
        else if(Manual_mode==4)
        {
            System.out.println("체형별 비율 보기");
        }
        else if(Manual_mode==5)
        {
            System.out.println("로그아웃");
        }
    }
}
