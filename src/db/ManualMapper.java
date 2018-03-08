package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Manual;

public interface ManualMapper {
	
	String selectAll = "SELECT * FROM manuals";
	String selectByID = "SELECT id, title, organization, address, edition, month, year, note, key FROM manuals WHERE id=#{id}";
	String insert = "INSERT INTO manuals(id, title, organization, address, edition, month, year, note, key) VALUES (#{id}, #{title}, #{organization}, #{address}, #{edition}, #{month}, #{year}, #{note}, #{key})";
	String update = "UPDATE manuals SET id=#{id}, title=#{title}, organization=#{organization}, address=#{address}, edition=#{edition}, month=#{month}, year=#{year}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM manuals WHERE id = #{id}";
	String deleteAll = "DELETE from manuals";
	
	@Select(selectAll)
	public ArrayList<Manual> getAllManuals();
			
	@Select(selectByID)
	public Manual getManual(int id);
			
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertManual(Manual a);

	@Update(update)
	public int updateIncollection(Manual b);
			
	@Delete(delete)
	public int deleteManual(Manual b);
	    
	@Delete(deleteAll)
	public int clear();

}
