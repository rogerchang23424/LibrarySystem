
public class BookData {
	public int booknum;
	public String bookname;
	public String bookkind;
	public String author;
	public int page;
	public boolean lendout;
	public int reserved;
	
	public BookData(){
		
	}
	
	public BookData(int booknum, String bookname, String bookkind, String author, int page){
		this.booknum = booknum;
		this.bookname = bookname;
		this.bookkind = bookkind;
		this.author = author;
		this.page = page;
		this.lendout = false;
		this.reserved = 0;
	}
}
