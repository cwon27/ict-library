package Pj01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mode {
    //회원 모드와 관리자 모드 구현 클래스
    private String id = "0000";
    private String pw = "0000";
    private List<BookVO> list = new ArrayList<BookVO>();
    Scanner s = new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Mode() {
        //기본데이터 넣어두기
        list.add(new BookVO(1, "불변의 법칙", "모건 하우절", "서삼독", "경제"));
        list.add(new BookVO(2, "허송세월", "김훈", "나남출판", "에세이"));
        list.add(new BookVO(33, "진짜 나를 찾아라", "법정", "샘터사", "에세이"));
        list.add(new BookVO(43, "불편한 편의점", "김호연", "나무옆의자", "소설"));
        list.add(new BookVO(5, "불편한 편의점2", "김호연", "나무옆의자", "소설"));
        list.add(new BookVO(6, "나미야 잡화점의 기적", "히가시노 게이고", "현대문학", "소설"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    //입력 메소드(정수)
    public int inputNum(String msg) {
        System.out.print(msg + " => ");
        return s.nextInt();
    }

    //입력 메소드(String)
    public String inputString(String msg) {
        System.out.print(msg + " => ");
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //관리자 정보 변경 메소드
    public void managerSet(String id, String pw) {
        setId(id);
        setPw(pw);
    }

    //번호 오름차순 정렬 내부 클래스
    class CompareNumAsc implements Comparator<BookVO> {
        @Override
        public int compare(BookVO b1, BookVO b2) {
            //올림차순 정렬 : 두 개 값 빼면 음수,0 -> 안바꿈// 양수 -> 바꿈
            return (b1.getNum() > b2.getNum()) ? 1 : (b1.getNum() < b2.getNum() ? -1 : 0);
        }
    }

    //도서 위쪽 메소드
    public void listTop() {
        System.out.println("\n\t\t\t\t\t\t\t   ** 도서 목록 **");
        System.out.println("==========================================================================================================================");
        System.out.println("번호" + "\t\t" + "도서 이름" + "\t\t\t\t" + "작가" + "\t\t\t\t" + "출판사" + "\t\t\t" + "도서 분야");
        System.out.println("==========================================================================================================================");
    }

    //list 보여주는 메소드
    public void BookList() {
        listTop();

        Collections.sort(list, new CompareNumAsc());
        for (BookVO vo : list) {
            vo.dataString();
        }
        System.out.println("\n** 총 도서 보유량 => " + list.size() + "\n");
    }

    //list 삽입 메소드
    public void insertList(int n, String bt, String a, String p, String bc) {
        //도서 번호가 중복인지 확인
        int cnt = 0;
        for (int i = 0; i < list.size(); i++) {
            if (n == list.get(i).getNum()) {
                System.out.println("[ Error : 도서 번호가 중복입니다! ]");
                break;
            }
            cnt++;
        }

        if (cnt == list.size()) { //for문이 다 돌아갔다는 뜻 같으면 중복수 X
            list.add(new BookVO(n, bt, a, p, bc));
            System.out.println("[ 새로운 도서 등록이 완료되었습니다! ]");
            BookList();
        }
    }

    //list 검색 모드 메소드
    public void search() {
        do {
            System.out.println("\n[ 도서 검색을 시작합니다.]");
            int menu2 = inputNum("** 검색하고자 하는 항목을 입력하세요 [ 1 : 도서 이름, 2 : 작가명, 3 : 도서 장르, 4 : 목록으로 돌아가기 ]");
            if (menu2 == 1) {
                //도서 이름로 검색
                System.out.print("* 찾고자 하는 도서 이름 : ");
                searchList("1");
            } else if (menu2 == 2) {
                //작가명으로 검색
                System.out.print("* 찾고자 하는 도서 작가명 : ");
                searchList("2");
            } else if (menu2 == 3) {
                //출판사명으로 검색
                System.out.print("* 찾고자 하는 도서 장르명 : ");
                searchList("3");
            } else if (menu2 == 4) {
                //목록으로 돌아가기
                System.out.println();
                break;
            } else {
                System.out.println("[ 검색 항목 번호를 잘못 입력하셨습니다. 다시 입력해주세요! ]");
            }
            String continueMsg = inputString("[ 도서를 더 검색하시겠습니까? (Y/N) ] : ");
            if (continueMsg.equals("N")) {
                System.out.println("[ 도서 검색을 종료합니다 ]\n");
                break;
            } else if (continueMsg.equals("Y")) {

            } else {
                System.out.println("[ Error : 잘못 입력하셨습니다. 도서 검색을 종료합니다. ]\n");
                break;
            }
        } while (true);
    }

    //list 검색 출력 메소드
    public void searchList(String type) {
        // type 1 : 도서 이름
        // type 2 : 작가명
        // type 2 : 출판사명
        List<BookVO> tmpList = new ArrayList<BookVO>();
        String keyword = null;
        try {
            keyword = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (type.equals("1")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getBookTitle().contains(keyword)) {
                    tmpList.add(list.get(i));
                }
            }
            if (tmpList.isEmpty()) {
                System.out.println("[ 해당 도서가 존재하지 않습니다! ]");
            } else if (!tmpList.isEmpty()) {
                listTop();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBookTitle().contains(keyword)) {
                        list.get(i).dataString();
                    }
                }
                System.out.println("\n** 검색 도서 보유량 => " + tmpList.size() + "\n");
            }
        } else if (type.equals("2")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAuthor().contains(keyword)) {
                    tmpList.add(list.get(i));
                }
            }
            if (tmpList.isEmpty()) {
                System.out.println("[ 해당 도서가 존재하지 않습니다! ]");
            } else if (!tmpList.isEmpty()) {
                listTop();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAuthor().contains(keyword)) {
                        list.get(i).dataString();
                    }
                }
                System.out.println("\n** 검색 도서 보유량 => " + tmpList.size() + "\n");
            }
        } else if (type.equals("3")) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getBookClass().contains(keyword)) {
                    tmpList.add(list.get(i));
                }
            }
            if (tmpList.isEmpty()) {
                System.out.println("[ 해당 도서가 존재하지 않습니다! ]");
            } else if (!tmpList.isEmpty()) {
                listTop();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBookClass().contains(keyword)) {
                        list.get(i).dataString();
                    }
                }
                System.out.println("\n** 검색 도서 보유량 => " + tmpList.size() + "\n");
            }
        }
    }

    //list 수정 메소드
    public void modifyList() {
        do {
            System.out.println("\n[ 도서 수정을 시작합니다.]");
            int selNum = inputNum("**** 수정하고싶은 도서를 검색하세요\n( 1 : 도서 번호로 검색 2 : 도서 이름으로 검색)");
            //1. 도서 번호로 검색하는 경우
            if (selNum == 1) {
                int modNum = inputNum("*** 수정할 도서의 번호를 입력하세요");
                List<BookVO> tmpList = new ArrayList<BookVO>();
                int index = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (modNum == list.get(i).getNum()) {
                        tmpList.add(list.get(i)); // num 번호가 있는 경우 add
                        index = i;
                    }
                }
                if (!tmpList.isEmpty()) {
                    int menu2 = inputNum("** 수정하고자 하는 항목을 입력하세요 [ 1 : 도서 이름, 2 : 작가명, 3 : 출판사, 4 : 도서 장르, 5 : 목록으로 돌아가기 ]");
                    if (menu2 == 1) {
                        //도서 이름을 수정
                        String modTitle = inputString("* 수정할 도서명");
                        list.get(index).setBookTitle(modTitle);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
                    } else if (menu2 == 2) {
                        //작가명을 수정
                        String modAuthor = inputString("* 수정할 작가명");
                        list.get(index).setAuthor(modAuthor);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
                    } else if (menu2 == 3) {
                        //출판사명을 수정
                        String modPublisher = inputString("* 수정할 출판사명");
                        list.get(index).setPublisher(modPublisher);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
                    } else if (menu2 == 4) {
                        //도서 장르명을 수정
                        String modClass = inputString("* 수정할 장르명");
                        list.get(index).setBookClass(modClass);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
                    } else if (menu2 == 5) {
                        //목록으로 돌아가기
                        System.out.println();
                        break;
                    } else {
                        System.out.println("[ 수정하고자 하는 항목 번호를 잘못 입력하셨습니다. 다시 입력해주세요! ]");
                    }
                } else if (tmpList.isEmpty()) {
                    System.out.println("[ 해당 도서가 존재하지 않습니다! ]");
                }
                String continueMsg = inputString("[ 도서를 더 수정하시겠습니까? (Y/N) ] : ");
                if (continueMsg.equals("N")) {
                    System.out.println("[ 도서 수정을 종료합니다 ]\n");
                    break;
                } else if (continueMsg.equals("Y")) {

                } else {
                    System.out.println("[ Error : 잘못 입력하셨습니다. 도서 수정을 종료합니다. ]\n");
                    break;
                }
            }
            //2. 도서 이름으로 검색하는 경우
            else if (selNum == 2) {
                String modT = inputString("*** 수정할 도서의 이름을 입력하세요");
                List<BookVO> tmpList = new ArrayList<BookVO>();
                int index = -1;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBookTitle().contains(modT)) {
                        tmpList.add(list.get(i)); // 이름이 있는 경우 add
                    }
                }
                //데이터가 여러개 나오는 경우
                if (tmpList.size() >= 2) {
                    listTop();
                    for (int i = 0; i < tmpList.size(); i++) {
                        tmpList.get(i).dataString();
                    }
                    int indexnum = inputNum("\n*** 도서가 " + tmpList.size() + "개 존재합니다. 수정을 원하는 도서의 번호를 입력하세요");
                    for (int i = 0; i < tmpList.size(); i++) {
                        if (indexnum == tmpList.get(i).getNum()) index = i; //여기
                    }
                    if (index != -1) {
                        int menu2 = inputNum("** 수정하고자 하는 항목을 입력하세요 [ 1 : 도서 이름, 2 : 작가명, 3 : 출판사, 4 : 도서 장르, 5 : 목록으로 돌아가기 ]");
                        if (menu2 == 1) {
                            //도서 이름을 수정
                            String modTitle = inputString("* 수정할 도서명");
                            tmpList.get(index).setBookTitle(modTitle);
                            System.out.println("[ 도서 수정이 완료되었습니다! ]");
                        } else if (menu2 == 2) {
                            //작가명을 수정
                            String modAuthor = inputString("* 수정할 작가명");
                            tmpList.get(index).setAuthor(modAuthor);
                            System.out.println("[ 도서 수정이 완료되었습니다! ]");
                        } else if (menu2 == 3) {
                            //출판사명을 수정
                            String modPublisher = inputString("* 수정할 출판사명");
                            tmpList.get(index).setPublisher(modPublisher);
                            System.out.println("[ 도서 수정이 완료되었습니다! ]");
                        } else if (menu2 == 4) {
                            //도서 장르명을 수정
                            String modClass = inputString("* 수정할 장르명");
                            tmpList.get(index).setBookClass(modClass);
                            System.out.println("[ 도서 수정이 완료되었습니다! ]");
                        } else if (menu2 == 5) {
                            //목록으로 돌아가기
                            System.out.println();
                            break;
                        } else {
                            System.out.println("[ 수정하고자 하는 항목 번호를 잘못 입력하셨습니다. 다시 입력해주세요! ]");
                        }
                    } else if (index == -1) {
                        System.out.println("[ Error : 도서 번호를 잘못 입력하셨습니다. ]");
                    }
                }
                // 데이터가 1개인 경우
                else if (tmpList.size() == 1) {
                    int menu2 = inputNum("** 수정하고자 하는 항목을 입력하세요 [ 1 : 도서 이름, 2 : 작가명, 3 : 출판사, 4 : 도서 장르, 5 : 목록으로 돌아가기 ]");
                    if (menu2 == 1) {
                        //도서 이름을 수정
                        String modTitle = inputString("* 수정할 도서명");
                        tmpList.get(0).setBookTitle(modTitle);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]");
                    } else if (menu2 == 2) {
                        //작가명을 수정
                        String modAuthor = inputString("* 수정할 작가명");
                        tmpList.get(0).setAuthor(modAuthor);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]");
                    } else if (menu2 == 3) {
                        //출판사명을 수정
                        String modPublisher = inputString("* 수정할 출판사명");
                        tmpList.get(0).setPublisher(modPublisher);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]");
                    } else if (menu2 == 4) {
                        //도서 장르명을 수정
                        String modClass = inputString("* 수정할 장르명");
                        tmpList.get(0).setBookClass(modClass);
                        System.out.println("[ 도서 수정이 완료되었습니다! ]");
                    } else if (menu2 == 5) {
                        //목록으로 돌아가기
                        System.out.println();
                        break;
                    } else {
                        System.out.println("[ 수정하고자 하는 항목 번호를 잘못 입력하셨습니다. 다시 입력해주세요! ]");
                    }
                } else {
                    System.out.println("[ 해당 도서가 존재하지 않습니다! ]");
                }
                String continueMsg = inputString("[ 도서를 더 수정하시겠습니까? (Y/N) ] : ");
                if (continueMsg.equals("N")) {
                    System.out.println("[ 도서 수정을 종료합니다 ]\n");
                    break;
                } else if (continueMsg.equals("Y")) {
                } else {
                    System.out.println("[ Error : 잘못 입력하셨습니다. 도서 수정을 종료합니다. ]\n");
                    break;
                }
            } else {
                System.out.println("[ Error : 잘못 입력하셨습니다. 도서 수정을 종료합니다. ]\n");
                break;
            }
        } while (true);
    }

    //중복코드 따로 빼려고 시도 -> 흠.......어케 빼지....?
