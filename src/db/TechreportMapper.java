package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Techreport;

public interface TechreportMapper {

	String selectAll = "SELECT * FROM techreport";
	String selectByID = "SELECT id, title, institution, year, TType, number, address, month, key "
			+ "FROM techreports WHERE id=#{id}";
	String selectByTitle = "SELECT * from techreport where UPPER(title) LIKE UPPER(#{s})";
	String insert = "INSERT INTO techreport(id, title, institution, year, TType, number, address, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{institution}, #{year}, #{PType}, #{number}, #{address}, #{month}, #{note}, #{key})";
    String update = "UPDATE techreport SET title=#{title}, institution=#{institution}, year=#{year}, TType=#{PType}, "
    		+ "number=#{number}, address=#{address}, month=#{month}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM techreport WHERE id = #{id}";
	String deleteAll = "DELETE from techreport";

		
	@Select(selectAll)
	public ArrayList<Techreport> getAllTechreports();
		
	@Select(selectByID)
	public Techreport getTechreport(int id);
	
	@Select(selectByTitle)
	public ArrayList<Techreport> searchByTitle(String s);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertTechreport(Techreport a);

	@Update(update)
	public int updateTechreport(Techreport b);
		
	@Delete(delete)
	public int deleteTechreport(Techreport b);
    
	@Delete(deleteAll)
	public int clear();

}