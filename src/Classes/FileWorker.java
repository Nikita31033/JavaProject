package Classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileWorker {
    private final String  inputFilePath;
    private final String outputFilePath;

    public FileWorker(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public boolean[][] getTwoDimensionalArray() {
        boolean[][] TwoDimensionalArray = null;

        try (FileInputStream inputFileStream = new FileInputStream(inputFilePath)) {
            String[] ArrayData;
            StringBuilder FileTextContainer = new StringBuilder();
            byte[] bytes = inputFileStream.readAllBytes();

            for (byte element : bytes)
                FileTextContainer.append((char) element);

            ArrayData = FileTextContainer.toString().split("\\s\\n?");

            TwoDimensionalArray = new boolean[Integer.parseInt(ArrayData[0])][Integer.parseInt(ArrayData[1])];

            for (int i = 0; i < TwoDimensionalArray.length; i++)
                for (int k = 0; k < TwoDimensionalArray[i].length; k++)
                    TwoDimensionalArray[i][k] = Boolean.parseBoolean(ArrayData[i * TwoDimensionalArray[i].length + k + 2]);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return TwoDimensionalArray;
    }

    public int[][] getGameField() {
        int[][] gameField = null;

        try (FileInputStream inputFileStream = new FileInputStream(inputFilePath)) {
            Scanner reader = new Scanner(inputFileStream);
            int width = reader.nextInt(),
                height = reader.nextInt();

            gameField = new int[width][height];

            for (int i = 0; i < gameField.length; i++)
                for (int k = 0; k < gameField[i].length; k++)
                    gameField[i][k] = reader.nextInt();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return gameField;
    }

    public void WriteInFile(String str) {
        try (FileOutputStream outputFileStream = new FileOutputStream(outputFilePath)) {
            byte[] data;
            data = str.getBytes();
            outputFileStream.write(data, 0, data.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