//    public void modifyListComm(int index){
//        int menu2 = inputNum("** 수정하고자 하는 항목을 입력하세요 [ 1 : 도서 이름, 2 : 작가명, 3 : 출판사, 4 : 도서 장르, 5 : 목록으로 돌아가기 ]");
//        if (menu2 == 1) {
//            //도서 이름을 수정
//            String modTitle = inputString("* 수정할 도서명");
//            list.get(index).setBookTitle(modTitle);
//            System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
//        } else if (menu2 == 2) {
//            //작가명을 수정
//            String modAuthor = inputString("* 수정할 작가명");
//            list.get(index).setAuthor(modAuthor);
//            System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
//        } else if (menu2 == 3) {
//            //출판사명을 수정
//            String modPublisher = inputString("* 수정할 출판사명");
//            list.get(index).setPublisher(modPublisher);
//            System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
//        } else if (menu2 == 4) {
//            //도서 장르명을 수정
//            String modClass = inputString("* 수정할 장르명");
//            list.get(index).setBookClass(modClass);
//            System.out.println("[ 도서 수정이 완료되었습니다! ]\n");
//        }
//    }

    //list 삭제 메소드
    public void removeList() {
        do {
            System.out.println("\n[ 도서 삭제를 시작합니다.]");
            int selNum = inputNum("**** 삭제하고싶은 도서를 검색하세요\n( 1 : 도서 번호로 검색 2 : 도서 이름으로 검색)");
            //1. 도서 번호로 검색하는 경우
            if (selNum == 1) {
                int modNum = inputNum("*** 삭제할 도서의 번호를 입력하세요");
                List<BookVO> tmpList = new ArrayList<BookVO>();
                int index = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (modNum == list.get(i).getNum()) {
                        tmpList.add(list.get(i)); // num 번호가 있는 경우 add
                        index = i;
                    }
                }
                if (!tmpList.isEmpty()) {
                    list.remove(index);
                    System.out.println("[ 도서 삭제가 완료되었습니다! ]");
                } else if (tmpList.isEmpty()) {
                    System.out.println("[ 해당 도서가 존재하지 않습니다! ]");
                }
                String continueMsg = inputString("[ 도서를 더 삭제하시겠습니까? (Y/N) ] : ");
                if (continueMsg.equals("N")) {
                    System.out.println("[ 도서 삭제를 종료합니다 ]\n");
                    break;
                } else if (continueMsg.equals("Y")) {

                } else {
                    System.out.println("[ Error : 잘못 입력하셨습니다. 도서 삭제를 종료합니다. ]\n");
                    break;
                }
            }
            //2. 도서 이름으로 검색하는 경우
            else if (selNum == 2) {
                String modT = inputString("*** 삭제할 도서의 이름을 입력하세요");
                List<BookVO> tmpList = new ArrayList<BookVO>();
                int index = -1;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBookTitle().contains(modT)) {
                        tmpList.add(list.get(i)); // 이름이 있는 경우 add
                    }
                }
                //데이터가 여러개 나오는 경우
                if (tmpList.size() >= 2) {
                    listTop();
                    for (int i = 0; i < tmpList.size(); i++) {
                        tmpList.get(i).dataString();
                    }
                    int indexnum = inputNum("\n*** 도서가 " + tmpList.size() + "개 존재합니다. 삭제를 원하는 도서의 번호를 입력하세요");
                    for (int i = 0; i < tmpList.size(); i++) {
                        if (indexnum == tmpList.get(i).getNum()) index = i;
                    }

                    if (index != -1) {
                        int indexlist = -1;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getNum() == tmpList.get(index).getNum()) indexlist = i;
                        }
                        list.remove(indexlist);
                        System.out.println("[ 도서 삭제가 완료되었습니다! ]");
                    } else if (index == -1) {
                        System.out.println("[ Error : 도서 번호를 잘못 입력하셨습니다. ]");
                    }
                }
                // 데이터가 1개인 경우
                else if (tmpList.size() == 1) {
                    for (int i = 0; i < list.size(); i++) {
                        if (tmpList.get(0).getNum() == list.get(i).getNum()) index = i;
                    }
                    list.remove(index);
                    System.out.println("[ 도서 삭제가 완료되었습니다! ]");
                } else {
                    System.out.println("[ 해당 도서가 존재하지 않습니다! ]");
                }
                String continueMsg = inputString("[ 도서를 더 삭제하시겠습니까? (Y/N) ] : ");
                if (continueMsg.equals("N")) {
                    System.out.println("[ 도서 삭제를 종료합니다 ]\n");
                    break;
                } else if (continueMsg.equals("Y")) {

                } else {
                    System.out.println("[ Error : 잘못 입력하셨습니다. 도서 삭제를 종료합니다. ]\n");
                    break;
                }
            } else {
                System.out.println("[ Error : 잘못 입력하셨습니다. 도서 삭제를 종료합니다. ]\n");
                break;
            }
        } while (true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
//회원모드
    public void memberMode() {
        while (true) {
            System.out.println("    [ 회원 페이지 ]");
            System.out.println("======================\n" + "1 : 도서 목록\n2 : 도서 검색\n3 : 목록으로 돌아가기\n" + "======================");
            int menu1 = inputNum("** 메뉴를 선택하세요");
            if (menu1 == 1) {
                //도서목록 보여주기
                BookList();
                System.out.println("[ 메뉴 선택 페이지로 돌아갑니다. ]\n");
            } else if (menu1 == 2) {
                //도서 검색
                search();
            } else if (menu1 == 3) {
                //목록으로 돌아가기
                break;
            } else {
                System.out.println("[ 메뉴 번호를 잘못 입력하셨습니다. 다시 입력해주세요! ]");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//관리자 모드
    public void managerMode() {
        System.out.println("[ 로그인 하세요 ]");
        String id = inputString("* 관리자 아이디");
        String pw = inputString("* 관리자 비밀번호");

        if (id.equals(getId()) && pw.equals(getPw())) {
            System.out.println("[ 로그인에 성공하셨습니다! ]\n");
            while (true) {
                System.out.println("   [ 관리자 페이지 ]");
                System.out.println("======================\n" + "1 : 도서 목록\n2 : 도서 등록\n3 : 도서 검색\n4 : 도서 수정\n5 : 도서 삭제\n6 : 관리자 정보 변경\n7 : 목록으로 돌아가기\n" + "======================");
                int menu1 = inputNum("** 메뉴를 선택하세요");
                if (menu1 == 1) {
                    //도서 목록
                    BookList();
                    System.out.println("[ 메뉴 선택 페이지로 돌아갑니다. ]\n");
                } else if (menu1 == 2) {
                    //도서 등록
                    do {
                        System.out.println("\n[ 도서를 등록합니다. ]");
                        int num = inputNum("책 번호");
                        String bookTitle = inputString("도서 이름");
                        String author = inputString("작가");
                        String publisher = inputString("출판사");
                        String bookClass = inputString("장르");

                        insertList(num, bookTitle, author, publisher, bookClass);

                        String continueMsg = inputString("[ 도서를 더 등록하시겠습니까? (Y/N) : ]");
                        if (continueMsg.equals("N")) {
                            System.out.println("[ 도서 등록을 종료합니다 ]\n");
                            break;
                        } else if (continueMsg.equals("Y")) {
                        } else {
                            System.out.println("[ Error : 잘못 입력하셨습니다. 도서 등록을 종료합니다. ]\n");
                            break;
                        }
                    } while (true);
                } else if (menu1 == 3) {
                    //도서 검색
                    search();
                } else if (menu1 == 4) {
                    //도서 수정
                    modifyList();
                } else if (menu1 == 5) {
                    //도서 삭제
                    removeList();
                } else if (menu1 == 6) {
                    //관리자 정보 변경
                    System.out.println("\n[ 관리자 정보를 변경합니다. ]");
                    id = inputString("변경할 아이디");
                    pw = inputString("변경할 비밀번호");
                    managerSet(id, pw);
                    System.out.println("\n[ 변경되었습니다! 다시 로그인 해주세요. ]");
                    String newId = inputString("관리자 아이디");
                    String newPw = inputString("관리자 비밀번호");
                    if (newId.equals(getId()) && newPw.equals(getPw())) {
                        System.out.println("[ 로그인에 성공하셨습니다! ]\n");
                    } else {
                        System.out.println("[ 관리자 정보가 일치하지 않습니다! 초기화면으로 이동합니다. ]\n");
                        break;
                    }
                } else if (menu1 == 7) {
                    //목록으로 돌아가기
                    break;
                } else {
                    System.out.println("[ 메뉴 번호를 잘못 입력하셨습니다. 다시 입력해주세요! ]");
                }
            }
        } else {
            System.out.println("[ 관리자 정보가 일치하지 않습니다! 초기화면으로 이동합니다. ]");
        }
    }
}

