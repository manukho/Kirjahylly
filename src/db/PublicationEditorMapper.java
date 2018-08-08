package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PublicationEditorMapper {
	
	String select = "SELECT (name) from pubed WHERE id = #{id} AND type = #{type}";
	String insert = "INSERT INTO pubed(id, type, name) VALUES (#{id}, #{type}, #{name})";
	String delete = "DELETE from pubed WHERE id = #{id} AND type = {type} AND name = #{name}";
	String deleteAll = "DELETE from pubed WHERE id = #{id} AND type = #{type}";
	String clear = "DELETE from pubed";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'PUBED'";
	String createTable = "CREATE TABLE pubed(    "+ 
			"id 		int		 		not null," + 
			"type		varchar(15) 	not null," + 
			"name		varchar(255)	not null)";
	
	@Select(select)
    public ArrayList<String> getAllPublicationEditors(@Param("id") int id, @Param("type") String type);
	
    @Insert(insert)
    public boolean insertPublicationEditor(@Param("id") int id, @Param("type") String type, @Param("name") String name);
    
    @Delete(delete)
    public int updatePublicationEditor(@Param("id") int id, @Param("type") String type, @Param("name") String name);
    
    @Delete(deleteAll)
    public int deleteAllPublicationEditors(@Param("id") int id, @Param("type") String type);

    @Delete(clear)
    public int clear();

	@Select(exists)
	public int num();
	
	@Update(createTable)
	public boolean createTable();
}
