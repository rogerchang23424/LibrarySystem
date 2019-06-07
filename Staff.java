import java.util.*;

public class Staff {
	int exec_num;
	
	public Staff(){
		
	}
	
	public void run_staff_subprog(){
		Scanner input = new Scanner(System.in);
		String buffer;
		BookData book;
		String bufbook;
		boolean isnum;
		int index;
		do{
			System.out.println("This is staff page");
			System.out.print("Staff>");
			buffer = input.nextLine();
			if(buffer.equals("exit")){
				break;
			}
			else if(buffer.equals("bookregister")){
				while(true){
					book = new BookData();
					System.out.print("Book num:");
					buffer = input.nextLine();
					if(buffer.equals("exit")){
						break;
					}
					book.booknum = Integer.parseInt(buffer);
					System.out.print("Book name:");
					book.bookname = input.nextLine();
					System.out.print("Book kind:");
					book.bookkind = input.nextLine();
					System.out.print("Book author:");
					book.author = input.nextLine();
					System.out.print("Book page:");
					buffer = input.nextLine();
					try{
						book.page = Integer.parseInt(buffer);
					}catch(Exception e){
						book.page = 0;
					}
					LibraryDatabase.registerNewBook(book);
				}
			}
			else if(buffer.equals("bookdelete")){
				while(true){
					System.out.print("Staff-bookdelete>");
					buffer = input.nextLine();
					if(buffer.equals("bookname")){
						buffer = input.nextLine();
						LibraryDatabase.delBook(buffer, false);
					}
					else if(buffer.equals("booknum")){
						buffer = input.nextLine();
						LibraryDatabase.delBook(buffer, true);
					}
					else if(buffer.equals("exit")){
						break;
					}
				}
			}
			else if(buffer.equals("bookedit")){
				while(true){
					System.out.print("Staff-bookedit>");
					buffer = input.nextLine();
					if(buffer.equals("exit")){
						break;
					}else if(buffer.equals("bookname")){
						buffer = input.nextLine();
						isnum = false;
					}else if(buffer.equals("booknum")){
						buffer = input.nextLine();
						isnum = true;
					}else{
						continue;
					}
					book = new BookData();
					System.out.print("Book num:");
					bufbook = input.nextLine();
					book.booknum = Integer.parseInt(bufbook);
					System.out.print("Book name:");
					book.bookname = input.nextLine();
					System.out.print("Book kind:");
					book.bookkind = input.nextLine();
					System.out.print("Book author:");
					book.author = input.nextLine();
					System.out.print("Book page:");
					bufbook = input.nextLine();
					book.page = Integer.parseInt(bufbook);
					LibraryDatabase.updateDatabase(buffer, isnum, book);
				}
			}
			else if(buffer.equals("search")){
				while(true){
					System.out.print("Staff-search>");
					buffer = input.nextLine();
					if(buffer.equals("exit")){
						break;
					}
					try{
						index = Integer.parseInt(buffer);
						if(index < 4){
							buffer = input.nextLine();
						}
						LibraryDatabase.viewBooks(index, buffer);
					}catch(Exception e){
						
					}
				}
			}
			else if(buffer.equals("viewstudent")){
				System.out.print("Staff-viewstudent>");
				bufbook = input.nextLine();
				LibraryDatabase.viewStudents(bufbook);
			}
		}while(true);
	}
}
