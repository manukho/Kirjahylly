package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Article;

public interface ArticleMapper {

	@Select("SELECT * FROM articles")
    public ArrayList<Article> getAllArticles();

	@Insert("INSERT INTO articles(id, title, journal, year, volume, number, firstPage, lastPage, month, note, key) VALUES (#{id},#{title},#{journal},#{year},#{volume},#{number},#{firstPage},#{lastPage},#{month},#{note},#{key})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")    
	public int insertArticle(Article a);

	@Update("UPDATE articles SET title=#{title}, journal=#{journal}, year=#{year}, volume=#{volume}, number=#{number}, firstPage=#{firstPage}, lastPage=#{lastPage}, month=#{month}, note=#{note}, key=#{key}")
    public int updateArticle(Article a);
	
	@Delete("DELETE from articles WHERE id=#{id}")
	public int deleteArticle(Article a);
	
	@Delete("DELETE from articles")
	public int clear();

	
	
}