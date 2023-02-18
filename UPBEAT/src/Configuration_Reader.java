import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Configuration_Reader {
    private File file;

    public Configuration_Reader(String filePath) {
        this.file = new File(filePath);
    }

    public void readFile() {
        Scanner scanner = new Scanner("configuration file.txt");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // ignore text after # symbol
            int commentIndex = line.indexOf("#");
            if (commentIndex != -1) {
                line = line.substring(0, commentIndex);
            }
            // process the line as needed
            System.out.println(line);
        }
        scanner.close();
    }
}