import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;

    /*    a. ID (String)
          b. name (String)
          c. Description (String)
          d. cost (dbl)

     */
        String record = "";
        String id = "";
        String name = "";
        String description = "";
        double cost = 0;

        do {
            id = SafeInput.getNonZeroLenString(scan, "Enter the ID [6 digits]");
            name = SafeInput.getNonZeroLenString(scan, "Enter the product name");
            description = SafeInput.getNonZeroLenString(scan, "Enter product description");
            cost = SafeInput.getDouble(scan, "Enter product cost");

            record = id + ", " + name + ", " + description + ", " + cost;
            products.add(record);

            done = SafeInput.getYNConfirm(scan, "Are you finished?");
        } while (!done);

        try {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for (String rec : products) {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
