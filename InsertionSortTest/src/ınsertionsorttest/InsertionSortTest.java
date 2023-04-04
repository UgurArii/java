package ınsertionsorttest;



public class InsertionSortTest {

    public static void main(String[] args) {
       InsertionSort sortArray = new InsertionSort(10);
        System.out.println("Unsorted Array");
        System.out.println(sortArray+"\n");
        sortArray.sort();
        
        System.out.println("Sorted Array");
        System.out.println(sortArray);
    }
    
}
