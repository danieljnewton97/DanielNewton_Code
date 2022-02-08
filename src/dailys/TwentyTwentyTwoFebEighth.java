package dailys;

public class TwentyTwentyTwoFebEighth {

    public static void main(String[] args)
    {
        System.out.println(addDigitsViaRecursion(50234));
        System.out.println(addDigitsViaMathematics(50234));
    }

    /**
     * Given an int num, where 0 <= num <= 2^31 - 1, find the summation of every digit until the result has only one digit.
     *
     * Example 1:
     *     Input: num = 38
     *     Output: 2
     *     Explanation: The process is
     *      38 --> 3 + 8 --> 11
     *      11 --> 1 + 1 --> 2
     *      Since 2 has only one digit, return it.
     * Example 2:
     *      Input: num = 0
     *      Output: 0
     *
     * @param num The number to be split and summed
     * @return the single digit summation of the numbers, also known as the Digital Root.
     */
    public static int addDigitsViaRecursion(int num) {

        // The obvious solution is to use recursion to get the result. So let us first break it up via recursion.

        /*
         * Terminating case will be when the number is a single digit, so less than 10. This could also be done using
         * remainder division. Since we use a base 10 number system then checking if the number divided by 10 has a remainder
         * of zero would also work. For example:
         * if (num / 10 == 0)
         * However I feel using less-than is preferable for the ease of reading.
         */
        if(num < 10)
        {
            return num;
        }
        // Assume 534 to be the number

        // Get a single digit. In this case we're trying to pop the 4 from the number
        int lastDigit = num % 10;

        // Get the remaining numbers
        int otherDigits = num / 10;

        /*
         * Get the summation of the lastDigit and the otherDigits. Since the other digits could be single digits or
         * multiple digits,
         */
        return addDigitsViaRecursion(lastDigit + addDigitsViaRecursion(otherDigits));
    }

    /**
     * This is the elegant solution to the problem described above. Rather than using recursion, this can actually be
     * done in O(1) time using some math tricks.
     *
     * This works on the math principal that "The original number is divisible by 9 if and only if the sum of its
     * digits is divisible by 9".
     *
     * @param num The number to be split and summed
     * @return the single digit summation of the numbers, also known as the Digital Root.
     */
    public static int addDigitsViaMathematics(int num)
    {
        if(num < 10)
        {
            return num;
        }
        if(num % 9 == 0)
        {
            return 9;
        }

        return num % 9;
    }
}
