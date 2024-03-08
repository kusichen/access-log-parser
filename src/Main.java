import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int countPathTrue = 0;
        while (true) {

            System.out.println("Укажите путь");
            String path = new Scanner(System.in).nextLine();
            System.out.println("Указанный Вами путь: " + path);

            File file = new File(path);
            boolean fileExists = file.exists();

            if (!fileExists) {
                System.out.println("Файл не существует");
                continue;
            }

            boolean isDirectory = file.isDirectory();

            if (isDirectory) {
                System.out.println("Путь является директорией");
            } else {
                countPathTrue++;
                System.out.println("Путь указан верно");

                System.out.println("Это файл номер: " + countPathTrue);
            }

        }
    }
}
