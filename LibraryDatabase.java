import java.util.*;
import java.util.regex.*;

public class LibraryDatabase {
	private static List<BookData> books = Arrays.asList(
			new BookData(82101, "Cihai", "Reference", "Shu Xincheng", 20000),
			new BookData(80001, "WW2 History", "History", "Winston Churchill", 971),
			new BookData(00003,	"Egg 100", "Cookbook", "Su yuan ma", 104),
			new BookData(50001,	"Be a honest man", "Political", "Ma Ying jeou", 520),
			new BookData(85719, "Sword Art Online", "Novel", "Reki Kawahara", 8763),
			new BookData(85728, "Spice and Wolf", "Novel", "Isuna Hasekura", 510),
			new BookData(85707, "The Old Man and the Sea", "Novel", "Ernest Hemingway", 127),
			new BookData(85703, "Romance of the Three Kingdoms", "Novel", "Luo Guanzhong", 480),
			new BookData(80002, "Records of the Grand Historian", "History", "Sima Qian", 6000));
	private static List<StudentData> students= new ArrayList<StudentData>();
	
	public LibraryDatabase(){
		
	}
	
	public static void registerNewBook(BookData bd){
		for(BookData book: books){
			if(book.booknum == bd.booknum || book.bookname.equals(bd.bookname)){
				System.out.println("Failed added book");
				return;
			}
		}
		books.add(bd);
		System.out.println("Successful added book.");
	}
	
	public static void delBook(String book, boolean isnum){
		int booknum;
		if(isnum){
			booknum = Integer.parseInt(book);
			for(BookData booki: books){
				if(booki.booknum == booknum){
					books.remove(booki);
					System.out.println("Remove book from library successful.");
					return;
				}
			}
		}else{
			for(BookData booki: books){
				if(booki.bookname.equals(book)){
					books.remove(booki);
					System.out.println("Remove book from library successful.");
					return;
				}
			}
		}
		System.out.println("No such book in library.");
	}
	
	public static void updateDatabase(String book, boolean isnum, BookData data){
		int booknum;
		if(isnum){
			booknum = Integer.parseInt(book);
			for(BookData booki: books){
				if(booki.booknum == booknum){
					editbook(booki, data, true);
					System.out.println("Edit book from library successful.");
					return;
				}
			}
		}else{
			for(BookData booki: books){
				if(booki.bookname.equals(book)){
					editbook(booki, data, false);
					System.out.println("Edit book from library successful.");
					return;
				}
			}
		}
		System.out.println("No such book in library.");
	}
	
	public static void viewBooks(int selection, String keyword){
		switch(selection){
		case 1:
			findBooksByName(keyword);
			break;
		case 2:
			try{
				findBooksByNum(Integer.parseInt(keyword));
			}catch(Exception e){
				
			}
			break;
		case 3:
			findKindBooks(keyword);
			break;
		case 4:
			showAllBooks();
			break;
		}
	}

	public static void viewStudents(String id){
		for(StudentData student: students){
			if(student.id.equals(id)){
				System.out.println("ID: " + id);
				System.out.println("Borrowed books:");
				for(BookData bd: student.borrow){
					System.out.println("Book Number:" + bd.booknum + ", Book Name:" + bd.bookname);
				}
				System.out.println("Reserved books:");
				for(BookData bd: student.reserve){
					System.out.println("Book Number:" + bd.booknum + ", Book Name:" + bd.bookname);
				}
				return;
			}
		}
		System.out.println("No such students");
	}
	
	public static StudentData loadStudent(String id){
		for(StudentData stu: students){
			if(stu.id.equals(id)){
				return stu;
			}
		}
		addStudent(id);
		return students.get(students.size()-1);
	}
	
	public static void addStudent(String id){
		students.add(new StudentData(id));
	}
	
	public static boolean borrowBook(StudentData student, String book, boolean isnum){
		int booknum;
		if(isnum){
			booknum = Integer.parseInt(book);
			for(BookData booki:books){
				if(booki.booknum == booknum){
					if(!booki.lendout){
						booki.lendout = true;
						student.borrow.add(booki);
						if(student.reserve.contains(booki)){
							student.reserve.remove(booki);
						}
						System.out.println("借出成功");
						return false;
					}else{
						System.out.println("此書以借出，是否預約");
						return true;
					}
				}
			}
		}else{
			for(BookData booki:books){
				if(booki.bookname.equals(book)){
					if(!booki.lendout){
						booki.lendout = true;
						student.borrow.add(booki);
						if(student.reserve.contains(booki)){
							student.reserve.remove(booki);
						}
						System.out.println("借出成功");
						return false;
					}else{
						System.out.println("此書以借出，是否預約");
						return true;
					}
				}
			}
		}
		System.out.println("No such book in library");
		return false;
	}
	
	public static void returnBook(StudentData student, String book, boolean isnum){
		int booknum;
		if(isnum){
			booknum = Integer.parseInt(book);
			for(BookData booki:student.borrow){
				if(booki.booknum == booknum){
					booki.lendout = false;
					student.borrow.remove(booki);
					System.out.println("Return book success.");
					return;
				}
			}
		}else{
			for(BookData booki:student.borrow){
				if(booki.bookname.equals(book)){
					booki.lendout = false;
					student.borrow.remove(booki);
					System.out.println("Return book success.");
					return;
				}
			}
		}
		System.out.println("Return book failed");
	}
	
