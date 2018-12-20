/**
* This HelperClass contains some useful methods
* which were being used durin the coding proccess.
*/
public class HelperClass {

    /**
     * This method return the minimal value
     * from two doubles.
     * This is an alternative version for Math.min function,
     * since the latter is dealing with integers only.
     *
     * @param a first number
     * @param b second number
     * @return number the minimal value
     */
    public static double min(double a, double b) {
        if (a <= b) {
            return a;
        }
        return b;
    }

    /**
     * This method return the maximal value
     * from two doubles.
     * This is an alternative version for Math.max function,
     * since the latter is dealing with integers only.
     *
     * @param a first number
     * @param b second number
     * @return number the maximal value
     */
    public static double max(double a, double b) {
        if (a <= b) {
            return b;
        }
        return a;
    }

    /**
    * String to Ints function.
    * The function takes array of strings and converts them to ints.
    *
    * @param numbers an array of string inputs
    * @return strToInt an array of ints
    */
    public static int[] stringsToInts(String[] numbers) {
        int[] strToInt = new int[numbers.length];
        int i;

        if (numbers.length == 0) {
            return null;
        }

        for (i = 0; i < numbers.length; i++) {
            if (!isNumeric(numbers[i])) {
                continue;
            } else {
            //converting srtrings to int
            strToInt[i] = Integer.parseInt(numbers[i]);
            }
        }
        return strToInt;
    }

    /**
     * This static method returns true if a string
     * contains digits only.
     *
     * @param str the string to check
     * @return boolean true if string has only digits
     */
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
