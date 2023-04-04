package selectionsorttest;


public class SelectionSortTest {
 
    public static void main(String[] args) {
       SelectionSort sortArray = new SelectionSort(10);
        System.out.println(sortArray + "\n");
        
        sortArray.sort();
        System.out.println("Sorted array:");
        System.out.println(sortArray);
    }
    
}
