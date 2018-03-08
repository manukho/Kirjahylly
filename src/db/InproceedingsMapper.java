package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Inproceedings;

public interface InproceedingsMapper {

	String selectAll = "SELECT * FROM inproceedings";
	String selectByID = "SELECT id, title, booktitle, year, volume, number, series, firstPage, lastPage, "
			+ "address, month, organization, publisher, note, key FROM inproceedings WHERE id=#{id}";
	String insert = "INSERT INTO inproceedings(id, title, booktitle, year, volume, number, series, firstPage, "
			+ "lastPage, address, month, organization, publisher, note, key) "
			+ "VALUES (#{id}, #{title}, #{booktitle}, #{year}, #{volume}, #{number}, #{series}, #{firstPage}, "
			+ "#{lastPage}, #{address}, #{month}, #{organization}, #{publisher}, #{note}, #{key})";
	String update = "UPDATE incollections SET id=#{id}, title=#{title}, booktitle=#{booktitle}, year=#{year}, "
			+ "volume=#{volume}, number=#{number}, series=#{series}, firstPage=#{firstPage}, "
			+ "lastPage=#{lastPage}, address=#{address}, month=#{month}, organization=#{organization}, "
			+ "publisher=#{publisher}, note=#{note}, key#={key}";
	String delete = "DELETE FROM inproceedings WHERE id = #{id}";
	String deleteAll = "DELETE from inproceedings";
		
		@Select(selectAll)
	    public ArrayList<Inproceedings> getAllInproceedings();
		
		@Select(selectByID)
		public Inproceedings getInproceedings(int id);
		
		@Insert(insert)
		@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
		public int insertInproceedings(Inproceedings a);

		@Update(update)
	    public int updateIncollection(Inproceedings b);
		
		@Delete(delete)
		public int deleteInproceedings(Inproceedings b);
		
		@Delete(deleteAll)
		public int clear();

	

}
