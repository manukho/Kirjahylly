package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Manual;

public interface ManualMapper {
	
	String selectAll = "SELECT * FROM manual";
	String selectByTitle = "SELECT * from manual where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from manual where year=#{y}";
	String selectByTitleAndYear = "SELECT * from manual where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String selectByID = "SELECT id, title, organization, address, edition, month, year, note, key FROM manual "
			+ "WHERE id=#{id}";
	String insert = "INSERT INTO manual(id, title, organization, address, edition, month, year, note, key) "
			+ "VALUES (#{id}, #{title}, #{organization}, #{address}, #{edition}, #{month}, #{year}, #{note}, #{key})";
	String update = "UPDATE manual SET title=#{title}, organization=#{organization}, address=#{address}, "
			+ "edition=#{edition}, month=#{month}, year=#{year}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM manual WHERE id = #{id}";
	String deleteAll = "DELETE from manual";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'MANUAL'";
	String createTable = "CREATE TABLE manual(" + 
			"id         	int             not null	," + 
			"title      	varchar(255)    not null	," +
			"organization	varchar(255)    			," + 
			"address    	varchar(255)				," + 
			"edition     	varchar(255)				," + 
			"month      	varchar(15)					," + 
			"year       	int             			," + 
			"note       	varchar(1023)				," + 
			"key        	varchar(15)					," + 
			"primary key(id))";
	
	@Select(selectAll)
	public ArrayList<Manual> getAll();
			
	@Select(selectByID)
	public Manual getManual(int id);
	
	@Select(selectByTitle)
	public ArrayList<Manual> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Manual> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Manual> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
			
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertManual(Manual a);

	@Update(update)
	public int updateManual(Manual b);
			
	@Delete(delete)
	public int deleteManual(Manual b);
	    
	@Delete(deleteAll)
	public int clear();

	@Select(exists)
	public int num();
	
	@Update(createTable)
	public boolean createTable();
}
