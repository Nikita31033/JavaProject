import Classes.InputArgs;
import Classes.FileWorker;

public class Main {
    public static void main(String[] args) {

        InputArgs filesPath = null;

        filesPath = parseCmdArgs(args);

        boolean[][] TwoDimensionalArray
                = new FileWorker(filesPath.GetInputFilePath(), filesPath.GetOutputFilePath()).getTwoDimensionalArray();

        for (int i = 0; i < TwoDimensionalArray.length; i++) {
            for (int k = 0; k < TwoDimensionalArray[i].length; k++)
                System.out.print(TwoDimensionalArray[i][k] + "\t");
            System.out.println();
        }
    }

    public static InputArgs parseCmdArgs(String[] args) {
        String  inputFilePath = null,
                outputFilePath = null;
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i].split("=")[0]) {
                    case "-help":
                        System.out.print(
                                String.format("Страница 1 из 1 для {-help}:\n" +
                                                "%-30s\t - Список аргументов.\n" +
                                                "%-30s\t - Указать путь к файлу с входными данными.\n" +
                                                "%-30s\t \n" +
                                                "%-30s\t - Указать путь к файлу для записи данных.\n" +
                                                "%-30s\t \n",
                                        "{-help}", "{-i} [inputFilePath]", "{--input-file=[inputFilePath]}",
                                        "{-o} [outputFilePath]", "{--output-file=[outputFilePath]}"));
                        System.exit(0);

                    case "-i":
                        inputFilePath = args[i + 1];
                        i++;
                        break;
                    case "--input-file":
                        inputFilePath = args[i].substring(args[i].indexOf('=') + 1);
                        break;

                    case "-o":
                        outputFilePath = args[i + 1];
                        i++;
                        break;
                    case "--output-file":
                        outputFilePath = args[i].substring(args[i].indexOf('=') + 1);
                        break;

                    default:
                        throw new Exception("Некорректно заданы аргументы. " +
                                "Воспользуйтесь аргументом {-help}.");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        } finally {
            if (args.length == 0) {
                System.out.println("Не заданы аргументы. " +
                        "Воспользуйтесь аргументом {-help}.");
                System.exit(-1);
            }
            else if (inputFilePath == null || outputFilePath == null) {
                System.out.println(inputFilePath == null
                        ? "Не указан путь к входному файлу. "
                        : "Не указан путь к файлу вывода. ");
                System.exit(-1);
            }
        }
        return new InputArgs(inputFilePath, outputFilePath);
    }
}