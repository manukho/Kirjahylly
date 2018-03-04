package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Inbook;

public interface InbookMapper {

	String selectAll = "SELECT * FROM inbooks";
	String insert = "INSERT INTO inbooks(id, title, chapter, firstPage, lastPage, publisher, year, "
			+ "volume, number, series, IBType, address, edition, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{chapter}, #{firstPage}, #{lastPage}, #{publisher}, #{year}, "
			+ "#{volume}, #{number}, #{series}, #{IBType}, #{address}, #{edition}, #{month}, #{note}, #{key})";
	String update = "UPDATE inbooks SET id=#{id}, title=#{title}, chapter=#{chapter}, "
			+ "firstPage=#{firstPage}, lastPage=#{lastPage}, publisher=#{publisher}, year=#{year}, "
			+ "volume=#{volume}, number=#{number}, series=#{series}, IBType=#{IBType}, "
			+ "address=#{address}, edition=#{edition}, month=#{month}, note=#{note}, key=#{key})";
	String delete = "DELETE FROM inbooks WHERE id = #{id}";
	String deleteAll = "DELETE from inbooks";
	
	@Select(selectAll)
    public ArrayList<Inbook> getAllInbooks();
	
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
