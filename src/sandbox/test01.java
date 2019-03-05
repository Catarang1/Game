package sandbox;

import commons.*;

public class test01 {
	public static void main(String[] args) {
		
		RegExMap<String, String> map = new RegExMap<>();
		map.put("something", "value of digit key");
		
		System.out.println(map.get("something"));
	}
}
