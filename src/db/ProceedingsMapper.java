package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Proceedings;

public interface ProceedingsMapper {

	String selectAll = "SELECT * FROM proceedings";
	String selectByID = "SELECT id, title, year, volume, number, series, address, month, publisher, organization, note, key FROM proceedings WHERE id=#{id}";
	String insert = "INSERT INTO proceedings(id, title, year, volume, number, series, address, month, publisher, organization, note, key) VALUES (#{id}, #{title}, #{year}, #{volume}, #{number}, #{series}, #{address}, #{month}, #{publisher}, #{organization}, #{note}, #{key})";
    String update = "UPDATE phdthesis SET id=#{id}, title=#{title}, year=#{year}, volume=#{volume}, number=#{number}, series=#{series}, address=#{address}, month=#{month}, publisher=#{publisher}, organization=#{organization}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM proceedings WHERE id = #{id}";
	String deleteAll = "DELETE from proceedings";

		
	@Select(selectAll)
	public ArrayList<Proceedings> getAllProceedings();
		
	@Select(selectByID)
	public Proceedings getProceedings(int id);
		
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
