package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Misc;

public interface MiscMapper {

	String selectAll = "SELECT * FROM misc";
	String selectByTitle = "SELECT * from misc where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from misc where year=#{y}";
	String selectByTitleAndYear = "SELECT * from misc where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String selectByID = "SELECT id, title, howpublished, month, year, note, key FROM misc WHERE id=#{id}";
	String insert = "INSERT INTO misc(id, title, howpublished, month, year, note, key) "
			+ "VALUES (#{id}, #{title}, #{howpublished}, #{month}, #{year}, #{note}, #{key})";
    String update = "UPDATE phdthesis SET title=#{title}, howpublished=#{howpublished}, month=#{month}, "
    		+ "year=#{year}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM misc WHERE id = #{id}";
	String deleteAll = "DELETE from misc";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'MISC'";
	String createTable = "CREATE TABLE misc(" + 
			"id          	int  			not null	," + 
			"title       	varchar(255)    			," + 
			"howpublished	varchar(255)				," + 
			"month       	varchar(15)					," + 
			"year        	int							," + 
			"note        	varchar(1023)				," + 
			"key         	varchar(15)					," + 
			"primary key(id))";
		
	@Select(selectAll)
	public ArrayList<Misc> getAll();
		
	@Select(selectByID)
	public Misc getMisc(int id);
	
	@Select(selectByTitle)
	public ArrayList<Misc> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Misc> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Misc> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertMisc(Misc a);

	@Update(update)
	public int updateMisc(Misc b);
		
	@Delete(delete)
	public int deleteMisc(Misc b);
    
	@Delete(deleteAll)
	public int clear();

	@Select(exists)
	public int num();
	
	@Update(createTable)
	public boolean createTable();
}