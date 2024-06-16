import java.util.Scanner;

public class PeakColumns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = 0;
        int cols = 0;
        boolean validInput = false;

        while(!validInput){
            try{
                System.out.println("Enter the number of rows and columns separated by a comma (e.g., 2, 3).: ");
                String input = scanner.nextLine();
                String[] dimensions = input.split(",");

                row = Integer.parseInt(dimensions[0].trim());
                cols = Integer.parseInt(dimensions[1].trim());
                validInput = true;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter two integers separated by a comma (e.g., 2, 3).");
            }
        }


        int[][] matrix = new int[row][cols];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < cols; j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        findPeakColumns(matrix);

        scanner.close();
    }
    public static void findPeakColumns(int[][] matrix) {
        if (!isValidMatrix(matrix)) {
            System.out.println("Invalid matrix input.");
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check if element is maximum in its row
                boolean isRowMax = true;
                for (int k = 0; k < cols; k++) {
                    if (matrix[i][k] > matrix[i][j]) {
                        isRowMax = false;
                        break;
                    }
                }

                // Check if element is minimum in its column
                boolean isColMin = true;
                for (int k = 0; k < rows; k++) {
                    if (matrix[k][j] < matrix[i][j]) {
                        isColMin = false;
                        break;
                    }
                }

                // If both conditions are met, print peak-column information
                if (isRowMax && isColMin) {
                    System.out.println("(" + (i + 1) + ", " + (j + 1) + ") = " + matrix[i][j]);
                }
            }
        }
    }

    private static boolean isValidMatrix(int[][] matrix) {
        // Check if the matrix is non-empty
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int cols = matrix[0].length;

        // Check if all rows have the same number of columns
        for (int[] row : matrix) {
            if (row.length != cols) {
                return false;
            }
        }

        return true;
    }
}
