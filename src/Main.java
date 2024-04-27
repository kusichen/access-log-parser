import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int countPathTrue = 0;
        int allLength = 0;
        int googleRequestCount = 0;
        int yandexRequestCount = 0;
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
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                int max = 0;

                while ((line = reader.readLine()) != null) {
                    max = line.length();
                    allLength++;
                    if (max > 1024) throw new InvalidLengthException("Найдена строка длиннее " + max + " символов");
                }
                String filePath = path;
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                String[] linesArray = lines.toArray(new String[lines.size()]);

                for (String newlines : linesArray) {
                    int first = newlines.indexOf('(');
                    int second = newlines.indexOf(')') - 1;
                    if (first < second) {
                        String substring = newlines.substring(first, second);
                        String[] parts = substring.split(";");
                        if (parts.length >= 2) {
                            String fragment = parts[1];
                            String[] fragments = fragment.split(" ");

                            for (int i = 0; i < fragments.length; i++) {
                                fragments[i] = fragments[i].trim();
                            }
                            String fragment2 = Arrays.toString(fragments);
                            if (fragments.length > 0) {
                                int slash = fragment2.indexOf('/');
                                if (slash > 0) {
                                    String beforeSlash = fragment2.substring(2, slash);
                                    if (beforeSlash.contains("Googlebot")) {
                                        googleRequestCount++;
                                    } else if (beforeSlash.contains("YandexBot")) {
                                        yandexRequestCount++;
                                    }
                                }
                            }

                        }
                    }
                }

                System.out.println("Количество строк " + allLength);
                System.out.println("Количество запросов от Googlebot: " + googleRequestCount);
                System.out.println("Количество запросов от YandexBot: " + yandexRequestCount);

            } catch (InvalidLengthException ex) {
                System.err.println(ex.getMessage());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class InvalidLengthException extends RuntimeException {
        public InvalidLengthException(String message) {
            super(message);
        }
    }
}

