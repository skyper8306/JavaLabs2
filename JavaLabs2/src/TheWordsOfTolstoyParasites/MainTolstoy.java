package TheWordsOfTolstoyParasites;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

//"Подсчет частоты появления слов во входном потоке. Дать возможность указывать минимальную/максимальную длину слова,
// участвующую в подсчёте частоты, для фильтрации предлогов и местоимений. Использовать Java8 Stream API.
// Файл для анализа https://drive.google.com/open?id=1YnRy5H8Emx4kyA1-lLZkNuY8LDTplulu
//* Выяснить самые популярные прилагательные в тексте."

// который=161, которые=112
public class MainTolstoy {
    public static void main(String args[]) {
        int minLenWords = 3, maxLenWords = 12;
        try (Stream<String> lines = Files.lines(Paths.get("src//TheWordsOfTolstoyParasites//Tolstoy.txt"))) {

            Stream<String> words = lines.flatMap(line -> Stream.of(line.split("[^A-Za-zА-Яа-я]+"))).
                    filter(s -> s.length() > minLenWords && s.length() < maxLenWords);

            HashMap<String, Integer> countWords = words.collect(HashMap::new,
                    (k, v) -> {
                        k.put(v, k.containsKey(v) ? (k.get(v) + 1) : 1);
                    },
                    HashMap::putAll);

            countWords.entrySet().stream()
                    .sorted(HashMap.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Такого файла не существует! " + e.getLocalizedMessage());
        }
    }
}
