package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Mastersthesis;

public interface MastersthesisMapper {

	String selectAll = "SELECT * FROM mastersthesis";
	String selectByID = "SELECT id, title, school, year, MSType, address, month, note, key "
			+ "FROM mastersthesis WHERE id=#{id}";
	String selectByTitle = "SELECT * from mastersthesis where UPPER(title) LIKE UPPER(#{s})";
	String selectByYear = "SELECT * from mastersthesis where year=#{y}";
	String selectByTitleAndYear = "SELECT * from mastersthesis where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String insert = "INSERT INTO mastersthesis(id, title, school, year, MSType, address, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{school}, #{year}, #{PType}, #{address}, #{month}, #{note}, #{key})";
	String update = "UPDATE mastersthesis SET title=#{title}, school=#{school}, year=#{year}, "
			+ "MSType=#{PType}, address=#{address}, month=#{month}, note=#{note}, key=#{key} WHERE id=#{id}";
	String delete = "DELETE FROM mastersthesis WHERE id = #{id}";
	String deleteAll = "DELETE from mastersthesis";

		
	@Select(selectAll)
	public ArrayList<Mastersthesis> getAll();
		
	@Select(selectByID)
	public Mastersthesis getMastersthesis(int id);
	
	@Select(selectByTitle)
	public ArrayList<Mastersthesis> searchByTitle(String s);

	@Select(selectByYear)
	public ArrayList<Mastersthesis> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Mastersthesis> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertMastersthesis(Mastersthesis a);

	@Update(update)
	public int updateMastersthesis(Mastersthesis b);
		
	@Delete(delete)
	public int deleteMastersthesis(Mastersthesis b);
    
	@Delete(deleteAll)
	public int clear();

}