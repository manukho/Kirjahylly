package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Mastersthesis;

public interface MastersthesisMapper {

	String selectAll = "SELECT * FROM mastersthesis";
	String selectByID = "SELECT id, title, organization, year, MTType, address, month, note, key "
			+ "FROM mastersthesis WHERE id=#{id}";
	String insert = "INSERT INTO mastersthesis(id, title, organization, year, MTType, address, month, note, key) "
			+ "VALUES (#{id}, #{title}, #{school}, #{year}, #{PType}, #{address}, #{month}, #{note}, #{key})";
	String update = "UPDATE mastersthesis SET id=#{id}, title=#{title}, organization=#{school}, year=#{year}, "
			+ "MTType=#{PType}, address=#{address}, month=#{month}, note=#{note}, key=#{key}";
	String delete = "DELETE FROM mastersthesis WHERE id = #{id}";
	String deleteAll = "DELETE from mastersthesis";

		
	@Select(selectAll)
	public ArrayList<Mastersthesis> getAllMastersthesiss();
		
	@Select(selectByID)
	public Mastersthesis getMastersthesis(int id);
		
	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertMastersthesis(Mastersthesis a);

	@Update(update)
	public int updateIncollection(Mastersthesis b);
		
	@Delete(delete)
	public int deleteMastersthesis(Mastersthesis b);
    
	@Delete(deleteAll)
	public int clear();

}