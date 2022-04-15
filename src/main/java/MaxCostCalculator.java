import java.util.Arrays;

public class MaxCostCalculator {
    public static void main(String[] args) {
        int matrix[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] copy = createCopyOfMatrix(matrix);
        print2DArray(copy);
        int maxCost = calculateMaxCost(matrix);
        System.out.println(maxCost);
    }

    public static int calculateMaxCost(int[][] matrix) {
        int[][] maxCostTable = generateMaxCostTable(matrix);
        print2DArray(maxCostTable);
        return findMaxValueInLastColumn(maxCostTable);
    }

    private static int[][] generateMaxCostTable(int[][] matrix) {
        int numberOfRows = matrix.length;
        int numberOfColumns = matrix[0].length;
        int[][] maxCostTable = new int[numberOfRows][numberOfColumns];
        System.out.printf("Matrix rows x columns = %d x %d\n", numberOfRows, numberOfColumns);

        // copy first column
        for (int r = 0; r < numberOfRows; r++) {
            maxCostTable[r][0] = matrix[r][0];
        }
        // Start from the second column to calculate next stage
        for (int c = 1; c < numberOfColumns; c++) {
            for (int r = 0; r < numberOfRows; r++) {
                boolean isTop = r == 0;
                boolean isBottom = r == numberOfRows - 1;
                int current = matrix[r][c];
                int maxCost = 0;
                if (isTop) {
                    /**
                     * |1,*,0|
                     * |4,0,0|
                     * |5,0,0|
                     */
                    int left = maxCostTable[r][c - 1];
                    int leftLower = maxCostTable[r + 1][c - 1];
                    maxCost = Math.max(left, leftLower);
                } else if (isBottom) {
                    /**
                     * |1,0,0|
                     * |4,0,0|
                     * |5,*,0|
                     */
                    int left = maxCostTable[r][c - 1];
                    int leftUpper = maxCostTable[r - 1][c - 1];
                    maxCost = Math.max(left, leftUpper);
                } else {
                    //int the middle
                    /**
                     * |1,0,0|
                     * |4,*,0|
                     * |5,0,0|
                     */
                    int left = maxCostTable[r][c - 1];
                    int leftUpper = maxCostTable[r - 1][c - 1];
                    int leftLower = maxCostTable[r + 1][c - 1];
                    maxCost = Math.max(Math.max(left, leftLower), leftUpper);
                }
                maxCostTable[r][c] = current + maxCost;
            }
        }
        return maxCostTable;
    }

    private static int findMaxValueInLastColumn(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        int numberOfRows = matrix.length;
        int lastColumnIndex = matrix[0].length - 1;
        for (int r = 0; r < numberOfRows; r++) {
            max = Math.max(max, matrix[r][lastColumnIndex]);
        }
        return max;
    }

    private static int[][] createCopyOfMatrix(int[][] matrix) {
        return Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
    }

    private static void print2DArray(int[][] arr) {
        String line = "**************************************************************";
        System.out.println(line);
        Arrays.stream(arr).forEach(ar -> System.out.println(Arrays.toString(ar)));
        System.out.println(line);
    }
}
