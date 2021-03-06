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
	String selectByYear = "SELECT * from unpublished where year=#{y}";
	String selectByTitleAndYear = "SELECT * from unpublished where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO unpublished(id, title, year, month, note, key) VALUES (#{id}, #{title}, "
			+ "#{year}, #{month}, #{note}, #{key})";
    String update = "UPDATE phdthesis SET title=#{title}, year=#{year}, month=#{month}, note=#{note}, "
    		+ "key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM unpublished WHERE id = #{id}";
	String deleteAll = "DELETE from unpublished";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'UNPUBLISHED'";
	String createTable = "CREATE TABLE unpublished(" +
			"id 		int		 		primary key 	auto_increment," + 
			"title      varchar(255)    not null	," +
			"year       int             not null	," + 
			"month     	varchar(15)					," + 
			"note       varchar(1023)	not null	," + 
			"key       	varchar(15))";
		
	@Select(selectAll)
	public ArrayList<Unpublished> getAll();
		
	@Select(selectByID)
	public Unpublished getUnpublished(int id);
	
	@Select(selectByTitle)
	public ArrayList<Unpublished> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Unpublished> searchByYear(@Param("y") int y);
	
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

	@Select(exists)
	public int num();
	
	@Update(createTable)
	public boolean createTable();
}