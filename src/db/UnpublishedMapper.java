package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Unpublished;

public interface UnpublishedMapper {

	String selectAll = "SELECT * FROM unpublished";
	String selectByID = "SELECT id, title, year, month, note, key FROM unpublished WHERE id=#{id}";
	String insert = "INSERT INTO unpublished(id, title, year, month, note, key) VALUES (#{id}, #{title}, #{year}, #{month}, #{note}, #{key})";
    String update = "UPDATE phdthesis SET id=#{id}, title=#{title}, year=#{year}, month=#{month}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM unpublished WHERE id = #{id}";
	String deleteAll = "DELETE from unpublished";

		
	@Select(selectAll)
	public ArrayList<Unpublished> getAllUnpublisheds();
		
	@Select(selectByID)
	public Unpublished getUnpublished(int id);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertUnpublished(Unpublished a);

	@Update(update)
	public int updateUnpublished(Unpublished b);
		
	@Delete(delete)
	public int deleteUnpublished(Unpublished b);
    
	@Delete(deleteAll)
	public int clear();

}