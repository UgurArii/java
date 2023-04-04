package stacktest;

import java.util.EmptyStackException;
import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {
        Stack<Number> stack = new Stack<Number>();
        
        stack.push(12L);
        System.out.println("Pushed 12L");
        printStack(stack);
        stack.push(34567);
        System.out.println("Pushed 34567");
        printStack(stack);
        stack.push(1.0F);
        System.out.println("Pushed 1.0F");
        printStack(stack);
        stack.push(1234.5678);
        System.out.println("Pushed 1234.5678");
        printStack(stack);
        
        try
        {
            Number removedObject = null;
            
            while(true)
            {
                removedObject = stack.pop();
                System.out.printf("Popped %\n",removedObject);
                printStack(stack);
                
            }
        }
        catch(EmptyStackException emptyStackException)
        {
            emptyStackException.printStackTrace();
        }
    }
    
    private static void printStack(Stack<Number> stack)
    {
        if(stack.isEmpty())
            System.out.println("stack is empty\n");
        else
            System.out.printf("stack contains: %s (top)\n",stack);
    }
}
