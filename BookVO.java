package Pj01;

import java.util.SortedMap;

public class BookVO {
    private int num; //번호
    private String bookTitle; //도서 이름
    private String author; //작가
    private String publisher; //출판사
    private String bookClass; //도서 장르

    public BookVO() {
    }

    public BookVO(int num, String bookTitle, String author, String publisher, String bookClass) {
        this.num = num;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.bookClass = bookClass;
    }

    public void dataString() {
        //bookTitle 길이 맞추기
        if (bookTitle.length()<=3) {
            System.out.printf("%d\t\t%s\t\t\t\t\t", num, bookTitle);
        }
        else if (bookTitle.length() <= 6) {
            System.out.printf("%d\t\t%s\t\t\t\t", num, bookTitle);
        } else if (bookTitle.length() < 9) {
            System.out.printf("%d\t\t%s\t\t\t\t", num, bookTitle);
        } else if (bookTitle.length() < 11) {
            System.out.printf("%d\t\t%s\t\t\t", num, bookTitle);
        } else if (bookTitle.length() <= 16) {
            System.out.printf("%d\t\t%s\t\t\t", num, bookTitle);
        }

        //author 길이 맞추기
        if (author.length() < 5) {
            System.out.printf("%s\t\t\t\t", author);
        } else if (author.length() < 8) {
            System.out.printf("%s\t\t\t", author);
        } else if (author.length() < 10) {
            System.out.printf("%s\t\t\t", author);
        }

        //publisher 길이 맞추기
        if (publisher.length() < 4) {
            System.out.printf("%s\t\t\t%s\n", publisher, bookClass);
        } else if (publisher.length() < 6) {
            System.out.printf("%s\t\t%s\n", publisher, bookClass);
        } else if (publisher.length() < 100) {
            System.out.printf("%s\t\t%s\n", publisher, bookClass);
        }


    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookClass() {
        return bookClass;
    }

    public void setBookClass(String bookClass) {
        this.bookClass = bookClass;
    }
}

