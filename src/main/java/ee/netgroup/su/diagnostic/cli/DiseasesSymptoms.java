package ee.netgroup.su.diagnostic.cli;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DiseasesSymptoms extends DiseasesSymptomsParser {

    static HashMap<String, List<String>> getDiseasesSymptomsPairs(final String[] arguments) throws IOException {

        System.out.println("Type '1' if you want to get TOP-3 diseases");
        System.out.println("or type '2' if you want to get unique symptoms of diseases");
        System.out.println("or type '3' if you want to get TOP-3 symptoms");

        String userInput = inputScanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(getInputFile(arguments)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> wordsInLine = Arrays.asList(line.split(","));
                hashMap.put(wordsInLine.get(0), wordsInLine.subList(1, wordsInLine.size()));
            }
            hashMap.forEach((key, value) -> symptomsAmountPerDiseaseMap.put(key, value.size()));

            if (userInput.equals("1")) {
                // Top-3 values in HashMap
                System.out.println("TOP-3 diseases: ");
                symptomsAmountPerDiseaseMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .limit(3)
                        .forEach(System.out::println);
            }

            Map<String, Long> symptomsPopularityMap = hashMap.values().stream()
                    .flatMap(Collection::stream)
                    .collect(groupingBy(symptom -> symptom, Collectors.counting()));


            if (userInput.equals("2")) {
                // Unique values in HashMap
                System.out.println("\nUnique symptoms:");
                symptomsPopularityMap.entrySet().stream()
                        .filter(entry -> entry.getValue() == 1)
                        .forEach(entry -> System.out.println(entry.getKey()));
            }

            if (userInput.equals("3")) {
                System.out.println("\nTOP-3 symptoms:");
                symptomsPopularityMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(3)
                        .forEach(System.out::println);
            }

            return hashMap;
        }
    }

}
