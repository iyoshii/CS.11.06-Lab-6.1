import java.io.*;
import java.util.Scanner;

public class AdventureTime {

    /** This is the main method and it is where you will test your implementations for challengeOne, challengeTwo, etc.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String fileName = "inputOneTwo.txt";
        String fileName2 = "inputThreeFour.txt";

        int challengeOneAnswer = challengeOne(fileName);
        int challengeTwoAnswer = challengeTwo(fileName);
        int challengeThreeAnswer = challengeThree(fileName2);
        int challengeFourAnswer = challengeFour(fileName2);

        writeFileAllAnswers("AdventureTime.txt", challengeOneAnswer, challengeTwoAnswer, challengeThreeAnswer, challengeFourAnswer);
    }

    /** TODO 1
     *
     * Challenge 1
     *
     * @param fileName
     * @return Answer to Challenge 1
     * @throws IOException
     */
    public static int challengeOne(String fileName) throws IOException {
        int[] measurement = readFile(fileName);
        int count = 0;

        for (int i = 1; i < measurement.length; i++){
            int before = measurement[i - 1];
            int now = measurement[i];

            if (now > before){
                count++;
            }
        }

        return count;
    }

    /** TODO 2
     *
     * Challenge 2
     *
     * @param fileName
     * @return Answer to Challenge 2
     * @throws FileNotFoundException
     */
    public static int challengeTwo(String fileName) throws FileNotFoundException {
        int[] measurement = readFile(fileName);
        int count = 0;
        Integer last = null;

        for (int i = 1; i < measurement.length - 1; i++){
            int window1 = measurement[i - 1];
            int window2 = measurement[i];
            int window3 = measurement[i + 1];
            int sum = window1 + window2 + window3;

            if (last != null && sum > last){
                count++;
            }
            last = sum;
        }

        return count;
    }

    /** TODO 3
     *
     * Challenge 3
     *
     * @param fileName2
     * @return Answer to Challenge 3
     * @throws FileNotFoundException
     */
    public static int challengeThree(String fileName2) throws FileNotFoundException {
        int i = 0;
        int horizontal = 0;
        int depth = 0;
        String[] commands = readFileTwo(fileName2);

        while (i < commands.length) {
            String command = commands[i];
            String[] parts = command.trim().split("\\s+");
            String action = parts[0];
            int value = Integer.parseInt(parts[1]);

            if(action.equals("forward")) {
                horizontal += value;
            }
            else if (action.equals("down")) {
                depth += value;
            }
            else if (action.equals("up")) {
                depth -= value;
            }

            i++;
        }

        return horizontal * depth;
    }

    /** TODO 4
     *
     * Challenge 4
     *
     * @param filename2
     * @return Answer to Challenge 4
     * @throws FileNotFoundException
     */
    public static int challengeFour(String filename2) throws FileNotFoundException {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        String[] commands = readFileTwo(filename2);

        for (String command : commands) {
            String[] parts = command.trim().split("\\s+");
            String action = parts[0];
            int value = Integer.parseInt(parts[1]);

            if(action.equals("forward")) {
                horizontal += value;
                depth += aim * value;
            } else if (action.equals("down")) {
                aim += value;
            } else if (action.equals("up")) {
                aim -= value;
            }
        }

        return horizontal * depth;
    }



    /** This method will write the values passed as challengeOne, challengeTwo, challengeThree, and challengeFour to a text file.
     * Do not edit this method.
     */
    private static void writeFileAllAnswers(String outPutFilename, int challengeOne, int challengeTwo, int challengeThree, int challengeFour) throws IOException {
        File file = new File(outPutFilename);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("Challenge 1: " + "\t" + challengeOne + "\n");
        bufferedWriter.write("Challenge 2: " + "\t" + challengeTwo + "\n");
        bufferedWriter.write("Challenge 3: " + "\t" + challengeThree + "\n");
        bufferedWriter.write("Challenge 4: " + "\t" + challengeFour + "\n");
        bufferedWriter.close();
    }

    /** This method will read the values in inputFilename into an array of integers, which it will return.
     * Do not edit this method.
     */
    private static int[] readFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int numberOfLinesInFile = countLinesInFile(inputFilename);
        int[] data = new int[numberOfLinesInFile];
        int index = 0;
        while (scanner.hasNextLine()) {
            data[index++] = scanner.nextInt();
        }
        scanner.close();
        return data;
    }

    /** This method will count the number of lines in a text file, which it will return.
     * Do not edit this method.
     */
    private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
        File file = new File(inputFilename);
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        return lineCount;
    }


    public static String[] readFileTwo(String fileName2) throws FileNotFoundException  {
        String[] commands = new String[0];
        File file = new File(fileName2);

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                commands = appendToArray(commands, line);
            }
        }

        return commands;
    }

    public static String[] appendToArray(String[] arr, String element) {
        String[] newArr = new String[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = element;
        return newArr;
    }

}