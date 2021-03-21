import Classes.InputArgs;
import Classes.FileWorker;
import Classes.Solutions;

public class Main {
    public static void main(String[] args) {

        InputArgs filesPath;

        filesPath = parseCmdArgs(args);

        boolean[][] twoDimensionalArray
                = new FileWorker(filesPath.GetInputFilePath(), filesPath.GetOutputFilePath()).getTwoDimensionalArray();

        for (boolean[] row : twoDimensionalArray) {
            for (boolean element : row) System.out.print(element + "\t");
            System.out.println();
        }

        Solutions solution = new Solutions();

        System.out.println("Max area = " + solution.FindTheBiggestTrueRectangles(twoDimensionalArray));
    }

    public static InputArgs parseCmdArgs(String[] args) {
        String  inputFilePath = null,
                outputFilePath = null;
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i].split("=")[0]) {
                    case "-help" -> {
                        System.out.printf("""
                                        Страница 1 из 1 для {-help}:
                                        %-30s\t - Список аргументов.
                                        %-30s\t - Указать путь к файлу с входными данными.
                                        %-30s\t\s
                                        %-30s\t - Указать путь к файлу для записи данных.
                                        %-30s\t\s
                                        """,
                                "{-help}", "{-i} [inputFilePath]", "{--input-file=[inputFilePath]}",
                                "{-o} [outputFilePath]", "{--output-file=[outputFilePath]}");
                        System.exit(0);
                    }
                    case "-i" -> {
                        inputFilePath = args[i + 1];
                        i++;
                    }
                    case "--input-file" -> inputFilePath = args[i].substring(args[i].indexOf('=') + 1);
                    case "-o" -> {
                        outputFilePath = args[i + 1];
                        i++;
                    }
                    case "--output-file" -> outputFilePath = args[i].substring(args[i].indexOf('=') + 1);
                    default -> throw new Exception("Некорректно заданы аргументы. " +
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