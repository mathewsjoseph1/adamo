package com.example.programs;
import java.util.Scanner;
public class assqus4 {
    public static int calculateprice(int number_of_iPads) {
        int price = 1000;
        return number_of_iPads * price;
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of iPad:");
        Scanner scanner = new Scanner(System.in);
        Integer number_of_iPads = scanner.nextInt();
        int total = calculateprice(number_of_iPads);
        System.out.println("The total cost of iPads is $"+total);


    }
}


