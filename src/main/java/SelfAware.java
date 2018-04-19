import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class SelfAware implements Language {
    /**
     * Appends the provided file with the provided message
     *
     * @param sourceFile {@link String} path to a java source file
     * @param message    {@link String} message to be appended
     * @throws IOException things didn't go too well ...
     */
    public void append(String sourceFile, String message) throws IOException
    {
        Files.write(Paths.get(sourceFile), message.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);

    }

    /**
     * Counts the total number of occurrences of all Java keyword in the file.
     *
     * @param sourceFile {@link String} path to a java source file
     * @return {@link int} number of times java keyword occur in the source file.
     * @throws Exception not a java file or no file maybe ...
     */
    public int occurrences(String sourceFile) throws Exception
    {
        int wordCount = 0; // keeps count of reserved words found in source file
        final String FILE_PATH = new String(Files.readAllBytes(Paths.get(sourceFile)));
        String[] tokens  = FILE_PATH.split("\\b");
        Language.sort();


        for (int index = 0; index < ReservedWords.length; index++) {
            for ( String token : tokens)
            {
                if (token.equals(ReservedWords[index]))
                {
                    wordCount++;
                }
            }
        }

        return wordCount;

    }

    public static void main(String[] args) throws Exception {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";
        SelfAware sa = new SelfAware();
        sa.append(code,"\n//Keyword occurrences: " + sa.occurrences(code));
    }



}

//Keyword occurrences: 31