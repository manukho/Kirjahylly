package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Unpublished;

public interface UnpublishedMapper {

	String selectAll = "SELECT * FROM unpublished";
	String selectByID = "SELECT id, title, year, month, note, key FROM unpublished WHERE id=#{id}";
	String selectByTitle = "SELECT * from unpublished where UPPER(title) LIKE UPPER(#{s})";
	String selectByTitleAndYear = "SELECT * from unpublished where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO unpublished(id, title, year, month, note, key) VALUES (#{id}, #{title}, "
			+ "#{year}, #{month}, #{note}, #{key})";
    String update = "UPDATE phdthesis SET title=#{title}, year=#{year}, month=#{month}, note=#{note}, "
    		+ "key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM unpublished WHERE id = #{id}";
	String deleteAll = "DELETE from unpublished";

		
	@Select(selectAll)
	public ArrayList<Unpublished> getAllUnpublisheds();
		
	@Select(selectByID)
	public Unpublished getUnpublished(int id);
	
	@Select(selectByTitle)
	public ArrayList<Unpublished> searchByTitle(String s);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Unpublished> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
		
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