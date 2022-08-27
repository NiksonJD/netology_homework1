import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {

    static String word;
    static int numberOfAttempts = 0;

    static {
        BufferedReader br;
        {
            try {
                br = new BufferedReader(new FileReader("Harry Potter.txt"));
                String str = br.readLine();
                while (str != null) {
                    word = str;
                    //остановим цикл чтения по строкам случайным образом
                    if (Math.random() < 0.2) {
                        break;
                    }
                    str = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        // javalove --------
        System.out.println("Угадай предложение");
        int lengthWord = word.length();
        String maskWord = "-".repeat(lengthWord);
        System.out.println(maskWord);

        // --------
        // a
        // -a-a----
        // o
        // -a-a-o--
        int[] blacklist = new int[256];
        Random rand = new Random();
        do {
            int num = rand.nextInt(0, 255);
            if (blacklist[num] == num) {
                continue;
            }
            blacklist[num] = num;
            char c = (char) num;
            numberOfAttempts++;
            System.out.printf("Бот пытается угадать символ: %s Попытка номер %d \n", c, numberOfAttempts);

            // char c = input.next().charAt(0);
            // a 2
            // w -1
            if (word.indexOf(c) >= 0) {
                System.out.println("Удача");
                for (char elem : word.toCharArray()) {
                    if (elem == c) {
                        maskWord = replaceMaskLetter(c, maskWord);
                    }
                }
                System.out.println(maskWord);
            } else {
                System.out.println("Промах, поробует еще раз");
                System.out.println(maskWord);
            }
        } while (maskWord.contains("-"));
        System.out.printf("Поздравляем бот выйграл. Использовано число попыток %d из 256 возможных \n", numberOfAttempts);
    }

    /*
    -a-a---- j
    ja-a----
     */
    public static String replaceMaskLetter(char c, String maskWord) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                stringBuilder.append(c);
            } else if (maskWord.charAt(i) != '-') {
                stringBuilder.append(maskWord.charAt(i));
            } else {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }
}