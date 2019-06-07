import java.lang.*;
import java.util.*;

public class Student {
	private StudentData login_student;
	
	public Student(){
		
	}
	
	public void run_student_subprog(String id){
		Scanner input = new Scanner(System.in);
		String buffer;
		String bufbook;
		int index;
		boolean isnum;
		boolean needext;
		login_student = LibraryDatabase.loadStudent(id);
		do{
			LibraryDatabase.viewStudents(id);
			System.out.print("ID:" + login_student.id + ">");
			buffer = input.nextLine();
			if(buffer.equals("exit")){
				break;
			}else if(buffer.equals("borrow")){
				System.out.print("ID:" + login_student.id + "-borrow>");
				System.out.print("\nBookname or Booknum?");
				buffer = input.nextLine();
				if(buffer.equals("booknum")){
					isnum = true;
					bufbook = input.nextLine();
					needext = LibraryDatabase.borrowBook(login_student, bufbook, isnum);
				}else if(buffer.equals("bookname")){
					isnum = false;
					bufbook = input.nextLine();
					needext = LibraryDatabase.borrowBook(login_student, bufbook, isnum);
				}else{
					continue;
				}
				
				if(needext){
					System.out.print("(y/n)?");
					buffer = input.nextLine();
					if(buffer.charAt(0) == 'y' || buffer.charAt(0) == 'Y'){
						LibraryDatabase.reserveBook(login_student, bufbook, isnum);
					}
				}
			}else if(buffer.equals("return")){
				System.out.print("ID:" + login_student.id + "-return>");
				System.out.print("\nBookname or Booknum?");
				buffer = input.nextLine();
				if(buffer.equals("booknum")){
					isnum = true;
					buffer = input.nextLine();
					LibraryDatabase.returnBook(login_student, buffer, isnum);
				}else if(buffer.equals("bookname")){
					isnum = false;
					buffer = input.nextLine();
					LibraryDatabase.returnBook(login_student, buffer, isnum);
				}
			}else if(buffer.equals("reserve")){
				System.out.print("ID:" + login_student.id + "-reserve>");
				System.out.print("\nBookname or Booknum?");
				buffer = input.nextLine();
				if(buffer.equals("booknum")){
					isnum = true;
					bufbook = input.nextLine();
					needext = LibraryDatabase.reserveBook(login_student, bufbook, isnum);
				}else if(buffer.equals("bookname")){
					isnum = false;
					bufbook = input.nextLine();
					needext = LibraryDatabase.reserveBook(login_student, bufbook, isnum);
				}else{
					continue;
				}
				
				if(needext){
					System.out.print("[y/n]");
					buffer = input.nextLine();
					if(buffer.charAt(0) == 'y' || buffer.charAt(0) == 'Y'){
						LibraryDatabase.borrowBook(login_student, bufbook, isnum);
					}
				}
			}else if(buffer.equals("reservecancel")){
				System.out.print("ID:" + login_student.id + "-return>");
				System.out.print("\nBookname or Booknum?");
				buffer = input.nextLine();
				if(buffer.equals("booknum")){
					isnum = true;
					buffer = input.nextLine();
					LibraryDatabase.cancelReserveBook(login_student, buffer, isnum);
				}else if(buffer.equals("bookname")){
					isnum = false;
					buffer = input.nextLine();
					LibraryDatabase.cancelReserveBook(login_student, buffer, isnum);
				}
			}else if(buffer.equals("search")){
				while(true){
					System.out.print("ID:" + login_student.id + "-search>");
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
		}while(true);
	}
}
