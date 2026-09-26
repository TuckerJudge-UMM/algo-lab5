import java.util.Arrays;

class Unsatisfactory {
    public static String[] assignCompanies(String[][] programmer_matrix, int[][] company_matrix, String[] assignments) {
        int numProgrammers = programmer_matrix[0].length;

        //base case: everyone has been assigned a company
        if (assignments.length == numProgrammers) {
            return assignments;
        }

        // recursive case:
        int programmerIndex = assignments.length;
        String[] nextAssignments = Arrays.copyOf(assignments, assignments.length + 1);

        // grabs this programmer's most preferred company
        // then checks that it's not already taken by a previous programmer
        // if it is, goes down the list of that programmer's preferences and tries the next one
        for (int choice = 0; choice < programmer_matrix.length; choice++) {
            String company = programmer_matrix[choice][programmerIndex];

            if (!isTaken(assignments, company)) {
                nextAssignments[programmerIndex] = (programmerIndex + 1) + company;

                break;
            }
        }

        return assignCompanies(programmer_matrix, company_matrix, nextAssignments);
    }

    // Helper function to check if a company has already been assigned to a programmer
    // Loops through the assignments array
    private static boolean isTaken(String[] assignments, String company) {
        // need a refresher on how to use the enhanced for loop: https://stackoverflow.com/questions/11685305/what-is-the-syntax-of-the-enhanced-for-loop-in-java 
        for (String a : assignments) {
            if (a.endsWith(company)) {
                return true;
            }
        }

        return false;
    }

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

    // public String[] step(int[][] programmers, int[][] companies){
    //     int r,c = 0;
    //     while()
    // }

    public static void main(String[] args) {
        int[][] company_matrix = {
           // who the company prefers
           //A  B  C  D  E
            {2, 1, 5, 1, 2},
            {5, 2, 3, 3, 3},
            {1, 3, 2, 2, 5},
            {3, 4, 1, 4, 4},
            {4, 5, 4, 5, 1}
        };

        String[][] programmer_matrix = {
           // who the programmer prefers
           // 1    2    3    4    5
            {"E", "D", "D", "C", "A"},
            {"A", "E", "B", "B", "D"},
            {"D", "B", "C", "D", "B"},
            {"B", "A", "E", "A", "C"},
            {"C", "C", "A", "E", "E"}
        };

        System.out.println("Company Matrix (numeric):");
        printIntMatrix(company_matrix);

        System.out.println("\nProgrammer Matrix (string):");
        printStringMatrix(programmer_matrix);

        System.out.println("\nPairings:");

        // Assign companies to programmers without replacement
        String[] pairings = assignCompanies(programmer_matrix, company_matrix, new String[0]);

        // Print the pairings
        for (String pairing : pairings) {
            System.out.println(pairing);
        }
    }

    static void printIntMatrix(int[][] m) {
        for (int[] row : m) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    static void printStringMatrix(String[][] m) {
        for (String[] row : m) {
            for (String val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
