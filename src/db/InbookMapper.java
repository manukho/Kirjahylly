package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Inbook;

public interface InbookMapper {

	String selectAll = "SELECT * FROM inbooks";
	String selectByTitle = "SELECT * from inbooks where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from inbooks where year=#{y}";
	String selectByTitleAndYear = "SELECT * from inbooks where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO inbooks(id, title, chapter, firstPage, lastPage, publisher, year, "
			+ "volume, number, series, IBType, address, edition, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{chapter}, #{firstPage}, #{lastPage}, #{publisher}, #{year}, "
			+ "#{volume}, #{number}, #{series}, #{PType}, #{address}, #{edition}, #{month}, #{note}, #{key})";
	String update = "UPDATE inbooks SET title=#{title}, chapter=#{chapter}, "
			+ "firstPage=#{firstPage}, lastPage=#{lastPage}, publisher=#{publisher}, year=#{year}, "
			+ "volume=#{volume}, number=#{number}, series=#{series}, IBType=#{PType}, "
			+ "address=#{address}, edition=#{edition}, month=#{month}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM inbooks WHERE id = #{id}";
	String deleteAll = "DELETE from inbooks";
	
	@Select(selectAll)
    public ArrayList<Inbook> getAll();
	
	@Select(selectByTitle)
	public ArrayList<Inbook> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Inbook> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Inbook> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
	
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertInbook(Inbook a);

	@Update(update)
    public int updateInbook(Inbook b);
	
	@Delete(delete)
	public int deleteInbook(Inbook b);
	
	@Delete(deleteAll)
	public int clear();
	
}
