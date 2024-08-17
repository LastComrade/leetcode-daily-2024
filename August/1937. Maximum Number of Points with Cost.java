class Solution {
    // Time - O(N * 4 * M) ~ O(N * M)
    // Space - O(2 * N) ~ O(N)
    public long maxPoints(int[][] points) {
        int rows = points.length;
        int cols = points[0].length;
        long maxPoints = 0;
        long rowMax = 0;
        long[] prevRow = new long[cols];
        long[] currRow = new long[cols];

        // Initialize the prevRow with the first row of the matrix
        for (int j=0; j<cols; j++) {
            prevRow[j] = points[0][j];
        }

        // Process the remaining rows
        for (int i=1; i<rows; i++) {
            currRow = new long[cols];
            // Calculate the rowMax for the left-to-right of the row 
            for (int j=0; j<cols; j++) {
                if (j == 0) {
                    // First element of the row
                    rowMax = prevRow[j];
                } else {
                    rowMax = Math.max(rowMax - 1, prevRow[j]);
                }

                currRow[j] = rowMax;
            }

            // Calculate the rowMax for the right-to-left of the row
            for (int j=cols-1; j>=0; j--) {
                if (j == cols - 1) {
                    // First element of the row
                    rowMax = prevRow[j];
                } else {
                    rowMax = Math.max(rowMax - 1, prevRow[j]);
                }

                currRow[j] = Math.max(currRow[j], rowMax);
            }

            // Now we have the maximum change that the each cell of the curr row can have
            // Therefore applying it to the cells
            for (int j=0; j<cols; j++) {
                currRow[j] += points[i][j];
            }

            prevRow = currRow;
        }

        for (int j=0; j<cols; j++) {
            maxPoints = Math.max(maxPoints, prevRow[j]);
        }

        return maxPoints;
    }
}
