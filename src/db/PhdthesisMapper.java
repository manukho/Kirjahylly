package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Phdthesis;

public interface PhdthesisMapper {

	String selectAll = "SELECT * FROM phdthesis";
	String selectByID = "SELECT id, title, school, year, PHDtype, address, month, note, key "
			+ "FROM phdthesis WHERE id=#{id}";
	String selectByTitle = "SELECT * from phdthesis where UPPER(title) LIKE UPPER(#{s})";
	String insert = "INSERT INTO phdthesis(id, title, school, year, PHDtype, address, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{school}, #{year}, #{PType}, #{address}, #{month}, #{note}, #{key})";
	String update = "UPDATE phdthesis SET title=#{title}, school=#{school}, year=#{year}, "
			+ "PHDtype=#{PType}, address=#{address}, month=#{month}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM phdthesis WHERE id = #{id}";
	String deleteAll = "DELETE from phdthesis";

		
	@Select(selectAll)
	public ArrayList<Phdthesis> getAllPhdthesis();
		
	@Select(selectByID)
	public Phdthesis getPhdthesis(int id);
	
	@Select(selectByTitle)
	public ArrayList<Phdthesis> searchByTitle(String s);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertPhdthesis(Phdthesis a);

	@Update(update)
	public int updatePhdthesis(Phdthesis b);
		
	@Delete(delete)
	public int deletePhdthesis(Phdthesis b);
    
	@Delete(deleteAll)
	public int clear();

}
