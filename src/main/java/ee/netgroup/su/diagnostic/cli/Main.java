package ee.netgroup.su.diagnostic.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static File getInputFile(final String[] arguments) {
        if (arguments.length < 1)
            throw new IllegalArgumentException("No input file given at commandline.");

        final File inputFile = new File(arguments[0]);

        if (!inputFile.exists())
            throw new IllegalArgumentException("Specified input file does not exist: " + inputFile);

        if (!inputFile.canRead())
            throw new IllegalArgumentException("Specified input file is not readable: " + inputFile);

        return inputFile;
    }

    /**
     * Starting point for our application.
     */
    public static void main(final String[] arguments) throws IOException {

        List<List<String>> records = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getInputFile(arguments)))) {


            // TODO: line parsing logic...
            String diseases;
            while ((diseases = bufferedReader.readLine()) != null) {
                String[] values = diseases.split(",");
                records.add(Arrays.asList(values));
            }

            // Count symptoms

            int lineNumber = 1;
            int symptomsCounter = 0;
            int selectedDisease = 10;

            for (List<String> diseasesSymptomsList : records) {
                for (String value : diseasesSymptomsList) {
                    if (lineNumber == selectedDisease) {
                        System.out.println("Symptoms for selected Disease: " + value);
                        symptomsCounter++;
                    }
                }
                lineNumber++;
            }

            int symptomsNumber = symptomsCounter - 1; // - disease
            System.out.println(symptomsNumber);


            //Count symptoms for each disease

        }

        }

}

