package test;

import java.util.*;

public class test2{

	public static void main(String args[]) {
		A a = new AImpl();
		B b = new B();
		C c = new C();
		
		a.doSomeProcess("s");
		a.hello();
		b.doIt();
		b.execute();
		c.doIt();
		c.execute();
		
		List<B> bList = Arrays.asList(new B[] {new B(), new B(), new B()});
		System.out.println("from blist  " + bList.toString());
		
		B[] bArr = (B[]) bList.toArray();
		/*B[] bArr = new B[bList.size()];
		bArr = bList.toArray(bArr);*/
		for(B b1:bArr) {
		System.out.println("from bArr: " + b1);
		}
	}
}


abstract interface A {
	void doSomeProcess(String s);
	
	
	
	default void hello(){
		System.out.println("hello");
	}
}

class AImpl implements A {
	public void doSomeProcess(String s) {
		System.out.println("A Imple Code");
	}
}

class B {
	public A doIt() {
		System.out.println("B doIt");
		A a = new AImpl();
		return a;
	}

	public Object execute() {
		System.out.println("B execute");
		return "B execute";
	}
	
	@Override
	public String toString() {
		return "B";
	}
}

class C extends B {
	public AImpl doIt() {
		System.out.println("C doit");
		return new AImpl();
	}

	public String execute() {
		System.out.println("C execute");
		return "C execute";
	}
}
