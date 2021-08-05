package com.example.programs;
import java.util.Scanner;
public class assqus5 {
    public static int computeProduct(int num1, int num2) {
        if ((num1 < 10 || num2 > 50) || (num2<10 || num1>50)){
            System.out.println("Not in Range");
            return 0;
        }
        else {
            return num1*num2;
        }
    }
    public static void main(String[] args) {    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number:");
        Integer num1 = scanner.nextInt();
        System.out.println("Enter the second number:");
        Integer num2 = scanner.nextInt();
        int product = computeProduct(num1, num2);
        if (product == 0){
            ;
        }
        else{
            System.out.println("The product of "+ num1+ " and "+ num2+ " is : "+ product);
        }
    }
}


