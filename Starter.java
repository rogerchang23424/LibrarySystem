import java.util.Scanner;

public class Starter {
	public static Student stu_inte = new Student();
	public static Staff stf_inte = new Staff();
	
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);
		String[] message;
		String buffer;
		//Starter.LoadInitBooks();
		do{
			System.out.print(">>");
			buffer = input.nextLine();
			message = buffer.split(" ");
			if(message[0].equals("student")){
				if(message.length == 1){
					System.out.println("Usage: student <student-id>");
				}else{
					stu_inte.run_student_subprog(message[1]);
				}
			}else if(message[0].equals("staff")){
				stf_inte.run_staff_subprog();
			}
		}while(!message[0].equals("exit"));
	}
	
	public static void LoadInitBooks(){
		LibraryDatabase.registerNewBook(new BookData(82101, "Cihai", "Reference", "Shu Xincheng", 20000));
		LibraryDatabase.registerNewBook(new BookData(80001, "WW2 History", "History", "Winston Churchill", 971));
		LibraryDatabase.registerNewBook(new BookData(00003,	"Egg 100", "Cookbook", "Su yuan ma", 104));
		LibraryDatabase.registerNewBook(new BookData(50001,	"Be a honest man", "Political", "Ma Ying jeou", 520));
		LibraryDatabase.registerNewBook(new BookData(85719, "Sword Art Online", "Novel", "Reki Kawahara", 8763));
		LibraryDatabase.registerNewBook(new BookData(85728, "Spice and Wolf", "Novel", "Isuna Hasekura", 510));
		LibraryDatabase.registerNewBook(new BookData(85707, "The Old Man and the Sea", "Novel", "Ernest Hemingway", 127));
		LibraryDatabase.registerNewBook(new BookData(85703, "Romance of the Three Kingdoms", "Novel", "Luo Guanzhong", 480));
		LibraryDatabase.registerNewBook(new BookData(80001, "Records of the Grand Historian", "History", "Sima Qian", 6000));
	}
}
