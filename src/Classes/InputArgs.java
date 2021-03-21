package Classes;

public class InputArgs {
    private String  inputFilePath,
                    outputFilePath;

    public InputArgs(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public String GetInputFilePath() {
        return this.inputFilePath;
    }

    public String GetOutputFilePath() {
        return this.outputFilePath;
    }
}
