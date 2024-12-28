package zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Practicum {

    public static void main(String[] args) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("result.txt"))) {
            String line;

            // Читаем файл построчно и обновляем frequencyMap.
            while ((line = br.readLine()) != null) {
                // Удаляем пробелы и приводим к верхнему регистру для надежности
                line = line.trim().toUpperCase();

                // Проверяем, если вариант уже существует в мапе, увеличиваем счетчик
                frequencyMap.put(line, frequencyMap.getOrDefault(line, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        // Выводим результат в формате "<буква>: <количество>"
        for (char option = 'A'; option <= 'D'; option++) {
            String key = String.valueOf(option);
            int count = frequencyMap.getOrDefault(key, 0);
            System.out.println(key + ": " + count);
        }
    }
}
