package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Misc;

public interface MiscMapper {

	String selectAll = "SELECT * FROM misc";
	String selectByID = "SELECT id, title, howpublished, month, year, note, key FROM misc WHERE id=#{id}";
	String insert = "INSERT INTO misc(id, title, howpublished, month, year, note, key) "
			+ "VALUES (#{id}, #{title}, #{howpublished}, #{month}, #{year}, #{note}, #{key}";
    String update = "UPDATE phdthesis SET id=#{id}, title=#{title}, howpublished=#{howpublished}, month=#{month}, year=#{year}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM misc WHERE id = #{id}";
	String deleteAll = "DELETE from misc";

		
	@Select(selectAll)
	public ArrayList<Misc> getAllMisc();
		
	@Select(selectByID)
	public Misc getMisc(int id);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertMisc(Misc a);

	@Update(update)
	public int updateIncollection(Misc b);
		
	@Delete(delete)
	public int deleteMisc(Misc b);
    
	@Delete(deleteAll)
	public int clear();

}