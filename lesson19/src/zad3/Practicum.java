package zad3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Practicum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество участников: ");
        int playersNumber = scanner.nextInt();
        scanner.nextLine(); // Считываем оставшийся перевод строки

        List<String> words = readWordsFromFile("words.txt");

        // Проверяем, достаточно ли слов для участников
        if (words.size() < playersNumber) {
            System.out.println("Недостаточно слов в файле. Добавьте слова и обновите файл.");
            return; // Завершаем выполнение программы
        }

        // Перемешиваем слова случайным образом
        Collections.shuffle(words);

        // Распределяем слова по карточкам
        int wordsNumber = words.size() / playersNumber;

        for (int i = 0; i < playersNumber; i++) {
            String filename = String.format("player%s.txt", i + 1);
            List<String> subList;

            // Проверяем, достаточно ли слов для последнего игрока
            if (i == playersNumber - 1) {
                subList = words.subList(i * wordsNumber, words.size()); // Последний игрок получает все оставшиеся слова
            } else {
                subList = words.subList(i * wordsNumber, (i + 1) * wordsNumber);
            }

            writeListToFile(subList, filename);
        }

        System.out.println("Карточки готовы!");
    }

    private static List<String> readWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }
        return words;
    }

    private static void writeListToFile(List<String> list, String filename) {
        try (FileWriter writer = new FileWriter(filename, StandardCharsets.UTF_8)) {
            for (String word : list) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время записи файла.");
        }
    }
}
