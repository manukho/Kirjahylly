package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Article;

public interface ArticleMapper {
	
	String selectAll = "SELECT * FROM articles";
	String insert = "INSERT INTO articles(id, title, journal, year, volume, number, firstPage, lastPage, "
			+ "month, note, key) "
			+ "VALUES (#{id},#{title},#{journal},#{year},#{volume},#{number},#{firstPage},#{lastPage},"
			+ "#{month},#{note},#{key})";
	String update = "UPDATE articles SET title=#{title}, journal=#{journal}, year=#{year}, volume=#{volume}, "
			+ "number=#{number}, firstPage=#{firstPage}, lastPage=#{lastPage}, month=#{month}, note=#{note}, key=#{key}";
	String delete = "DELETE from articles WHERE id=#{id}";
	String deleteAll = "DELETE from articles";
	
	@Select(selectAll)
    public ArrayList<Article> getAllArticles();

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public int insertArticle(Article a);

	@Update(update)
    public int updateArticle(Article a);
	
	@Delete(delete)
	public int deleteArticle(Article a);
	
	@Delete(deleteAll)
	public int clear();

	
	
}