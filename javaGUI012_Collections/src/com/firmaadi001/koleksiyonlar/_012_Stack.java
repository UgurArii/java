
package com.firmaadi001.koleksiyonlar;

import java.util.EmptyStackException;
import java.util.Stack;


public class _012_Stack {
	public static void main(String args[]){
		
		Stack st = new Stack();
		System.out.println("Stack " + st);
		
		pushGoster(st,100);
		pushGoster(st,54);
		pushGoster(st,454);
		popGoster(st);
		
		
		try{
			popGoster(st);
		}catch(EmptyStackException e){
			
		}
		
	}

	private static void pushGoster(Stack st, int i) {
		st.push(new Integer(i));
		System.out.println("push("+i+")");
		System.out.println("Stack: "+st);
	}

	private static void popGoster(Stack st) {
		System.out.println("pop----->");
		Integer i = (Integer) st.pop();
		System.out.print("Stack: "+st);
	}
}
