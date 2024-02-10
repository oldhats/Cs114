package edu.njit.cs114;

import java.util.Arrays;

/**
 * Author: Ravi Varadarajan
 * Date created: 2/7/2024
 */
public class LabCashRegister {

    private static final int INFINITY = Integer.MAX_VALUE;

    private int [] denominations;

    /**
     * Constructor
     * @param denominations values of coin types with no particular order
     * @throws Exception when a coin of denomination 1 does not exist
     */
    public LabCashRegister(int [] denominations) throws Exception {
        /**
         * Complete code here
         */
        // check if the array contains a coin of denomination 1
        boolean oneInArray = false;

        // if it does, copy the array to the denominations array
        for (int denomination : denominations) {
            if (denomination == 1) {
                oneInArray = true;
                break;
            }
        }


        if (oneInArray) {
        this.denominations = Arrays.copyOf(denominations, denominations.length);
        } else {
            throw new Exception("Unit value missing");
        }

    }

    // make an array of same size as denominations array with each entry
    // equal to nCoins
    public int [] makeCoinsArray(int nCoins) {
        int [] changes = new int[denominations.length];
        for (int i=0; i < changes.length; i++) {
            changes[i] = nCoins;
        }
        return changes;
    }

    /**
     * Recursive function that finds the minimum number of coins to make change for the given
     * value using only denominations that are in indices
     * startDenomIndex, startDenomIndex+1,.....denominations.length-1 of the
     * denominations array.The coins used in a denomination should not exceed
     * the available coins for that denomination
     * @param availableCoins
     * @param startDenomIndex
     * @param remainingValue
     * @return
     */
    private int minimumCoinsForChange(int [] availableCoins,
                                      int startDenomIndex, int remainingValue) {
        /**
         * Complete code
         */
        // check if remaining value is 0
        if (startDenomIndex == denominations.length) {
            if (remainingValue == 0) {
                return 0;
            }
            // if remaining value is greater than 0, return infinity
            if (remainingValue > 0) {
                return INFINITY;
            }
        }

        // find the maximum number of coins for the current denomination
        int maxDenomCoins = remainingValue / denominations[startDenomIndex];
        int minCoins = INFINITY;

        // try all possible number of coins for the current denomination
        for (int nCoins = 0; nCoins <= maxDenomCoins; nCoins++) {
            if (nCoins <= availableCoins[startDenomIndex]) {
                int newRemainingValue = remainingValue - nCoins * denominations[startDenomIndex];
                int coinsNeeded = minimumCoinsForChange(availableCoins, startDenomIndex + 1, newRemainingValue);

                if (coinsNeeded != INFINITY) {
                    minCoins = Math.min(minCoins, nCoins +coinsNeeded);
                }
            }
        }

        // return the minimum number of coins needed to make change
        return minCoins == INFINITY ? INFINITY : minCoins;
    }

    /**
     * Wrapper function that finds the minimum number of coins to make change
     * for the given value with restrictions on number of coins available.
     * The coins used in a denomination should not exceed the available coins
     * for that denomination.
     * @param availableCoins
     * @param value value for which to make change
     * @return
     */
    public int minimumCoinsForChange(int [] availableCoins, int value) {
        // check if the value is greater than the total available coins
        int totalAvailable = 0;
        for (int i = 0; i < denominations.length; i++) {
            totalAvailable += denominations[i] * availableCoins[i];
        }
        if (value > totalAvailable) {
            return INFINITY;
        }
        return minimumCoinsForChange(availableCoins,0,value);
    }

    /**
     * Wrapper function that finds the minimum number of coins to make change for the given value
     * @param value
     * @return
     */
    public int minimumCoinsForChange(int value) {
        return minimumCoinsForChange(makeCoinsArray(INFINITY), 0, value);
    }

    public static void main(String [] args) throws Exception {
        LabCashRegister reg = null;
        try {
            new LabCashRegister(new int[]{50, 25, 10, 5});
            assert false;
        } catch (Exception e) {
            assert true;
        }
        reg = new LabCashRegister(new int [] {25, 1, 50, 5, 10});
        // should return a total of 7 coins
        int nCoins = reg.minimumCoinsForChange(98);
        System.out.println("Minimum coins to make change for " + 98
                + " from {25,1,50,5,10} = "+ nCoins);
        assert nCoins == 7;
        // should return a total of 5 coins
        nCoins = reg.minimumCoinsForChange(58);
        System.out.println("Minimum coins to make change for " + 58
                + " from {25,1,50,5,10} = "+ nCoins);
        assert nCoins == 5;
        reg = new LabCashRegister(new int [] {25, 10, 1});
        // should return a total of 7 coins
        nCoins = reg.minimumCoinsForChange(34);
        System.out.println("Minimum coins to make change for " + 34
                + " from {25,10,1} = "+ nCoins);
        assert nCoins == 7;
        reg = new LabCashRegister(new int [] {7, 24, 1, 42});
        // should return a total of 2 coins
        nCoins = reg.minimumCoinsForChange(48);
        System.out.println("Minimum coins to make change for " + 48
                + " from {7,24,1,42} = "+ nCoins);
        assert nCoins == 2;
        reg = new LabCashRegister(new int [] {50, 1, 3, 16, 30});
        // should return a total of 3 coins
        nCoins = reg.minimumCoinsForChange(35);
        System.out.println("Minimum coins to make change for " + 35
                + " from {50,1,3,16,30} = "+ nCoins);
        assert nCoins == 3;
        reg = new LabCashRegister(new int [] {50, 25, 10, 5, 1});
        // should return a total of 15 coins
        nCoins = reg.minimumCoinsForChange(new int [] {2, 3, 20, 100, 100}, 243);
        System.out.println("Minimum coins to make change for " + 243
                + " from {50, 25, 10, 5, 1}} with available coins = "
                + Arrays.toString(new int [] {2, 3, 20, 100, 100}) + " = " + nCoins);
        assert nCoins == 15;
        reg = new LabCashRegister(new int [] {1, 7, 24, 42});
        // should return a total of 7 coins
        nCoins = reg.minimumCoinsForChange(new int [] {100, 20, 1, 1}, 48);
        System.out.println("Minimum coins to make change for " + 48
                + " from {1, 7, 24, 42}} with available coins = "
                + Arrays.toString(new int [] {100, 20, 1, 1}) + " = " + nCoins);
        assert nCoins == 7;
    }
}

