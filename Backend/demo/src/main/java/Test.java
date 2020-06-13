import java.util.Set;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		Set s= new TreeSet();
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(3);
		//s.add(null);
		System.out.println(s);
		

	}

}
