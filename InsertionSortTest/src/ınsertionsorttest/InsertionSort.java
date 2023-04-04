package ınsertionsorttest;

import java.util.Arrays;
import java.util.Random;


public class InsertionSort {
    private int[] data;
    private static final Random generator = new Random();
    
    public InsertionSort(int size)
    {
    data = new int[size];
    
    for(int i = 0;i < size; i++)
    data[i] = 10 + generator.nextInt(90);
    }
    
    public void sort()
    {
        int insert;
        
        for(int next=1; next<data.length;next++)
        {
         insert = data[next];
         
         int moveItem = next;
         
         while(moveItem > 0&& data[moveItem-1]>insert)
         {
             data[moveItem] = data[moveItem-1];
             moveItem--;
         }
         data[moveItem] = insert;
         printPass(next, moveItem);
        }
    }

    public void printPass(int pass, int index) {
        System.out.print(String.format("after pass %2d:", pass));
       
       for(int i=0; i<index;i++)
            System.out.print(data[i]+" ");
       
        System.out.print(data[index] + "* ");
        
        for(int i=index+1;i<data.length;i++)
            System.out.print(data[i]+" ");
        
        System.out.print("\n            ");
        
        for(int i=0; i<=pass;i++)
            System.out.print("-- ");
        System.out.println("\n");
    }
    public String toString()
    {
        return Arrays.toString(data);
    }
}
