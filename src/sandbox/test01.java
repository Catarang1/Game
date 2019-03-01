package sandbox;

import java.util.*;

public class test01 {
	public static void main(String[] args) {
		
		HashSet<String> set = new HashSet<>();
		set.add("something");
		set.add("else");
		
		String x = "something";
		
		System.out.println(set.contains(x));
		
	}
}
