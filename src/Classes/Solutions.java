package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solutions {
    public Solutions() { }
    private int[] coords = new int[4];
    public int FindTheBiggestTrueRectangles(boolean[][] Array) {

        ArrayList<int[]> listHistograms = new ArrayList<>();
        // Convert boolean array into int[][]
        int[][] intArray = new int[Array.length][Array[0].length];
        for (int i = 0; i < Array.length; i++)
            for (int k = 0; k < Array[i].length; k++)
                intArray[i][k] = Array[i][k] ? 1 : 0;

        // Calculate area for first row and initialize it as
        // result
        int result = 0;

        // iterate over row to find maximum rectangular area
        // considering each row as histogram
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i].length; j++) {
                // if A[i][j] is 1 then add A[i-1][j]
                if (intArray[i][j] == 1 && i > 0) {
                    intArray[i][j] += intArray[i - 1][j];
                }
            }

            listHistograms.add(intArray[i]);

            // Update result if area with current row (as
            // last row of rectangle) is more
            result = Math.max(result, GetHistogramArea(intArray[i], intArray[i].length));
        }
        System.out.println("Histogram: ");
        for (int[] listHistogram : listHistograms) {
            System.out.println(Arrays.toString(listHistogram));
        }
        return result;
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
