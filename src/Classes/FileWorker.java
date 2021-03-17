package Classes;

import java.io.FileInputStream;
import java.io.IOException;

public class FileWorker {
    private String  inputFilePath,
                    outputFilePath;

    public FileWorker(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public boolean[][] getTwoDimensionalArray() {
        boolean[][] TwoDimensionalArray = null;

        try (FileInputStream inputFileStream = new FileInputStream(inputFilePath)) {
            String[] ArrayData;
            String FileTextContainer = "";
            byte[] bytes = inputFileStream.readAllBytes();

            for (byte element : bytes)
                FileTextContainer += (char)element;

            ArrayData = FileTextContainer.split("\\s\\n?");

            TwoDimensionalArray = new boolean[Integer.parseInt(ArrayData[0])][Integer.parseInt(ArrayData[1])];

            for (int i = 0; i < TwoDimensionalArray.length; i++)
                for (int k = 0; k < TwoDimensionalArray[i].length; k++)
                    TwoDimensionalArray[i][k] = Boolean.parseBoolean(ArrayData[i * TwoDimensionalArray[i].length + k + 2]);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return TwoDimensionalArray;
    }
}
