package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Proceedings;

public interface ProceedingsMapper {

	String selectAll = "SELECT * FROM proceedings";
	String selectByID = "SELECT id, title, year, volume, number, series, address, month, publisher, organization, "
			+ "note, key FROM proceedings WHERE id=#{id}";
	String selectByTitle = "SELECT * from proceedings where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from proceedings where year=#{y}";
	String selectByTitleAndYear = "SELECT * from proceedings where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO proceedings(id, title, year, volume, number, series, address, month, publisher, "
			+ "organization, note, key) VALUES (#{id}, #{title}, #{year}, #{volume}, #{number}, #{series}, "
			+ "#{address}, #{month}, #{publisher}, #{organization}, #{note}, #{key})";
    String update = "UPDATE phdthesis SET title=#{title}, year=#{year}, volume=#{volume}, number=#{number}, "
    		+ "series=#{series}, address=#{address}, month=#{month}, publisher=#{publisher}, "
    		+ "organization=#{organization}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM proceedings WHERE id = #{id}";
	String deleteAll = "DELETE from proceedings";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'PROCEEDINGS'";
	String createTable = "CREATE TABLE proceedings(" +
			"id 			int		 		primary key 	auto_increment," + 
			"title			varchar(255) 	not null," + 
			"year			int				not null," + 
			"volume			int		        		," + 
			"number			int						," +
			"series			varchar(255)			," + 
			"address    	varchar(255)			," + 
			"month			varchar(15)				," + 
			"publisher  	varchar(255)    		," +
			"organization	varchar(255)    		," + 
			"note			varchar(1023)			," + 
			"key			varchar(15))";
		
	@Select(selectAll)
	public ArrayList<Proceedings> getAll();
		
	@Select(selectByID)
	public Proceedings getProceedings(int id);
	
	@Select(selectByTitle)
	public ArrayList<Proceedings> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Proceedings> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Proceedings> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertProceedings(Proceedings a);

	@Update(update)
	public int updateProceedings(Proceedings b);
		
	@Delete(delete)
	public int deleteProceedings(Proceedings b);
    
	@Delete(deleteAll)
	public int clear();

	@Select(exists)
	public int num();
	
	@Update(createTable)
	public boolean createTable();
}
