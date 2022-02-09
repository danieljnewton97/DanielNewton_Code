package dailys;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwentyTwentyTwoFebNinth {

    public static void main(String[] args)
    {
        System.out.println(findPairs(IntStream.of(3,1,4,1,5).toArray(), 2));
        System.out.println(findPairs(IntStream.of(1,2,3,4,5).toArray(), 1));
        System.out.println(findPairs(IntStream.of(1,3,1,5,4).toArray(), 0));
        System.out.println(findPairs(IntStream.of(1,1,2,2,3,3,4,4).toArray(), 0));
        System.out.println(findPairs(IntStream.of(1,1,7,7,3,3,6,6,2,2).toArray(), 0));
        System.out.println(findPairs(IntStream.of(-1,-1,1,1).toArray(), 0));
    }

    /**
     * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
     *
     * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true
     *
     * - 0 <= i < j < nums.length
     * - |nums[i] - nums[j]| == k
     *
     * Constraints given:
     * - nums.length is at least 2
     * - nums can contain positive and negative integers
     * - k will always be positive
     * @param nums an array of integers
     * @param k the difference that each k-diff pair should result in
     * @return total number, as an int, of k-diff pairs for the given array
     */
    public static int findPairs(int[] nums, int k)
    {
        int uniquePairs = 0;
        /*
         * Since one of our constraints is that nums will always have at least 2 elements, we can bypass checking
         * the array for emptiness
         */

        /*
         * Seems like we could check if the length of the array is 2 and just return one if the diff of the two elements
         * is equal to k. Ideally our algorithm below should handle this all the same, but lets just go ahead and knock
         * out that simple edge case/
         */
        if(nums.length == 2)
        {
            return Math.abs(nums[0] - nums[1]) == k ? 1 : 0;
        }

        if(k == 0)
        {
            return getUniqueCountOfMatchingPairs(nums);
        }

        /*
         * Convert the array to a list of unique numbers because we do not have a constraint saying that we cannot have
         * multiple of the same int in the array.
         *
         * Sort the elements so that we can make assumptions about the ordering to make our looping simpler.
         */
        List<Integer> uniqueAndSortedNums = Arrays.stream(nums).distinct().boxed().sorted().collect(Collectors.toList());

        /*
         * Since our collection is ordered and we know it does not contain duplicates then we know we can just check
         * from the current element to the end of the list.
         *
         * For example, if our array is [1,2,3,4,5], then first check 1 - 2, 1 - 3, ..., 1 - 5. Since we're taking the
         * absolute value, we know that 1 - 2 and 2 - 1 will give us the same result so there is no need to check that
         * pairing. Since our array is sorted we know everything before the current element does not need to get
         * checked.
         */
        for(int i = 0; i < uniqueAndSortedNums.size(); i++)
        {
            for(int j = i + 1 ; j < uniqueAndSortedNums.size(); j++)
            {
                if(Math.abs(uniqueAndSortedNums.get(i) - uniqueAndSortedNums.get(j)) == k)
                {
                    uniquePairs++;
                }
            }
        }

        return uniquePairs;
    }

    /**
     * Get the count of distinct matching pairs from an array.
     *
     * For example, if an array contains [1,1,1,1], this would be one distinct pair of 1's. Likewise if an array
     * contains [1,1,2,2,3,3] this would be three distinct pairs: 1's, 2's, and 3's.
     *
     * This is to be used when k == 0 because you can only have a difference of 0 when two pairs are identical.
     * @param nums the number array to be checked for pairs
     * @return the count of matching unique pairs
     */
    private static int getUniqueCountOfMatchingPairs(int[] nums) {
        Map<Integer, Integer> numsByCount = new HashMap<>();
        for (int num : nums)
        {
            numsByCount.put(num, numsByCount.getOrDefault(num, 0) + 1);
        }
        return (int) numsByCount.values().stream().filter(value -> value > 1).count();
    }
}
