package maman11;

import java.util.Scanner;

public class Triangle2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(
                "This program determines if a triangle is valid"
                        + " and if it is, what kind of triangle it is.");

        System.out.println("Please enter the three lengths"
                + " of the triangle's sides");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        // checking if the triangle is valid by the triangle inequality theorem
        // and all side lengths are positive
        if (a + b <= c || a + c <= b || b + c <= a || a < 0 || b < 0 || c < 0) {
            System.out.println("The numbers: " + a + ", " + b + " and " + c + " " + "cannot represent a triangle");
        }
        // checking if the triangle is equilateral
        // by checking if all the sides are equal
        else if (a == b && b == c) {
            System.out.println(
                    "The numbers: " + a + ", " + b + " and " + c + " " + "represent an equilateral triangle");
        }
        // checking if the triangle is isosceles
        // by checking if two sides are equal
        else if (a == b || b == c || a == c) {
            System.out.println(
                    "The numbers: " + a + ", " + b + " and " + c + " " + "represent an isosceles triangle");
        }
        // checking if the triangle is right angle by
        // checking if the sum of the squares of two
        // sides is equal to the square of the third
        // side
        else if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)
                || Math.pow(b, 2) + Math.pow(c, 2) == Math.pow(a, 2)
                || Math.pow(a, 2) + Math.pow(c, 2) == Math.pow(b, 2)) {
            System.out.println(
                    "The numbers: " + a + ", " + b + " and " + c + " " + "represent a right angle triangle");
        }
        // if the triangle is not equilateral
        // , isosceles or right angle, it is a common triangle
        else {
            System.out.println(
                    "The numbers: " + a + ", " + b + " and " + c + " " + "represent a common triangle");
        }
    } // end of method main
} // end of class Triangle