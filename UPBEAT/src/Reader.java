import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    private File file;

    public Reader(String filePath) {
        this.file = new File(filePath);
    }

    public void readFile() {
        try {
            Scanner scanner = new Scanner(file);

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
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}