package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Book;

public interface BookMapper {
	
	String selectAll = "SELECT * FROM books";
	String selectByTitle = "SELECT * from books where UPPER(title) LIKE UPPER(#{s})";
	String selectByTitleAndYear = "SELECT * from books where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String selectByYear = "SELECT * from books where year=#{y}";
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
    public ArrayList<Book> getAll();
	
	@Select(selectByTitle)
	public ArrayList<Book> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Book> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Book> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
	
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
