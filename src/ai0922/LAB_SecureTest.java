package ai0922;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LAB_SecureTest {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            String line ="";
            String Secure="";
            FileWriter fw = new FileWriter("secure.txt");

            while (true) {
                Secure = "";
                System.out.print("스파이에게 전달할 메시지: ");
                line = s.nextLine();
                if (line.equals(""))
                    break;


                for (int i = 0; i < line.length(); i++) {
                    int num = (int)line.charAt(i);
                    num += 100;
                    Secure += (char)num;
                }

                fw.write(line +="\n");
            }

            s.close();
            fw.close();

            System.out.println("--- secure.txt 암호화 완료 ---");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
