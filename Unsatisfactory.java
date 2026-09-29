import java.util.Arrays;

// Sources used for help online:
// [1] https://stackoverflow.com/questions/11685305/what-is-the-syntax-of-the-enhanced-for-loop-in-java 
// [2] https://stackoverflow.com/questions/8000826/is-it-possible-to-get-only-the-first-character-of-a-string 
// [3] https://stackoverflow.com/questions/35669580/converting-char-a-to-number-0-using-java-function 

// should probably rename class
class Unsatisfactory {

    public static String[] assignPairs(String[][] programmer_matrix, int[][] company_matrix) {
        // Gets us the dimensions of the matrix
        // Used for how many pairs there shoud be and how many rankings there are
        int num_pairs = programmer_matrix[0].length;

        // Array for programmers choice in companies ranked
        int[] programmer_next_choice = new int[num_pairs];

        // Array for what programmer a company currently holds
        int[] company_current_programmer = new int[num_pairs];

        // Array for what company a programmer currently holds
        String[] programmer_current_company = new String[num_pairs];

        // Array for tracking which programmers currently hold a company
        boolean[] programmer_is_assigned = new boolean[num_pairs];

        int assigned_count = 0;

        while (assigned_count < num_pairs) {
            int programmer_index = 0;

            // Check for the first programmer who doesn't have a match yet
            for (int i = 0; i < num_pairs; i++) {
                if (programmer_is_assigned[i] == false) {
                    programmer_index = i;
                    break;
                }
            }

            // Look up which rank this programmer is trying with this round
            int rank = programmer_next_choice[programmer_index];

            String company = programmer_matrix[rank][programmer_index];

            // Advance the rank in case this try fails,
            // the next time they try the next company down
            int next_rank = rank + 1;
            programmer_next_choice[programmer_index] = next_rank;

            // Convert the company letter into a 0-based index for the company matrix
            int company_index = company.charAt(0) - 'A';

            int held_programmer = company_current_programmer[company_index];

            // Programmers start at 1
            // If the held programmer is 0, then company is not taken
            if (held_programmer == 0) {
                int programmer = programmer_index + 1;

                // Make the programmer and comanies assigned to eachother
                company_current_programmer[company_index] = programmer;
                programmer_current_company[programmer_index] = company;

                programmer_is_assigned[programmer_index] = true;

                assigned_count = assigned_count + 1;
            } else {
                int new_programmer = programmer_index + 1;

                // Company already holds a progammer, need to compare to see who the comapnay rates higher
                int new_programmer_rating = companyRating(company_matrix, company_index, new_programmer);
                int held_programmer_rating = companyRating(company_matrix, company_index, held_programmer);

                // Lower the rank the better, like golf
                // Check whether the company prefers the new programmer over its current
                if (new_programmer_rating < held_programmer_rating) {
                    // Comapny takes the new programmer
                    company_current_programmer[company_index] = new_programmer;
                    programmer_current_company[programmer_index] = company;

                    programmer_is_assigned[programmer_index] = true;

                    // Make the old progammer unnassigend
                    int held_programmer_index = held_programmer - 1;
                    programmer_is_assigned[held_programmer_index] = false;
                }
            }
        }

        String[] pairings = new String[num_pairs];

        // grab all the pairs
        for (int i = 0; i < num_pairs; i++) {
            int progammer = i + 1;
            pairings[i] = progammer + programmer_current_company[i];
        }

        return pairings;
    }

    // This is old dead code, can delete later
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
            //System.out.println("" + company);
            //System.out.println("\n");

