// Coded by: Harel Tussi
// ID: 314 615 923
// Date: 05/11/2022

import java.util.Scanner;

public class Triangle1 {
        public static void main(String[] args) {
                Scanner scan = new Scanner(System.in);
                // opening message and description of the program
                System.out.println("This program calculates the area "
                                + "and the perimeter of a given triangle. ");

                // get the lengths of the triangle's sides from the user
                System.out.println("Please enter the three lengths"
                                + " of the triangle's sides");
                int a = scan.nextInt();
                int b = scan.nextInt();
                int c = scan.nextInt();

                // calculating the triangle perimeter
                int perimeter = a + b + c;
                // dividing the triangle perimeter by 2 to calculate the semi-perimeter
                int semiPerimeter = perimeter / 2;
                // calculating the triangle area
                double area = Math.sqrt(semiPerimeter *
                                (semiPerimeter - a) *
                                (semiPerimeter - b) *
                                (semiPerimeter - c));

                // print the triangle sides
                System.out.println("The lengths of the triangle sides are: " + a + ", " + b + ", " + c + ".");
                // print the triangle perimeter
                System.out.println("The perimeter of the triangle is: " + perimeter);
                // print the triangle area
                System.out.println("The area of the triangle is: " + area);
                scan.close();
        } // end of method main
} // end of class Triangle
