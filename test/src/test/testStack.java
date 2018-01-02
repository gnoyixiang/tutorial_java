package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class testStack {
	public static void main(String[] args) {
		String[] strlist = { "KOMALA ", "SU MON", "KUANG" };
		Integer[] intlist = { 20, 30, 60, 45 };
		// Complete code by calling MichaelStack’s
		// push(...) and pop() methods in sequence over
		// data strlist and intlist.
		// (Refer to question 1f)

		MichaelStack<Object> testStack = new MichaelStack<Object>();
		

		List[] testArrays = new List[] { Arrays.asList(strlist), Arrays.asList(intlist) };

		for (List l : testArrays) {
			System.out.println("adding to testStack... " /* + l.toString() */);

			for (Object arrayItem : l) {
				System.out.println(arrayItem);
			}

			for (Object arrayItem : l) {
				if (!testStack.hasRoom()) {
					System.out.println("no more room in testString...");
					System.out.println("stop adding to testString...");
					break;
				}
				System.out.println("adding..." + arrayItem);
				testStack.push(arrayItem);
				// System.out.println(testStack.toString());
			}
			
			System.out.println();
			
			System.out.println("removing from testStack...");
			try {
				while (true) {
					System.out.println("removing..." + testStack.pop());
				}
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("caught ArrayIndexOutOfBoundsException...");
				// System.out.println(ex);
				System.out.println("Array is empty\nTest Next\n");
			}
		}
	}
}

class MichaelStack<T> {
	private int index = 0;
	public static final int MAX = 3;
	private T[] data = (T[]) new Object[MAX];

	public void push(T obj) {
		data[index++] = obj;
	}

	public boolean hasNext() {
		return index > 0;
	}

	public boolean hasRoom() {
		return index < MAX;
	}

	public T pop() {
		if (hasNext()) {
			return data[--index];
		}
		throw new ArrayIndexOutOfBoundsException(-1);
	}
}
