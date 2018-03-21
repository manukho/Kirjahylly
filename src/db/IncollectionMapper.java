package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Incollection;

public interface IncollectionMapper {
	
	
	String selectAll = "SELECT * FROM incollections";
	String selectByTitle = "SELECT * from incollections where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from incollections where year=#{y}";
	String selectByTitleAndYear = "SELECT * from incollections where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO incollections(id, title, booktitle, publisher, year, volume, number, series, "
			+ "ICType, chapter, firstPage, lastPage, address, edition, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{booktitle}, #{publisher}, #{year}, #{volume}, #{number}, #{series}, "
			+ "#{PType}, #{chapter}, #{firstPage}, #{lastPage}, #{address}, #{edition}, #{month}, #{note}, #{key})";
	String update = "UPDATE incollections SET title=#{title}, booktitle=#{booktitle}, "
			+ "publisher=#{publisher}, year=#{year}, volume=#{volume}, number=#{number}, series=#{series}, "
			+ "ICType=#{PType}, chapter#{chapter}, firstPage=#{firstPage}, lastPage=#{lastPage}, "
			+ "address=#{address}, edition=#{edition}, month=#{month}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM incollections WHERE id = #{id}";
	String deleteAll = "DELETE from incollections";
	
	@Select(selectAll)
    public ArrayList<Incollection> getAll();
	
	@Select(selectByTitle)
	public ArrayList<Incollection> searchByTitle(String s);
	
	@Select(selectByYear)
	public ArrayList<Incollection> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Incollection> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
	
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertIncollection(Incollection a);

	@Update(update)
    public int updateIncollection(Incollection b);
	
	@Delete(delete)
	public int deleteIncollection(Incollection b);
	
	@Delete(deleteAll)
	public int clear();

}
