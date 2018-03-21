package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PublicationAuthorMapper {
	
	String select = "SELECT (name) from pubauth WHERE id = #{id} and type = #{type}";
	String count = "SELECT COUNT(id) FROM pubauth WHERE id=#{id} AND type=#{type} and UPPER(name) LIKE UPPER(#{name})";
	String insert = "INSERT INTO pubauth(id, type, name) VALUES (#{id}, #{type}, #{name})";
	String delete = "DELETE from pubauth WHERE id = #{id} AND type = #{type} AND name = #{name}";
	String deleteAll = "DELETE from pubauth WHERE id = #{id} AND type = #{type}";
	String clear = "DELETE from pubauth";

	@Select(select)
    public ArrayList<String> getAllPublicationAuthors(@Param("id") int id, @Param("type") String type);
	
	@Select(count)
	public int countIdName(@Param("id") int id, @Param("type") String type, @Param("name") String name);
	
    @Insert(insert)
    public boolean insertPublicationAuthor(@Param("id") int id, @Param("type") String type, @Param("name") String name);
    
    @Delete(delete)
    public int updatePublicationAuthor(@Param("id") int id, @Param("type") String type, @Param("name") String name);
    
    @Delete(deleteAll)
    public int deleteAllPublicationAuthors(@Param("id") int id, @Param("type") String type);

    @Delete(clear)
    public int clear();
}