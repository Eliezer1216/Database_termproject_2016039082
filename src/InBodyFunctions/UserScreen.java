package InBodyFunctions;

import java.util.Scanner;

public class UserScreen {

    public static void F_UserScreen()
    {

        System.out.println("\n**************************************************");
        System.out.println("사용자 메뉴얼 화면 입니다!!\n");
        System.out.println("1. 인바디 결과 입력\n2. 전체 학생 건강 정보\n3. 학생 개인 건강 정보\n4. 건강 관리 필요 학생 리스트\n5. 체형별 비율 보기\n6. 로그아웃");
        System.out.println("**************************************************\n");
        System.out.println("원하는 기능을 입력하세요!!");
        int Manual_mode;
        Scanner sc=new Scanner((System.in));
        Manual_mode=sc.nextInt();
        if(Manual_mode==1)
        {
            System.out.println("사랑해요");
        }
    }
}
