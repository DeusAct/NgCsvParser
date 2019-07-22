package ee.netgroup.su.diagnostic.cli;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static ee.netgroup.su.diagnostic.cli.DiseasesSymptoms.getDiseasesSymptomsPairs;

public class DiseasesSymptomsParser {

    static HashMap<String, List<String>> hashMap = new HashMap<>();
    static HashMap<String, Integer> symptomsAmountPerDiseaseMap = new HashMap<>();
    static Scanner inputScanner = new Scanner(System.in);
    static JSONObject patientSymptom = new JSONObject();

    static File getInputFile(final String[] arguments) {
        if (arguments.length < 1) {
            throw new IllegalArgumentException("No input file given at commandline.");
        } else {

            final File inputFile = new File(arguments[0]);

            if (!inputFile.exists()) {
                throw new IllegalArgumentException("Specified input file does not exist: " + inputFile);
            } else if (!inputFile.canRead()) {
                throw new IllegalArgumentException("Specified input file is not readable: " + inputFile);
            }
            return inputFile;
        }
    }

    public static void main(final String[] arguments) throws IOException {
        //System.out.println(getDiseasesSymptomsPairs(arguments).entrySet());
        getDiseasesSymptomsPairs(arguments);
    }
}