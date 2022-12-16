package maman13;

/**
 * Ex13
 */
public class Ex13 {

    public static void main(String[] args) {
        System.out.println(
                prince(new int[][] {
                        { 2, 0, 1, 2, 3 },
                        { 8, 3, 5, 5, 4 },
                        { 8, -1, 6, 8, 7 },
                        { 3, 4, 7, 2, 4 },
                        { 2, 4, 3, 1, 2 }
                }, 0, 0));
    }

    /**
     * Given a string of 0s and 1s, return the minimum number of swaps required to
     * calculate the min amount of changes required to make the string alternating
     * between 0 and 1.
     * Time Complexity: O(n) we are iterating over the string once
     * Space Complexity: O(1) we are using 2 variables to store the
     * changes required and they are not dependent on the string length
     * 
     * @param s string of 0s and 1s
     * @return the minimum number of swaps required to make the string alternating
     */
    public static int alternating(String s) {
        int count0 = 0; // changes required when the string starts from 0
        int count1 = 0; // changes required when the string starts from 1

        for (int i = 0; i < s.length(); i++) {

            // string starts with 1 => all chars at even places should be 1 and
            // that at odd places should be 0
            if ((i % 2 == 0 && s.charAt(i) == '0') || (i % 2 != 0 && s.charAt(i) == '1'))
                count1++;

            // string starts with 0 => all chars at even places should be 0 and that at odd
            // places should be 1
            else if ((i % 2 == 0 && s.charAt(i) == '1') || (i % 2 != 0 && s.charAt(i) == '0'))
                count0++;
        }

        // return minimum of the two and divide by 2 for couples swaps
        return Math.min(count0, count1) / 2;
    }

    private static int f(int[] a, int low, int high) {
        int res = 0;
        for (int i = low; i <= high; i++)
            res += a[i];
        return res;
    }

    /**
     * A. Given an array of numbers, the function returns the maximum
     * length of a subarray that has an even sum.
     * B. Time Complexity: O(n^3) we are iterating over the array 3 times in nested
     * loops. Space Complexity: O(1) we are using constant variables that are not
     * dependent
     * on the array length.
     * 
     * 
     * @param a an array of numbers
     * @return the maximum length of a subarray that has an even sum
     */
    public static int what(int[] a) {
        int temp = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int c = f(a, i, j);
                if (c % 2 == 0) {
                    if (j - i + 1 > temp)
                        temp = j - i + 1;
                }
            }
        }
        return temp;
    }

    /**
     * A. Given an array of numbers, it returns the maximum
     * length of a subarray that has an even sum.
     * B. Time Complexity: O(n) we are iterating over the array once
     * Space Complexity: O(1) we are using constant variables to store the
     * temp and tha max sum.
     * 
     * 
     * @param a an array of numbers
     * @return the maximum length of a subarray that has an even sum
     */
    public static int whatBetter(int[] a) {
        int sum = 0; // total sum of array
        int length = a.length; // length of array
        int result = 0; // max length of subarray with even sum

        // Calculate total sum of array
        for (int i = 0; i < length; i++)
            sum += a[i];

        // If sum is even, return total length
        if (sum % 2 == 0)
            return length;

        // Find an index i such the a[i] is odd
        // and compare length of both halfs excluding
        // a[i] to find max length subarray
        for (int i = 0; i < length; i++) {
            if (a[i] % 2 != 0) {
                result = Math.max(result, Math.max(length - i - 1, i));
            }
        }

        return result;
    }

    /**
     * Recursive helper function for isWay
     * 
     * @param a       an array of positive integers
     * @param i       current index
     * @param visited array of visited indexes
     * @return true if there is a way to reach the last index of the array by moving
     */
    private static boolean recurseIsWay(int[] a, int i, int[] visited) {
        // if index is out of bounds or already visited return false
        if (i < 0 || i > a.length - 1 || visited[i] == 1)
            return false;

        // if index is the last index return true
        if (i == a.length - 1)
            return true;

        // mark index as visited and recurse
        visited[i] = 1;

        // recurse one time going forward and one time going backward
        return recurseIsWay(a, i + a[i], visited) || recurseIsWay(a, i - a[i], visited);
    }

    /**
     * Given an array of positive integers, it returns true if there is a way to
     * reach the last index of the array by moving forward or backward by the
     * value of the current index.
     * 
     * @param a an array of positive integers
     * @return true if there is a way to reach the last index of the array by moving
     *         forward or backward by the
     */
    public static boolean isWay(int[] a) {
        return recurseIsWay(a, 0, new int[a.length]);
    }

    private static boolean isInBounds(int[][] drm, int r, int c) {
        return (r >= 0 && r < drm.length) && (c >= 0 && c < drm[0].length);
    }

    private static boolean isClimable(int currentHeight, int newHeight) {
        if (currentHeight == newHeight)
            return true;
        if (currentHeight > newHeight) {
            return (currentHeight - newHeight) <= 2;
        }
        if (currentHeight < newHeight) {
            return (newHeight - currentHeight) <= 1;
        }
        return false;
    }

    private static int shortestPath(int[][] drm, int r, int c, int prev) {

        // if we are out of bounds, it means this path is not possible
        if (!isInBounds(drm, r, c)) {
            return Integer.MAX_VALUE;
        }

        // current cell height
        int height = drm[r][c];

        // if we reached cell with height -1 it means with have reached the evil
        if (height == -1) {
            return 1;
        }

        // if we reached a cell with value -2 it means we already visited it
        if (height == -2) {
            return Integer.MAX_VALUE;
        }

        if (!isClimable(prev, height)) {
            return Integer.MAX_VALUE;
        }

        System.out.println("r: " + r + " c: " + c + " height: " + height + "");

        // mark cell as visited
        drm[r][c] = -2;

        int up = shortestPath(drm, r - 1, c, height);
        int down = shortestPath(drm, r + 1, c, height);
        int right = shortestPath(drm, r, c + 1, height);
        int left = shortestPath(drm, r, c - 1, height);

        // set back to height
        drm[r][c] = height;

        int shortest = Math.min(Math.min(up, down), Math.min(left, right));
        if (shortest == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return 1 + shortest;

    }

    public static int prince(int[][] drm, int i, int j) {
        return shortestPath(drm, i, j, drm[i][j]);
    }

}