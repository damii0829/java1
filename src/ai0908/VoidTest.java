package ai0908;

public class VoidTest {
    public static void printLine(String c, int count){
        for (int i = 0; i < count; i++) {
            System.out.print(c);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        String[] imojis = {"❤️", "🤷‍♀️", "😗", "😉", "😶", "🙄", "🥱"};
        int count = 0;

        for (int i = 0; i < 7; i++) {
            count += 10;
            printLine(imojis[i], count);
        }
    }
}
