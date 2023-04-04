
package twodimensionalarray;


public class TwoDimensionalArray {

    
    public static void main(String[] args) {
     // two-dimensional array of student grades
int[][] gradesArray = { { 87, 96, 70 },
{ 68, 87, 90 },
{ 94, 100, 90 },
{ 100, 81, 82 },
{ 83, 65, 85 },
{ 78, 87, 65 },
{ 85, 75, 83 },
{ 91, 94, 100 },
{ 76, 72, 84 },
{ 87, 93, 73 } };

GradeBook myGradeBook = new GradeBook(
"CS101 Introduction to Java Programming",gradesArray);

myGradeBook.displayMessage();
myGradeBook.processGrades();
    }
    
}
