package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Book;

public interface BookMapper {
	
	String selectAll = "SELECT * FROM books";
	String selectByTitle = "SELECT * from books where UPPER(title) LIKE UPPER(#{s})";
	String insert = "INSERT INTO books(id, title, publisher, year, volume, number, series, address, edition, "
			+ "month, url, note, key) "
			+ "VALUES (#{id}, #{title}, #{publisher}, #{year}, #{volume}, #{number}, #{series}, #{address}, "
			+ "#{edition}, #{month}, #{url}, #{note}, #{key})";
	String update = "UPDATE books SET title=#{title}, publisher=#{publisher}, year=#{year}, "
			+ "volume=#{volume}, number=#{number}, series=#{series}, address=#{address}, edition=#{edition}, "
			+ "month=#{month}, url=#{url}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM books WHERE id = #{id}";
	String deleteAll = "DELETE from books";
	
	@Select(selectAll)
    public ArrayList<Book> getAllBooks();
	
	@Select(selectByTitle)
	public ArrayList<Book> searchByTitle(String s);
	
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertBook(Book a);

	@Update(update)
    public int updateBook(Book b);
	
	@Delete(delete)
	public int deleteBook(Book a);
	
	@Delete(deleteAll)
	public int clear();

}
