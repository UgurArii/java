
package linearsearchtest;

import java.util.Scanner;


public class LinearSearchTest {

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int searchInt;
        int position;
        
        LinearArray searchArray = new LinearArray(10);
        System.out.println(searchArray + "\n");
        
        System.err.print("Please enter an integer value(-1 to quit\n)");
        searchInt = input.nextInt();
        
        while(searchInt != -1)
        {
            position = searchArray.linearSearch(searchInt);
            
            if(position == -1)
                System.out.println("The intger" + searchInt +" was not found.\n");
            else
                System.out.println("the integer " +searchInt + " was found in positon" +position);
            
            System.out.println("Please enter an integer value(-1 to quit):");
            searchInt = input.nextInt();
            
        }
        
    }
    
}
