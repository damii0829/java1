package ai0915;

import java.util.Scanner;

public class PasswordGenerator {
    static boolean checkPassword(String pwd){
        if(pwd.length()<8)
            return false;
        for (int i = 0; i <pwd.length(); i++) {
            char ch = pwd.charAt(i);
            if(!Character.isAlphabetic(ch))
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String password;

        System.out.print("새로운 비밀번호 입력: ");
        password = s.next();

        if(checkPassword(password))
            System.out.println("비밀번호 규칙에 잘 맞습니다.");
        else
            System.out.println("오류! 비밀번호가 규칙에 맞지 않습니다.");
        s.close();
    }
}