	public static boolean reserveBook(StudentData student, String book, boolean isnum){
		int booknum;
		if(isnum){
			booknum = Integer.parseInt(book);
			for(BookData booki:books){
				if(booki.booknum == booknum){
					if(!student.reserve.contains(booki)){
						if(booki.lendout){
							if(booki.reserved < 10){
								booki.reserved += 1;
								student.reserve.add(booki);
								System.out.println("預約成功");
							}else{
								System.out.println("此書無法預約，預約名額滿了");
							}
							return false;
						}else{
							System.out.println("此書未借出，無法預約，是否要藉此本書");
							return true;
						}
					}else{
						System.out.println("ID" + student.id + "此人已預約此書");
						return false;
					}
				}
			}
		}else{
			for(BookData booki:books){
				if(booki.bookname.equals(book)){
					if(!student.reserve.contains(booki)){
						if(booki.lendout){
							if(booki.reserved < 10){
								booki.reserved += 1;
								student.reserve.add(booki);
								System.out.println("預約成功");
							}else{
								System.out.println("此書無法預約，預約名額滿了");
							}
							return false;
						}else{
							System.out.println("此書未借出，無法預約，是否要藉此本書");
							return true;
						}
					}else{
						System.out.println("ID" + student.id + "此人已預約此書");
						return false;
					}
				}
			}
		}
		System.out.println("No such book in library");
		return false;
	}
	
	public static void cancelReserveBook(StudentData student, String book, boolean isnum){
		int booknum;
		if(isnum){
			booknum = Integer.parseInt(book);
			for(BookData booki:student.reserve){
				if(booki.booknum == booknum){
					booki.reserved -= 1;
					student.reserve.remove(booki);
					System.out.println("Cancel reserved book success.");
					return;
				}
			}
		}else{
			for(BookData booki:student.reserve){
				if(booki.bookname.equals(book)){
					booki.reserved -= 1;
					student.reserve.remove(booki);
					System.out.println("Cancel reserved book success.");
					return;
				}
			}
		}
		System.out.println("Return book failed");
	}
	
	private static void editbook(BookData oldd, BookData newd, boolean isnum){
		if(isnum){
			oldd.bookname = newd.bookname;
		}else{
			oldd.booknum = newd.booknum;
		}
		oldd.bookkind = newd.bookkind;
		oldd.author = newd.author;
		oldd.page = newd.page;
	}
	
	private static void findBooksByName(String name){
		boolean find = false;
		Pattern p = Pattern.compile(name);
		Matcher m;
		books.sort(new Comparator<Object>(){
			@Override
			public int compare(Object o1, Object o2) {
				return ((BookData)o1).bookname
						.compareTo(((BookData)o2).bookname);
			}
		});
		System.out.printf("%-10s   %-75s  %-35s  %-35s  %-5s  %-10s  %-3s\n", "書號", "書名", "種類", "作者", "頁數", "是否已借出", "預約人數");
		for(BookData book: books){
			m = p.matcher(book.bookname);
			if(m.find()){
				find = true;
				System.out.printf("%05d   %-30s  %-15s  %-15s  %5d " + ((book.lendout)?" true":"false") + "     %3d\n",
						book.booknum, book.bookname, book.bookkind, book.author, book.page, book.reserved);
			}
		}
		if(!find){
			System.out.println("No match book.");
		}
	}
	
	private static void findBooksByNum(int booknum) {
		boolean find = false;
		books.sort(new Comparator<Object>(){
			@Override
			public int compare(Object o1, Object o2) {
				if(((BookData)o1).booknum > ((BookData)o2).booknum){
					return 1;
				}else if(((BookData)o1).booknum == ((BookData)o2).booknum){
					return 0;
				}else{
					return -1;
				}
			}
		});
		System.out.printf("%-10s   %-75s  %-35s  %-35s  %-5s  %-10s  %-3s\n", "書號", "書名", "種類", "作者", "頁數", "是否已借出", "預約人數");
		for(BookData book: books){
			if(book.booknum == booknum){
				find = true;
				System.out.printf("%05d   %-30s  %-15s  %-15s  %5d " + ((book.lendout)?" true":"false") + "     %3d\n",
						book.booknum, book.bookname, book.bookkind, book.author, book.page, book.reserved);
			}
		}
		if(!find){
			System.out.println("No match book.");
		}
	}
	
	private static void findKindBooks(String kind){
		boolean find = false;
		books.sort(new Comparator<Object>(){
			@Override
			public int compare(Object o1, Object o2) {
				return ((BookData)o1).bookname
						.compareTo(((BookData)o2).bookname);
			}
		});
		System.out.printf("%-10s   %-75s  %-35s  %-35s  %-5s  %-10s  %-3s\n", "書號", "書名", "種類", "作者", "頁數", "是否已借出", "預約人數");
		for(BookData book: books){
			if(book.bookkind.equalsIgnoreCase(kind)){
				find = true;
				System.out.printf("%05d   %-30s  %-15s  %-15s  %5d " + ((book.lendout)?" true":"false") + "     %3d\n",
						book.booknum, book.bookname, book.bookkind, book.author, book.page, book.reserved);
			}
		}
		if(!find){
			System.out.println("No match book.");
		}
		
	}
	
	private static void showAllBooks(){
		books.sort(new Comparator<Object>(){
			@Override
			public int compare(Object o1, Object o2) {
				return ((BookData)o1).bookname
						.compareTo(((BookData)o2).bookname);
			}
		});
		System.out.printf("%-10s   %-75s  %-35s  %-35s  %-5s  %-10s  %-3s\n", "書號", "書名", "種類", "作者", "頁數", "是否已借出", "預約人數");
		for(BookData book: books){
			System.out.printf("%05d   %-30s  %-15s  %-15s  %5d " + ((book.lendout)?" true":"false") + "     %3d\n",
					book.booknum, book.bookname, book.bookkind, book.author, book.page, book.reserved);
		}
	}
}
