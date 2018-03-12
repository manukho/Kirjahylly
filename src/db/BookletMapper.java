package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Booklet;

public interface BookletMapper {

	String selectAll = "SELECT * FROM booklets";
	String selectByTitle = "SELECT * from booklets where UPPER(title) LIKE UPPER(#{s})";
	String insert = "INSERT INTO booklets(id, title, howpublished, address, month, year, note, key) "
			+ "VALUES (#{id}, #{title}, #{howpublished}, #{address}, #{month}, #{year}, #{note}, #{key})";
	String update = "UPDATE booklets SET title=#{title}, howpublished=#{howpublished}, "
			+ "address=#{address}, month=#{month}, year=#{year}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM booklets WHERE id = #{id}";
	String deleteAll = "DELETE from booklets";
	
	@Select(selectAll)
    public ArrayList<Booklet> getAllBooklets();
	
	@Select(selectByTitle)
	public ArrayList<Booklet> searchByTitle(String s);
	
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertBooklet(Booklet a);

	@Update(update)
    public int updateBooklet(Booklet b);
	
	@Delete(delete)
	public int deleteBooklet(Booklet b);
	
	@Delete(deleteAll)
	public int clear();

}
