package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Techreport;

public interface TechreportMapper {

	String selectAll = "SELECT * FROM techreport";
	String selectByID = "SELECT id, title, institution, year, TType, number, address, month, key "
			+ "FROM techreports WHERE id=#{id}";
	String selectByTitle = "SELECT * from techreport where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from techreport where year=#{y}";
	String selectByTitleAndYear = "SELECT * from techreport where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO techreport(id, title, institution, year, TType, number, address, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{institution}, #{year}, #{PType}, #{number}, #{address}, #{month}, #{note}, #{key})";
    String update = "UPDATE techreport SET title=#{title}, institution=#{institution}, year=#{year}, TType=#{PType}, "
    		+ "number=#{number}, address=#{address}, month=#{month}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM techreport WHERE id = #{id}";
	String deleteAll = "DELETE from techreport";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'TECHREPORT'";
	String createTable = "CREATE TABLE techreport(" +
			"id 		int		 		primary key 	auto_increment," + 
			"title			varchar(255) 	not null," + 
			"institution  	varchar(255)    not null," +
			"year			int				not null," + 
			"type	     	varchar(255)			," + 
			"number			int						," + 
			"address    	varchar(255)			," + 
			"month			varchar(15)				," + 
			"note			varchar(1023)			," + 
			"key			varchar(15))";
		
	@Select(selectAll)
	public ArrayList<Techreport> getAll();
		
	@Select(selectByID)
	public Techreport getTechreport(int id);
	
	@Select(selectByTitle)
	public ArrayList<Techreport> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Techreport> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Techreport> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertTechreport(Techreport a);

	@Update(update)
	public int updateTechreport(Techreport b);
		
	@Delete(delete)
	public int deleteTechreport(Techreport b);
    
	@Delete(deleteAll)
	public int clear();

	@Select(exists)
	public int num();
	
	@Update(createTable)
	public boolean createTable();
}