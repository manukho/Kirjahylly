package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Manual;

public interface ManualMapper {
	
	String selectAll = "SELECT * FROM manual";
	String selectByID = "SELECT id, title, organization, address, edition, month, year, note, key FROM manual "
			+ "WHERE id=#{id}";
	String insert = "INSERT INTO manual(id, title, organization, address, edition, month, year, note, key) "
			+ "VALUES (#{id}, #{title}, #{organization}, #{address}, #{edition}, #{month}, #{year}, #{note}, #{key})";
	String update = "UPDATE manual SET id=#{id}, title=#{title}, organization=#{organization}, address=#{address}, edition=#{edition}, month=#{month}, year=#{year}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM manual WHERE id = #{id}";
	String deleteAll = "DELETE from manual";
	
	@Select(selectAll)
	public ArrayList<Manual> getAllManuals();
			
	@Select(selectByID)
	public Manual getManual(int id);
			
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertManual(Manual a);

	@Update(update)
	public int updateManual(Manual b);
			
	@Delete(delete)
	public int deleteManual(Manual b);
	    
	@Delete(deleteAll)
	public int clear();

}
