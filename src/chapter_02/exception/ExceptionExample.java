package chapter_02.exception;

import java.io.IOException;
import java.util.Scanner;

// 메소드의 매개변수가 음수이면 예외를 발생시키는 메소드 생성
public class ExceptionExample {
    // checked exception
    public static void ioException(int value) throws IOException {
        if (value > 0)
            return;
        else
            throw new IOException();
    }

    // unchecked exception
    public static void indexOutOfBoundsException(int value) throws IndexOutOfBoundsException {
        if (value > 0)
            return;
        else
            throw new IndexOutOfBoundsException();
    }

    // unchecked exception
    public static void nullPointException(int value) throws NullPointerException {
        if (value > 0)
            return;
        else
            throw new NullPointerException();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int value = sc.nextInt();

        try {
            ExceptionExample.ioException(value); // IOException은 checked exception 반드시 예외처리 해야한다.
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExceptionExample.indexOutOfBoundsException(value);  // unchecked exception이라 try-catch로 예외처리 하지 않아도 된다.
        ExceptionExample.nullPointException(value); // unchecked exception
    }
}
