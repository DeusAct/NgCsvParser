package ee.netgroup.su.diagnostic.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private String disease;
    private String symptom;
    private String symptom1;
    private String symptom2;
    private String symptom3;
    private String symptom4;
    private String symptom5;
    private String symptom6;
    private String symptom7;
    private String symptom8;
    private String symptom9;
    private Main(String disease, String symptom, String symptom1, String symptom2, String symptom3, String symptom4, String symptom5, String symptom6, String symptom7, String symptom8, String symptom9) {
        this.disease = disease;
        this.symptom = symptom;
        this.symptom1 = symptom1;
        this.symptom2 = symptom2;
        this.symptom3 = symptom3;
        this.symptom4 = symptom4;
        this.symptom5 = symptom5;
        this.symptom6 = symptom6;
        this.symptom7 = symptom7;
        this.symptom8 = symptom8;
        this.symptom9 = symptom9;
    }

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

    public static void main(final String[] arguments) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(getInputFile(arguments)))) {

            ArrayList<Main> arrayList = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                for (String s : arr) {
                    arrayList.add(new Main(s, s, s, s, s, s, s, s, s, s, s));
                    System.out.println(s);
                }
            }
        }
    }

    @Override
    public String toString() {
        return disease + symptom + symptom1 + symptom2 + symptom3 + symptom4 + symptom5 + symptom6 + symptom7 + symptom8 + symptom9;
    }
}
