class Unsatisfactory {
    // p1 is where c2 
    //
    public boolean isUnsatisfactory(int[][] mat, int p1Ratingc1, int p1Ratingc2, int c2ratingp1, int c2ratingp2){
        // P1 ranks C2 higher than C1
        // C2 ranks P1 higher than P2
        if (p1Ratingc2 > p1Ratingc1 && c2ratingp1 > c2ratingp2) { return true; }
        return false;
    // A pairing of programmers with companies is called satisfactory if it doesn't have two pairings, (P1, C1) and (P2, C2), such that:
    // 
    // 
    }
}
