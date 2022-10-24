import java.util.List;

/**
 * This is the class to implement Coin Combination problem
 */
public class CoinCombinationHelper {

    /*
        1. (a) Use an integer matrix OPT where OPT(i,j) is the number of ways to represent total value of i with j coins
           (b) For the problem with OPT(i,j), it can be represented as the combination of following subproblems:
                The total value of i can be the combination of following sceanrios:
                    Using 0 coin with value of V_j: the problem becomes subproblem of OPT(i,j-1)
                    Using 1 coin with value of V_j: the problem becomes subproblem of OPT(i-1*V_j,j-1)
                    Using 2 coin with value of V_j: the problem becomes subproblem of OPT(i-2*V_j,j-1)
                    ...
                    Using a coin with value of V_j: the problem becomes subproblem of OPT(i-a*V_j,j-1)
                    Note: a = floor(i/V_j)
                When we reach the scenario where j = 1 or i = 0, we have a very simple subproblem

        2. Base case: when j = 1, if i mod V_j == 0, OPT(i,j) = 1, otherwise, OPT(i,j) = 0
           Recursion case:
                    Let a = floor(i/V_j)
                    OPT(i,j) = sum_(k=0,a)(OPT(i-k*V_j,j-1))
        3. Final value, integer total number of ways to have value S with K different coins, aka OPT(S,K)
        4.  For Dyanmic Programming logic, will record every subsolution in the matrix, and alway check
                   if solution already being computed by searching the matrix first
        5. For each i, j, the number of subproblem is a = floor(i/V_j)
        6. Constant
        7. Maximum subproblem needs to solve equals to number of matrix elements that we need to fill, which is S*K, so time complexity is O(S*K)
     */

    public static int getCoinCombination(int total, List<Integer> coins) {
        int k = coins.size();
        int[][] coinCombinationCount = new int[total][k];
        for (int i = 0; i < total; i++) {
            for (int j = 0; j < k; j++) {
                coinCombinationCount[i][j] = -1;
            }
        }
        return getCoinCombinationSub(total, k, coins, coinCombinationCount);
    }

    private static int getCoinCombinationSub(int i, int j, List<Integer> coins, int[][] coinCombinationCount) {
        if (i == 0) {
            // Happen to divide exactly, one combination
            return 1;
        }
        if (coinCombinationCount[i-1][j-1] > 0) {
            return coinCombinationCount[i-1][j-1];
        }
        if (j == 1) {
            coinCombinationCount[i-1][j-1] = (i % coins.get(j-1) == 0) ? 1 : 0;
            return coinCombinationCount[i-1][j-1];
        }
        int coinValue = coins.get(j-1);
        int maxCoins = i / coinValue;
        int currentCombinations = 0;
        for (int k = 0; k < maxCoins; k++) {
            currentCombinations += getCoinCombinationSub(i - k*coinValue, j-1, coins, coinCombinationCount);
        }
        coinCombinationCount[i-1][j-1] = currentCombinations;
        return currentCombinations;
    }


}
