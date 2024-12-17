package Pj01;

public class Main {
    public static void main(String[] args) {
        Mode mode = new Mode();

        while (true) {
            System.out.println("\n환영합니다 OOO 도서관입니다 모드를 선택해주세요!");
            System.out.println("================================================\n" + "1 : 회원모드\n2 : 관리자모드\n3 : 프로그램 종료\n" + "================================================");
            int menu1 = mode.inputNum("** 모드를 선택하세요");
            System.out.println();
            if (menu1 == 1) {
                mode.memberMode();
            } else if (menu1 == 2) {
                mode.managerMode();
            } else if (menu1 == 3) {
                System.out.println("OOO 도서관 프로그램을 종료합니다. 이용해주셔서 감사합니다:)");
                break;
            } else {
                System.out.println("모드 번호를 잘못 입력하셨습니다. 다시 입력해주세요!");
            }
        }
    }
}
