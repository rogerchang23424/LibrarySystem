import java.util.*;

public class StudentData {
	String id;
	List<BookData> borrow;
	List<BookData> reserve;
	
	public StudentData(String id){
		this.id = id;
		borrow = new ArrayList<BookData>();
		reserve = new ArrayList<BookData>();
	}
}
