import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		
		
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("aaa");

		Set<String> unique = new HashSet<String>(list);
		for (String key : unique) {
		    System.out.println(key + ": " + Collections.frequency(list, key));
		}

	}

}
