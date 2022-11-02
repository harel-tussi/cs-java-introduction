package maman11;

import java.util.Scanner;

public class Triangle1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("This program calculates the area "
                + "and the perimeter of a given triangle. ");
        System.out.println("Please enter the three lengths"
                + " of the triangle's sides");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        System.out.println("The lengths of the triangle sides are:" + a + ", " + b + ", " + c + ".");
        /** calculating the triangle perimeter */
        int perimeter = a + b + c;
        System.out.println("The perimeter of the triangle is: " + perimeter);
        /** dividing the triangle perimeter by 2 */
        int s = perimeter / 2;
        /** calculating the triangle area */
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        System.out.println("The area of the triangle is: " + area);

        scan.close();

    } // end of method main
} // end of class Triangle