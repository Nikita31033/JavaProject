package Classes;

import java.util.ArrayList;

public class Solutions {
    public Solutions() { }

    public String FindTheBiggestTrueRectangles(boolean[][] Array) {
        ArrayList<int[]> rectangles;
        int[][] intArray = new int[Array.length][Array[0].length];
        int area, maxArea = 0,
            width, height;
        StringBuilder str = new StringBuilder();
        // Convert boolean array into int[][]
        for (int i = 0; i < Array.length; i++)
            for (int k = 0; k < Array[i].length; k++)
                intArray[i][k] = Array[i][k] ? 1 : 0;

        // Get all rectangles coordinates
        rectangles = Search(intArray);
        if (rectangles.size() == 0)
            str = new StringBuilder("(-1, -1, -1, -1)");
        else {
            // Find maximum area
            for (int[] rectangle : rectangles) {
                width = (int)Math.sqrt(Math.pow(rectangle[3] - rectangle[1], 2)) + 1;
                height = (int)Math.sqrt(Math.pow(rectangle[0] - rectangle[2], 2)) + 1;
                area = width * height;
                maxArea = Math.max(area, maxArea);
            }
            // Print (x1, y1, width, height) for max area rectangles
            str.append("\nПрямоугольники, S = ").append(maxArea).append(":\n");
            for (int[] rectangle : rectangles) {
                width = (int)Math.sqrt(Math.pow(rectangle[3] - rectangle[1], 2)) + 1;
                height = (int)Math.sqrt(Math.pow(rectangle[0] - rectangle[2], 2)) + 1;
                if (width * height == maxArea)
                    str.append("(").append(rectangle[1]).append(", ").append(rectangle[0]).append(", ").append(width).append(", ").append(height).append(")\n");
            }
        }
        return str.toString();
    }

    public int WhoWon(int[][] arr) {
        int result;
        boolean isOneWin, isZeroWin = isOneWin = false;

        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1)
                    isOneWin = isOneWin || IsWon(i, j, arr);
                else if (arr[i][j] == 0)
                    isZeroWin = isZeroWin || IsWon(i, j, arr);
            }

        result = isOneWin ? isZeroWin ? 0 : 1 : isZeroWin ? -1 : 0 ;
        return result;
    }

    private boolean IsWon(int i, int j, int[][] arr) {
        int result = 1;
        int value = arr[i][j];

        boolean[] canMove = new boolean[]{i + 1 >= arr.length, j + 1 >= arr[i].length, j - 1 < 0};

        if (!canMove[1] && arr[i][j + 1] == value)
            while (!canMove[1] && arr[i][j + 1] == value) {
                j++;
                canMove[1] = j + 1 >= arr[i].length;
                if (++result == 5)
                    return true;
            }
        else if (!canMove[0] && !canMove[1] && arr[i + 1][j + 1] == value) {
            result = 1;
            while (!canMove[0] && !canMove[1] && arr[i + 1][j + 1] == value) {
                i++;
                j++;
                canMove[0] = i + 1 >= arr.length;
                canMove[1] = j + 1 >= arr[i].length;
                if (++result == 5)
                    return true;
            }
        } else if (!canMove[0] && arr[i + 1][j] == value) {
            result = 1;
            while (!canMove[1] && arr[i + 1][j] == value) {
                i++;
                canMove[0] = i + 1 >= arr.length;
                if (++result == 5)
                    return true;
            }
        } else if (!canMove[0] && !canMove[2] && arr[i + 1][j - 1] == value) {
            result = 1;
            while (!canMove[1] && arr[i + 1][j - 1] == value) {
                j--;
                i++;
                canMove[0] = i + 1 >= arr.length;
                canMove[2] = j - 1 < 0;
                if (++result == 5)
                    return true;
            }
        }
        return false;
    }

    private ArrayList<int[]> Search(int[][] arr) {
        boolean[] canMove;
        boolean goodRectangle = true;

        int[] coords; // {y1, x1, y2, x2}
        int width,
            startI, startJ = startI = 0;

        ArrayList<int[]> coordList = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                j = startJ;
                coords = new int[4];
                canMove =  new boolean[] {i+1 >= arr.length, j+1 >= arr[i].length, i-1 < 0, j-1 < 0};

                if (arr[i][j] == 1) {
                    width = 1;
                    // Take first coords
                    coords[0] = i;
                    coords[1] = j;
                    coords[2] = i;
                    coords[3] = j;
                    if (    (!canMove[2] && !canMove[3] && arr[i-1][j-1] == 1)
                         || (!canMove[3] && arr[i][j-1] == 1)
                         || (!canMove[2] && arr[i-1][j] == 1)
                         || (!canMove[0] && !canMove[3] && arr[i+1][j-1] == 1)
                         || (!canMove[0] && !canMove[1] && arr[i+1][j+1] == 1 && arr[i][j+1] != 1)) {
                        goodRectangle = false;
                        break;
                    }
                    // Find width, go right while next 1
                    while (!canMove[1] && arr[i][j + 1] == 1) {
                        width++;
                        j++;
                        canMove[1] = j + 1 >= arr[i].length;
                        // Update coords
                        coords[3] = j;
                        if (!canMove[2] && arr[i-1][j] == 1 || (!canMove[2] && !canMove[1] && arr[i-1][j+1] == 1)) {
                            goodRectangle = false;
                            break;
                        }
                    }
                    // Find height, go down while next 1
                    while (!canMove[0] && arr[i + 1][j] == 1) {
                        i++;
                        canMove[0] = i+1 >= arr.length;
                        coords[2] = i;
                        // Check and clear matrix if rectangle bad :P
                        if (!canMove[1] && arr[i][j + 1] == 1) {
                            goodRectangle = false;
                            break;
                        } else if (!canMove[1] && arr[i][j + 1] == 0)
                            for (int k = 1; k < width; k++)
                                if (arr[i][j - k] == 0) {
                                    goodRectangle = false;
                                    break;
                                }
                        // Check left side of rectangle
                        if (j - width >= 0 && arr[i][j-width] == 1 || (!canMove[0] && j - width >= 0 && arr[i+1][j-width] == 1)) {
                            goodRectangle = false;
                            break;
                        }
                    }
                    // Check place under rectangle
                    if (!canMove[0]) {
                        i++;
                        if (!canMove[1] && arr[i][j+1] == 1) {
                            goodRectangle = false;
                            break;
                        }
                        for (int k = 1; k < width; k++)
                            if (arr[i][j - k] == 1) {
                                goodRectangle = false;
                                break;
                            }
                        if (j - width > 0 && arr[i][j-width] == 1) {
                            goodRectangle = false;
                            break;
                        }
                    }
                    // Finally add goodRectangle
                    if(goodRectangle)
                        coordList.add(coords);
                }

                goodRectangle = true;
                startJ++;
                if (startJ >= arr[i].length) {
                    startJ = 0;
                    startI++;
                }
                i = startI;
            }
        }
        return coordList;
    }
}
