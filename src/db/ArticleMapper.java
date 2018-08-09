package db;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pub.Article;

public interface ArticleMapper {
	
	String selectAll = "SELECT * FROM articles";
	String selectByID = "SELECT id, title, journal, year, volume, number, firstPage, lastPage, month, note key FROM ARTICLES WHERE id=#{id}";
	String selectByTitle = "SELECT * from articles where UPPER(title) LIKE UPPER(#{s})";
	String selectByTitleAndYear = "SELECT * from articles where UPPER(title) LIKE UPPER(#{s}) AND year=#{y}";
	String selectByYear = "SELECT * from articles where year=#{y}";
	String insert = "INSERT INTO articles(id, title, journal, year, volume, number, firstPage, lastPage, "
			+ "month, note, key) "
			+ "VALUES (#{id},#{title},#{journal},#{year},#{volume},#{number},#{firstPage},#{lastPage},"
			+ "#{month},#{note},#{key})";
	String update = "UPDATE articles SET title=#{title}, journal=#{journal}, year=#{year}, volume=#{volume}, "
			+ "number=#{number}, firstPage=#{firstPage}, lastPage=#{lastPage}, month=#{month}, note=#{note}, "
			+ "key=#{key} WHERE id=#{id}";
	String delete = "DELETE from articles WHERE id=#{id}";
	String deleteAll = "DELETE from articles";
	String exists = "SELECT count(*) FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'ARTICLES'";
	String createTable = "CREATE TABLE articles(    "+ 
			"id 		int		 		primary key 	auto_increment," + 
			"title		varchar(255) 	not null," + 
			"journal	varchar(255)	not null," + 
			"year		int				not null," + 
			"volume		int		        not null," + 
			"number		int						," + 
			"firstPage 	int						," + 
			"lastPage	int						," + 
			"month		varchar(15)				," + 
			"note		varchar(1023)			," + 
			"key		varchar(15))";
	
	@Select(selectAll)
    public ArrayList<Article> getAll();
	
	@Select(selectByID)
	public Article getArticle(int id);
	
	@Select(selectByTitle)
	public ArrayList<Article> searchByTitle(String s);
	
	@Select(selectByYear)
	public ArrayList<Article> searchByYear(@Param("y") int y);
	
	@Select(selectByTitleAndYear)
	public ArrayList<Article> searchByTitleAndYear(@Param("s") String s, @Param("y") int y);

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public int insertArticle(Article a);

	@Update(update)
    public int updateArticle(Article a);
	
	@Delete(delete)
	public int deleteArticle(Article a);
	
	@Delete(deleteAll)
	public int clear();	
	
	@Select(exists)
	public int num();
	
	@Update(createTable)
	public boolean createTable();
}