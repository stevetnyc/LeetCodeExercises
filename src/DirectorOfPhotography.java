class DirectorOfPhotography {
//
//    static int returnIndex(String C, char target) {
//        for (int i = 0; i < C.length()) {
//            if (C.charAt(i) == target) return i;
//        }
//        return 0;
//    }

     static long getArtisticPhotographCount(int N, String C, int X, int Y) {

        int photos = 0;
        int[] P = new int[N+1];
        int[] B = new int[N+1];

        //initialize arrays with running count of key at each index
         for (int i = 1; i <=N; i++){
             char found = C.charAt(i-1);
             P[i] = P[i-1] + ((found == 'P') ? 1 : 0);
             B[i] = B[i-1] + ((found == 'B') ? 1 : 0);
         }

         for (int i = 0; i <N; i++) {
             if(C.charAt(i) == 'A') {
                 int frontStart = Math.min((i + X), N);
                 int frontEnd = Math.min(i + Y + 1, N);
                 int backEnd = Math.max(i - X + 1, 0);
                 int backStart = Math.max(i - Y, 0);
                 photos += (P[frontEnd] - P[frontStart]) * (B[backEnd] - B[backStart]);
                 photos += (B[frontEnd] - B[frontStart]) * (P[backEnd] - P[backStart]);

             }

         }
         return photos;
    }
    public static void main(String[] args) {

        /*
            N = 5
            C = APABA
            X = 1
            Y = 2
         */
        System.out.println(getArtisticPhotographCount(5, "APABA", 1, 2));

    }
}

