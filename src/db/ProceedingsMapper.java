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
	String selectByTitleAndYear = "SELECT * from proceedings where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO proceedings(id, title, year, volume, number, series, address, month, publisher, "
			+ "organization, note, key) VALUES (#{id}, #{title}, #{year}, #{volume}, #{number}, #{series}, "
			+ "#{address}, #{month}, #{publisher}, #{organization}, #{note}, #{key})";
    String update = "UPDATE phdthesis SET title=#{title}, year=#{year}, volume=#{volume}, number=#{number}, "
    		+ "series=#{series}, address=#{address}, month=#{month}, publisher=#{publisher}, "
    		+ "organization=#{organization}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM proceedings WHERE id = #{id}";
	String deleteAll = "DELETE from proceedings";

		
	@Select(selectAll)
	public ArrayList<Proceedings> getAllProceedings();
		
	@Select(selectByID)
	public Proceedings getProceedings(int id);
	
	@Select(selectByTitle)
	public ArrayList<Proceedings> searchByTitle(String s);
	
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

}
