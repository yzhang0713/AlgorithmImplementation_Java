/**
 * This is the class to implement String Palindrome problem
 */
public class StringPalindromeHelper {


    /*
        1. (a) Use an integer matrix to record the longest subsequence between index i and j of string s (note: i <= j)
           (b) For any two index i and j (i <= j) will check following conditions:
                    In case i and j are the same position, the longest sequence can only be 1
                    In case i and j are different position, but same character, then the longest subsequence
                        should be the longest subsequence between position i+1 and j-1 plus 2
                    In case i and j are different position, and different character, have to check two substrings
                        the one between i and j-1, and the one between i+1 and j, take the better solution of the two

        2. See code
        3. Final value, integer indicating largest subsequence between position 0 and n-1, n being the length of string
        4.  For Dyanmic Programming logic, will record every subsolution in the matrix, and alway check
                   if solution already being computed by searching the matrix first
        5. In case character at i and j are the same, number of subproblem is 1
           In case character at i and j are different, number of subproblem is 2
        6. Constant
        7. Maximum subproblem needs to solve equals to number of matrix elements that we need to fill, which is 1/2n^2, so time complexity is O(n^2)
     */

    public static int getLongestPalindromeSubSequence(String s) {
        int n = s.length();
        int[][] longestSubSeq = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                longestSubSeq[i][j] = -1;
            }
        }
        return getLongestPalindromeSubSequenceBetweenIndex(s, 0, n-1, longestSubSeq);
    }

    private static int getLongestPalindromeSubSequenceBetweenIndex(String s, int start, int end, int[][] longestSubSeq) {
        if (start > end) {
            return 0;
        }
        if (longestSubSeq[start][end] > 0) {
            return longestSubSeq[start][end];
        }
        if (start == end) {
            longestSubSeq[start][end] = 1;
            return 1;
        }
        if (s.charAt(start) == s.charAt(end)) {
            longestSubSeq[start][end] = 2 + getLongestPalindromeSubSequenceBetweenIndex(s, start+1, end-1, longestSubSeq);
        } else {
            longestSubSeq[start][end] = Math.max(getLongestPalindromeSubSequenceBetweenIndex(s, start+1, end, longestSubSeq),
                                                 getLongestPalindromeSubSequenceBetweenIndex(s, start, end-1, longestSubSeq));
        }
        return longestSubSeq[start][end];
    }

}
