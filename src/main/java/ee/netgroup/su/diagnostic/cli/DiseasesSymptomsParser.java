package ee.netgroup.su.diagnostic.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DiseasesSymptomsParser {
    private HashMap<String, List<String>> hashMap = new HashMap<>();

    private static File getInputFile(final String[] arguments) {
        if (arguments.length < 1) {
            throw new IllegalArgumentException("No input file given at commandline.");
        } else {

            final File inputFile = new File(arguments[0]);

            if (!inputFile.exists()) {
                throw new IllegalArgumentException("Specified input file does not exist: " + inputFile);
            }
            if (!inputFile.canRead()) {
                throw new IllegalArgumentException("Specified input file is not readable: " + inputFile);
            }
            return inputFile;
        }
    }

    public static void main(final String[] arguments) throws IOException {
        DiseasesSymptomsParser diseasesSymptomsParser = new DiseasesSymptomsParser();
        System.out.println(diseasesSymptomsParser.getDiseasesSymptomsPairs(arguments).entrySet());
    }

    private HashMap<String, List<String>> getDiseasesSymptomsPairs(final String[] arguments) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(getInputFile(arguments)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                List<String> wordsInLine = Arrays.asList(line.split(","));
                hashMap.put(wordsInLine.get(0), wordsInLine.subList(1, wordsInLine.size()));
            }
        }
        return hashMap;
    }


}
