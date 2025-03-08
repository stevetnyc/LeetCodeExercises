class DirectorOfPhotography {

     static long getArtisticPhotographCount(int N, String C, int X, int Y) {

        int photos = 0;
        for (int i=0; i < N; i++) {
            if (C.charAt(i) == 'P' || C.charAt(i) == 'B' ) {
                char found = C.charAt(i);
                System.out.println("Found " + found + " at " + i);
                for (int j = i+1; j < N; j++) {
                    if (C.charAt(j) == 'A' && (j - i >= X && j - i <= Y )) {
                        System.out.println("Found A at " + j);
                        for(int k = j+1; k < N; k++) {
                            if (((found == 'P' && C.charAt(k) == 'B') || (found == 'B' && C.charAt(k) == 'P')) && (k - j >= X && k -j <= Y )) {
                                System.out.println("Found photo at " + k);
                                photos++;
                                break;
                            }
                        }

                    }
                }
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

