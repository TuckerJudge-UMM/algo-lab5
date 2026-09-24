class Unsatisfactory {
    // p1 is where c2 
    //

    public boolean isUnsatisfactory(int[][] mat, int p1Ratingc1, int p1Ratingc2, int c2ratingp1, int c2ratingp2){
        if (c2ratingp1 == 0 && c2ratingp2 == 0){ return false; }
        if (p1Ratingc1 == 0 && p1Ratingc2 == 0){ return false; }
        // P1 ranks C2 higher than C1
        // C2 ranks P1 higher than P2
        if (p1Ratingc2 > p1Ratingc1 && c2ratingp1 > c2ratingp2) { return true; }
        return false;
        // A pairing of programmers with companies is called satisfactory if it doesn't have two pairings, (P1, C1) and (P2, C2), such that:
        // 
        // 
    }
    public String[] step(int[][] programmers, int[][] companies){
        int r,c = 0;
        while()
    }
    public static void main(String[] args) {
        int[][] matrixA = {
            {2, 1, 5, 1, 2},
            {5, 2, 3, 3, 3},
            {1, 3, 2, 2, 5},
            {3, 4, 1, 4, 4},
            {4, 5, 4, 5, 1}
        };

        char[][] matrixB = {
            {'E', 'D', 'D', 'C', 'A'},
            {'A', 'E', 'B', 'B', 'D'},
            {'D', 'B', 'C', 'D', 'B'},
            {'B', 'A', 'E', 'A', 'C'},
            {'C', 'C', 'A', 'E', 'E'}
        };

        System.out.println("Matrix A (numeric):");
        printIntMatrix(matrixA);

        System.out.println("\nMatrix B (character):");
        printCharMatrix(matrixB);
    }

    static void printIntMatrix(int[][] m) {
        for (int[] row : m) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    static void printCharMatrix(char[][] m) {
        for (char[] row : m) {
            for (char val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
