import java.util.Arrays;

class Unsatisfactory {
    // probably going to do a constructor and put the matrices here as well as a static var that will return all the way up the trees by itself
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
            System.out.println("" + company);
            System.out.println("\n");

            if (!isTaken(assignments, company)) {
                // making it easily splitable with a delim
                nextAssignments[programmerIndex] = (programmerIndex + 1) + " " + company;
                break;
            }
        }
        // i think this works if you do a valid check here and then remove elements in the arr? and walk the matrix
        return assignCompanies(programmer_matrix, company_matrix, nextAssignments);
    }

    // Helper function to check if a company has already been assigned to a programmer
    // Loops through the assignments array
    private static boolean isTaken(String[] assignments, String company) {
        for (String a : assignments) {
            if (a.endsWith(company)) {
                return true;
            }
        }

        return false;
    }
    public static boolean assessAllPairs(int currIdx, String[] assignments, int[][] companyMatrix, String[] programmerMatrix){
        // p1 is the latest insertion at currIdx
        // p2 is all pairs listed in the assignments in front of it
        // c1 is the latest insertion company
        // c2 is compnay of the respective pairs in assignments
        int j;
        while(j<currIdx){
            assignments[j].split(" ");
            if (programmerMatrix[j].startsWith(assignments[currIdx])) { break; };
        }
        // assignments -1 idx will be the pair and then i'll remove it there if it's bad
        String currComparison = assignments[currIdx].split(" ")[0];
        for(int i = 0; i<currIdx-1;i++){
            // have to find idx of both
            int p1Ratingc1 = findPairRating(currComparison, currIdx, companyMatrix);
            int p1Ratingc2 = findPairRating(programmerMatrix[]);
            int c2ratingp1 = findPairRating();
            int c2ratingp2;
            if (!Unsatisfactory(p1Ratingc1, p1Ratingc2, c2ratingp1, c2ratingp2)){ return false; }
        }
        return true;
    }
    public static int findPairRating(String val, int idx, int[][] mat){
        for (int i = 0; i<mat.length;i++){
            if(val == mat[i][idx]){ return i; }
        }
    }


    public static boolean isUnsatisfactory(int[][] mat, int p1Ratingc1, int p1Ratingc2, int c2ratingp1, int c2ratingp2){
        // P1 ranks C2 higher than C1 && C2 ranks P1 higher than P2
        if (p1Ratingc2 > p1Ratingc1 && c2ratingp1 > c2ratingp2) { return true; }
        return false;
    }

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

        System.out.println("Company Matrix:");
        printIntMatrix(company_matrix);

        System.out.println("\nProgrammer Matrix:");
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
