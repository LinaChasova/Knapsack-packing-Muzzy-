import java.util.*;

/**
 * Created by AlinaCh on 21.02.2017.
 */
public class Knapsack {

    /**
     * algorithm solves 0-1 Knapsack problem, it chooses the best option of either placing item
     * in the bag or not for increasing capacity until reaching the limit and getting the best value
     * @param value something to be put in knapsack
     * @param weight the 'price' of relative things in knapsack
     * @param capacity the maximum weight knapsack could store
     * @return the maximum sum of values that could be stored in knapsack with given capacity
     */
    public Integer knapsack(ArrayList<Integer> value, ArrayList<Integer> weight, int capacity) {
        int[][] sol = new int[value.size() + 1][capacity + 1];
        for (int i = 0; i <= value.size(); i++)
            for (int j = 0; j <= capacity; j++) {
                if (i == 0)
                    sol[0][i] = 0;
                else if (weight.get(i - 1) > j)
                    sol[i][j] = sol[i-1][j];
                else
                    sol[i][j] = max(sol[i-1][j], sol[i-1][j-weight.get(i - 1)] + value.get(i - 1));
            }
        return sol[value.size()][capacity];
    }

    /**
     * finds max value of two integers
     * @param a integer
     * @param b integer
     * @return maximum value
     */
    private int max(int a, int b) {
        return (a > b)? a : b;
    }
}
