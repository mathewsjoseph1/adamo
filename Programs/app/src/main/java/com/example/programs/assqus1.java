package com.example.programs;
import java.util.Scanner;
public class assqus1 {
    public static void main(String[] args) {
        System.out.println("enter the first number");
        Scanner scanner = new Scanner (System.in);
        Integer first = scanner.nextInt();
        System.out.println("enter the second number");
        Integer second = scanner.nextInt();
        System.out.println("enter the third number");
        Integer third = scanner.nextInt();
        if (first<second && first<third){
            System.out.println("Smallest number is :" + first);

        }
        else if (second<third){
            System.out.println("Smallest number is : " + second);
        }
        else{
            System.out.println("Smallest number is : " + third);
        }



    }

}
