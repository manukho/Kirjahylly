package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Inproceedings;

public interface InproceedingsMapper {

	String selectAll = "SELECT * FROM inproceedings";
	String selectByTitle = "SELECT * from inproceedings where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from inproceedings where year=#{y}";
	String selectByTitleAndYear = "SELECT * from inproceedings where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String selectByID = "SELECT id, title, booktitle, year, volume, number, series, firstPage, lastPage, "
			+ "address, month, organization, publisher, note, key FROM inproceedings WHERE id=#{id}";
	String insert = "INSERT INTO inproceedings(id, title, booktitle, year, volume, number, series, firstPage, "
			+ "lastPage, address, month, organization, publisher, note, key) "
			+ "VALUES (#{id}, #{title}, #{booktitle}, #{year}, #{volume}, #{number}, #{series}, #{firstPage}, "
			+ "#{lastPage}, #{address}, #{month}, #{organization}, #{publisher}, #{note}, #{key})";
	String update = "UPDATE incollections SET title=#{title}, booktitle=#{booktitle}, year=#{year}, "
			+ "volume=#{volume}, number=#{number}, series=#{series}, firstPage=#{firstPage}, "
			+ "lastPage=#{lastPage}, address=#{address}, month=#{month}, organization=#{organization}, "
			+ "publisher=#{publisher}, note=#{note}, key#={key} WHERE id=#{id}";
	String delete = "DELETE FROM inproceedings WHERE id = #{id}";
	String deleteAll = "DELETE from inproceedings";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'INPROCEEDINGS'";
	String createTable = "CREATE TABLE inproceedings(" + 
			"id         	int             not null	," + 
			"title      	varchar(255)    not null	," +
			"booktitle  	varchar(255)    not null	," +
			"year       	int             not null	," + 
			"volume     	int							," + 
			"number     	int							," + 
			"series     	varchar(255)				," + 
			"firstPage		int							," +
			"lastPage		int							," +
			"address    	varchar(255)				," + 
			"month      	varchar(15)					," + 
			"organization	varchar(255)    			," + 
			"publisher  	varchar(255)    			," + 
			"note       	varchar(1023)				," + 
			"key        	varchar(15)					," + 
			"primary key(id))";
		
		@Select(selectAll)
	    public ArrayList<Inproceedings> getAll();
		
		@Select(selectByID)
		public Inproceedings getInproceedings(int id);
		
		@Select(selectByTitle)
		public ArrayList<Inproceedings> searchByTitle(String s);

		@Select(selectByYear)
		public ArrayList<Inproceedings> searchByYear(@Param("y") int y);
		
		@Select(selectByTitleAndYear)
		public ArrayList<Inproceedings> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
		
		@Insert(insert)
		@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
		public int insertInproceedings(Inproceedings a);

		@Update(update)
	    public int updateInproceedings(Inproceedings b);
		
		@Delete(delete)
		public int deleteInproceedings(Inproceedings b);
		
		@Delete(deleteAll)
		public int clear();

		@Select(exists)
		public int num();
		
		@Update(createTable)
		public boolean createTable();
}
