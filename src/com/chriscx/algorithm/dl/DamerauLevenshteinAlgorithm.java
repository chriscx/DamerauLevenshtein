/*
Copyright (C) 2013 by Christopher Cailleaux.
Permission is hereby granted, free of charge, to any person obtaining a copy of 
this software and associated documentation files (the "Software"), to deal in 
the Software without restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell copies of 
the Software, and to permit persons to whom the Software is furnished to do so, 
subject to the following conditions:
The above copyright notice and this permission notice shall be included in all 
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.chriscx.algorithm.dl;

/**
 *
 * @author Chris
 */
public class DamerauLevenshteinAlgorithm {

    private String source;
    private String target;

    /**
     * @param source
     * @param target
     */
    public DamerauLevenshteinAlgorithm(String source, String target) {

        this.source = source;
        this.target = target;
    }

    /**
     * @param a
     * @param b
     * @param c
     * @return returns minimum of 3 args
     */
    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    /**
     * @return returns the Damerau-Levenshtein distance
     */
    public int getOptimalStringAlignmentDistance() {

        int[][] matrix = new int[source.length() + 1][target.length() + 1];
        int i, j, cost;

        if (source.equals(target)) {
            return 0;
        }

        if (source.length() == 0) {
            return target.length();
        }
        if (target.length() == 0) {
            return source.length();
        }

        for (i = 0; i <= source.length(); i++) {
            matrix[i][0] = i;
        }

        for (j = 0; j <= target.length(); j++) {
            matrix[0][j] = j;
        }

        for (i = 1; i <= source.length(); i++) {
            for (j = 1; j <= target.length(); j++) {

                cost = (source.charAt(i - 1) == target.charAt(j - 1)) ? 0 : 1;

                matrix[i][j] = DamerauLevenshteinAlgorithm.minimum(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1, matrix[i - 1][j - 1] + cost);

                if ((i > 1) && (j > 1) && (source.charAt(i - 1) == target.charAt(j - 2)) && (source.charAt(i - 2) == target.charAt(j - 1))) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 2][j - 2] + cost);
                }
            }
        }

        return matrix[source.length()][target.length()];
    }
}
