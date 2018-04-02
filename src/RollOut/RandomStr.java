package RollOut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//TODO нужен рефакторинг класса

public class RandomStr {

    static String Alf = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //абвгдеёжзийклмнопрстуфхцчшщъыьэюя АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ
    static int random;
    static char[] sumb;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int leng = Integer.parseInt(reader.readLine());
        printStr(leng);
        System.out.println();
        // System.out.println(getStrD(leng));
    }

    public static void printStr(int leng) throws IOException {
        sumb = Alf.toCharArray();
        for (int i = 0; i < leng; i++) {
            random = (int) (Math.random() * Alf.length());
            System.out.print(sumb[random]);
        }
    }

    public static String getStr(int leng) {
        sumb = Alf.toCharArray();
        String line = "";
        for (int i = 0; i < leng; i++) {
            random = (int) (Math.random() * Alf.length());
            line += (sumb[random]);
        }
        return line;
    }

    public static String getStrDomain(int leng) {
        sumb = Alf.toCharArray();
        String line = "";
        for (int i = 0; i < leng; i++) {
            if (i == 63 || i == 127 || i == 191) {
                line += ".";
            } else {
                random = (int) (Math.random() * Alf.length());
                line += (sumb[random]);
            }
        }
        return line;
    }
}
