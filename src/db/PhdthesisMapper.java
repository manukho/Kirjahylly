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
	String selectByID = "SELECT id, title, organization, year, MTType, address, month, note, key "
			+ "FROM phdthesis WHERE id=#{id}";
	String insert = "INSERT INTO phdthesis(id, title, organization, year, MTType, address, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{school}, #{year}, #{PType}, #{address}, #{month}, #{note}, #{key})";
	String update = "UPDATE phdthesis SET id=#{id}, title=#{title}, organization=#{school}, year=#{year}, "
			+ "MTType=#{PType}, address=#{address}, month=#{month}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM phdthesis WHERE id = #{id}";
	String deleteAll = "DELETE from phdthesis";

		
	@Select(selectAll)
	public ArrayList<Phdthesis> getAllPhdthesis();
		
	@Select(selectByID)
	public Phdthesis getPhdthesis(int id);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertPhdthesis(Phdthesis a);

	@Update(update)
	public int updateIncollection(Phdthesis b);
		
	@Delete(delete)
	public int deletePhdthesis(Phdthesis b);
    
	@Delete(deleteAll)
	public int clear();

}
