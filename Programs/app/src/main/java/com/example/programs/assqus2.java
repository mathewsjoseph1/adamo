package com.example.programs;

import java.util.Scanner;

public class assqus2 {
    public static void main(String[] args) {
        System.out.println("enter the lower limit");
        Scanner scanner = new Scanner (System.in);
        Integer lower = scanner.nextInt();
        System.out.println("enter the upper limit");
        Integer upper = scanner.nextInt();
        int low = lower + 1 ;
        for(; low<upper;++low)
        {

            System.out.print(low+"\t");


        }

    }
}