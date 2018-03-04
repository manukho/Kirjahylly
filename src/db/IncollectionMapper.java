package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Incollection;

public interface IncollectionMapper {
	
	
	String selectAll = "SELECT * FROM incollections";
	String insert = "INSERT INTO incollections(id, title, booktitle, publisher, year, volume, number, series, "
			+ "ICtype, chapter, firstPage, lastPage, address, edition, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{booktitle}, #{publisher}, #{year}, #{volume}, #{number}, #{series}, "
			+ "#{ICtype}, #{chapter}, #{firstPage}, #{lastPage}, #{address}, #{edition}, #{month}, #{note}, #{key})";
	String update = "UPDATE incollections SET id=#{id}, title=#{title}, booktitle=#{booktitle}, "
			+ "publisher=#{publisher}, year=#{year}, volume=#{volume}, number=#{number}, series=#{series}, "
			+ "ICtype=#{ICtype}, chapter#{chapter}, firstPage=#{firstPage}, lastPage=#{lastPage}, "
			+ "address=#{address}, edition=#{edition}, month=#{month}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM incollections WHERE id = #{id}";
	String deleteAll = "DELETE from incollections";
	
	@Select(selectAll)
    public ArrayList<Incollection> getAllIncollections();
	
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
