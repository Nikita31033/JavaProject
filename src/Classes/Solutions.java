package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solutions {
    public Solutions() { }

    public int FindTheBiggestTrueRectangles(boolean[][] Array) {
        int[][] intArray = new int[Array.length][Array[0].length];
        int[][] histograms = new int[Array.length][Array[0].length];

        int result = 0;

        // Convert boolean array into int[][]
        for (int i = 0; i < Array.length; i++)
            for (int k = 0; k < Array[i].length; k++)
                intArray[i][k] = Array[i][k] ? 1 : 0;

        FindTrueRectangle(intArray);

        // iterate over row to find maximum rectangular area
        // considering each row as histogram
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i].length; j++) {
                // if A[i][j] is 1 then add A[i-1][j]
                if (intArray[i][j] == 1 && i > 0) {
                    intArray[i][j] += intArray[i - 1][j];
                }
            }

            histograms[i] = intArray[i];

            // Update result if area with current row (as
            // last row of rectangle) is more
            result = Math.max(result, GetHistogramArea(intArray[i], intArray[i].length));
        }
        System.out.println("Histogram: ");
        for (int[] listHistogram : histograms) {
            System.out.println(Arrays.toString(listHistogram));
        }
        return result;
    }

    private void FindTrueRectangle(int[][] arr) {

        int width, height = width = 0,
            area, maxArea = area = 0,
            startI, startJ = startI = 0;

        boolean isOnlyZeroAround = true;
        boolean[] endOfArray;

        for (int i = 0; i < arr.length; i++) {
            startJ++;
            if (startJ >= arr[i].length) {
                startJ = 0;
                startI++;
            }
            i = startI;
            for (int j = 0; j < arr[i].length; j++) {
                j = startJ;
                // Update every loop
                width = height = 0;
                endOfArray = new boolean[]{i + 1 >= arr.length, j + 1 >= arr[i].length, i - 1 < 0, j - 1 < 0};
                // Find 0 around
                if (arr[i][j] == 0) {
                    // Find "1"
                    if (!endOfArray[1] && arr[i][j + 1] == 1 && (!endOfArray[2] && arr[i-1][j] == 0 || endOfArray[2])) {
                        // Find height
                        while (!endOfArray[0] && arr[i + 1][j] == 0 && arr[i][j+1] == 1) {
                            i++;
                            height++;
                            endOfArray[0] = i + 1 >= arr.length;
                        }
                        if (endOfArray[0] && arr[i][j+1] == 1) {
                            height++;
                            while (!endOfArray[1] && arr[i][j+1] == 1) {
                                width++;
                                j++;
                                endOfArray[1] = j+1 >= arr[i].length;
                            }
                        } else {
                            // Find width
                            while (!endOfArray[1] && !endOfArray[2] && arr[i][j + 1] == 0 && arr[i - 1][j + 1] == 1) {
                                width++;
                                j++;
                                endOfArray[1] = j + 1 >= arr[i].length;
                            }
                            // Check right side
                            if (!endOfArray[2] && !endOfArray[1] && arr[i - 1][j + 1] == 0 && arr[i][j + 1] == 0) {
                                i = i - height;
                                if (arr[i][j] == 1) {
                                    for (int k = 0; k < height; k++) {
                                        if (arr[i + k][j + 1] == 1)
                                            isOnlyZeroAround = false;
                                    }
                                    if (!isOnlyZeroAround) {
                                        i = startI;
                                        j = startJ;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        i = startI;
                        j = startJ;
                        break;
                    }
                    area = width * height;
                    maxArea = Math.max(area, maxArea);
                    break;
                } else {
                    width = height = 1;
                    if (!endOfArray[3] && !endOfArray[2] && !endOfArray[1]
                        && (arr[i-1][j-1] == 1 || arr[i-1][j] == 1 || arr[i-1][j+1] == 1)) {
                        i = startI;
                        j = startJ;
                        break;
                    }
                    while (!endOfArray[0] && arr[i + 1][j] == 1) {
                        height++;
                        i++;
                        endOfArray[0] = i + 1 >= arr.length;
                    }
                    if (!endOfArray[0] && arr[i+1][j] == 0) {
                        i++;
                        while (!endOfArray[1] && !endOfArray[2] && arr[i][j + 1] == 0
                                && arr[i - 1][j + 1] == 1) {
                            width++;
                            j++;
                            endOfArray[1] = j + 1 >= arr[i].length;
                        }
                    } else {
                        while (!endOfArray[1] && arr[i][j + 1] == 1) {
                            width++;
                            j++;
                            endOfArray[1] = j + 1 >= arr[i].length;
                        }
                    }
                    // Check right side
                    if (!endOfArray[2] && !endOfArray[1] && arr[i - 1][j + 1] == 0 && arr[i][j + 1] == 0) {
                        i = i - (height - 1);
                        if (arr[i][j] == 1) {
                            for (int k = 0; k < height; k++) {
                                if (arr[i + k][j + 1] == 1)
                                    isOnlyZeroAround = false;
                            }
                            if (!isOnlyZeroAround) {
                                i = startI;
                                j = startJ;
                                break;
                            }
                        }
                    } else {
                        i = startI;
                        j = startJ;
                        break;
                    }
                    area = width * height;
                    maxArea = Math.max(area, maxArea);
                    break;
                }
            }
        }
        System.out.println("Площадь: " + maxArea);
    }

    private int GetHistogramArea(int[] row, int columnsCount) {

        Stack<Integer> s = new Stack<>();

        int maxArea = 0; // Initialize max area
        int top;  // To store top of stack
        int area; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < columnsCount)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || row[s.peek()] <= row[i])
                s.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                top = row[s.peek()];  // store the top index
                s.pop();  // pop the top
                area = top * i;

                if (!s.empty())
                    area = top * (i - s.peek() - 1);
                
                maxArea = Math.max(area, maxArea);
            }
        }

        // Now pop the remaining bars from stack and
        // calculate area with every popped bar as the
        // smallest bar
        while (!s.empty()) {
            top = row[s.peek()];
            s.pop();
            area = top * i;
            if (!s.empty())
                area = top * (i - s.peek() - 1);

            maxArea = Math.max(area, maxArea);
        }

        return maxArea;
    }
}