            if (!isTaken(assignments, company)) {
                nextAssignments[programmerIndex] = (programmerIndex + 1) + company;
                break;
            }
        }
        // i think this works if you do a valid check here and then remove elements in the arr? and walk the matrix
        return assignCompanies(programmer_matrix, company_matrix, nextAssignments);
    }

    // Helper function to check if a company has already been assigned to a programmer
    // Loops through the assignments array
    private static boolean isTaken(String[] assignments, String company) {
        // need a refresher on how to use the enhanced for loop [1]
        for (String pair : assignments) {
            if (pair.endsWith(company)) {
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
        // should be static throughout
        int p1Ratingc1 = findPairRatingString(currComparison, j, companyMatrix);
        int p1 = Integer.parseInt(assignments[currIdx].split(" ")[1]);
        for(int i = 0; i<currIdx-1;i++){
            // companies have numbers
            // have to find idx of both
            String c2 = assignments[i].split(" ")[0];
            int k;
            while(k<currIdx){
                assignments[j].split(" ");
                if (programmerMatrix[j].endsWith(assignments[currIdx])) { break; };
            }
            
            int p1Ratingc2 = findPairRatingString(c2, j, companyMatrix);
            int c2ratingp1 = findPairRatingInt(p1, );
            int c2ratingp2 = findPairRatingInt();
            if (!Unsatisfactory(p1Ratingc1, p1Ratingc2, c2ratingp1, c2ratingp2)){ return false; }
        }
        return true;
    }
public static int findPairRatingString(String val, int idx, String[][] mat){
    int i;
    for (i = 0; i<mat.length;i++){
        if(val == mat[i][idx]){ break; }
    }
    return i;
}
public static int findPairRatingInt(int val, int idx, int[][] mat){
    int i;
    for (i = 0; i<mat.length;i++){
        if(val == mat[i][idx]){ break; }
    }
    return i;
}

// Helper to check if two pairings are unsatisfactory
public static boolean isUnsatisfactory(int p1Ratingc1, int p1Ratingc2, int c2ratingp1, int c2ratingp2){
    // P1 ranks C2 higher than C1 && C2 ranks P1 higher than P2
    // Inverting the comparison operators to reflect that a lower index indicates a higher preference
    if (p1Ratingc2 < p1Ratingc1 && c2ratingp1 < c2ratingp2) { return true; }
    return false;
}

// Helper to find how highly a programmer ranks a given company
public static int programmerRating(String[][] programmer_matrix, int programmerIndex, String company) {
    for (int i = 0; i < programmer_matrix.length; i++) {
        if (programmer_matrix[i][programmerIndex].equals(company)) {
            return i;
        }
    }

    return programmer_matrix.length;
}

// Helper to find how highly a company ranks a given programmer
public static int companyRating(int[][] company_matrix, int companyIndex, int programmer) {
    for (int i = 0; i < company_matrix[companyIndex].length; i++) {
        if (company_matrix[i][companyIndex] == programmer) {
            return i;
        }
    }

    return company_matrix.length;
}

// Helper to check everything is satisfactory
public static boolean isSatisfactoryPairings(String[] pairings, String[][] programmer_matrix, int[][] company_matrix) {
    // loop from first pair to one less than last
    for (int i = 0; i < pairings.length - 1; i++) {
        // loop from second pair to last
        for (int j = i + 1; j < pairings.length; j++) {

            // Needed a refresher on how to extract elements from a string [2]
            // Subtract 1 to convert to 0-based index
            int p1 = Character.getNumericValue(pairings[i].charAt(0)) - 1; 
            String c1String = pairings[i].substring(1);

            int p2 = Character.getNumericValue(pairings[j].charAt(0)) - 1;
            String c2String = pairings[j].substring(1);

            // Found help making a string into a numeric index for the company [3]
            int c1Int = c1String.charAt(0) - 'A';
            int c2Int = c2String.charAt(0) - 'A';

            // Get programmer Ratings
            int p1Ratingc1 = programmerRating(programmer_matrix, p1, c1String);
            int p1Ratingc2 = programmerRating(programmer_matrix, p1, c2String);

            int p2Ratingc1 = programmerRating(programmer_matrix, p2, c1String);
            int p2Ratingc2 = programmerRating(programmer_matrix, p2, c2String);

            // Convert back to 1-based index for companyRating function
            p1 = p1 + 1;
            p2 = p2 + 1; 

            // Get company Ratings
            int c2Ratingp1 = companyRating(company_matrix, c2Int, p1);
            int c2Ratingp2 = companyRating(company_matrix, c2Int, p2);

            int c1Ratingp1 = companyRating(company_matrix, c1Int, p1);
            int c1Ratingp2 = companyRating(company_matrix, c1Int, p2);

            // Check both ways for the pair
            boolean p1PrefersC2OverC1 = isUnsatisfactory(p1Ratingc1, p1Ratingc2, c2Ratingp1, c2Ratingp2);
            boolean p2PrefersC1OverC2 = isUnsatisfactory(p2Ratingc2, p2Ratingc1, c1Ratingp2, c1Ratingp1);

            // if one is an unsatisfactory pairing, return false
            if (p1PrefersC2OverC1 || p2PrefersC1OverC2) {
                return false;
            }
        }
    }

    return true; 
}

// Main method to run the algorithm
public static void main(String[] args) {
    // Here is where you assign company preferences
    int[][] company_matrix = {
        // who the company prefers
        //A  B  C  D  E
        {2, 1, 5, 1, 2},
        {5, 2, 3, 3, 3},
        {1, 3, 2, 2, 5},
        {3, 4, 1, 4, 4},
        {4, 5, 4, 5, 1}
    };

    // Here is where you assign programmer preferences
    // who the programmer prefers
    // 1    2    3    4    5
    String[][] programmer_matrix = {
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

    // Assign programmers and companies
    String[] pairings = assignPairs(programmer_matrix, company_matrix);

    System.out.println();
    System.out.println("\nPairings:");

    // Print the pairings
    for (String pairing : pairings) {
        System.out.println(pairing);
    }

    System.out.println();

    // Check to make sure everything is correct
    System.out.println("Is every paring satisfactory: " + isSatisfactoryPairings(pairings, programmer_matrix, company_matrix));
}

// Helper to print int matrix
static void printIntMatrix(int[][] m) {
    for (int[] row : m) {
        for (int val : row) {
            System.out.print(val + "\t");
        }
        System.out.println();
    }
}

// Helper to print string matrix
static void printStringMatrix(String[][] m) {
    for (String[] row : m) {
        for (String val : row) {
            System.out.print(val + "\t");
        }
        System.out.println();
    }
}
}
