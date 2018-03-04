package db;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import pub.Article;
import pub.Book;
import pub.Booklet;

public class DBManagement {
	
	public SqlSessionFactory factory = null;
	private Logger logger;
	SqlSession session;
	ArticleMapper articleMapper;
	BookMapper bookMapper;
	BookletMapper bookletMapper;
	PublicationAuthorMapper pubAuthMapper;
	PublicationEditorMapper pubEdMapper;
	
	
	public DBManagement() {
		configurelog4j();
		init();
		
		test();
		
		cleanUp();
	}
	
    public void init() {
        String resource = "Configuration.xml";
        InputStream is;
        try {
            is = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        factory = new SqlSessionFactoryBuilder().build(is);
        factory.getConfiguration().addMapper(ArticleMapper.class);
        factory.getConfiguration().addMapper(BookMapper.class);
        factory.getConfiguration().addMapper(BookletMapper.class);
        
        factory.getConfiguration().addMapper(PublicationAuthorMapper.class);
        factory.getConfiguration().addMapper(PublicationEditorMapper.class);
        
        
    	session = factory.openSession(true);
    	articleMapper = session.getMapper(ArticleMapper.class);
    	bookMapper = session.getMapper(BookMapper.class);
    	bookletMapper = session.getMapper(BookletMapper.class);
    	
    	pubAuthMapper = session.getMapper(PublicationAuthorMapper.class);
    	pubEdMapper = session.getMapper(PublicationEditorMapper.class);
    	
    }
    
    private void configurelog4j() {
    	logger = Logger.getLogger(DBManagement.class);
    	BasicConfigurator.configure();
    }
    
    private void test() {
    	clearAll();
    	
    	ArrayList<String> al = new ArrayList<String>();
    	al.add("Pyrga, Evangelina");
    	al.add("Schulz, Frank");
    	al.add("Wagner, Dorothea");
    	al.add("Zaroliagis, Christos");
    	Article a = new Article("Efficient models for timetable information in public transportation systems", 
    			al, "Journal of Experimental Algorithmics", 2008, 12);
    	
    	insertArticle(a);
    	
    	ArrayList<String> al2 = new ArrayList<String>();
    	al2.add("Griffioen, David");
    	al2.add("Vaandrager, Frits");
    	Article a2 = new Article("A theory of normed simulations", al2, "ACM Transactions on Computational Logic", 2004, 5);
    	a2.setNumber(4);
    	a2.setPages(577, 610);
    	
    	insertArticle(a2);

    	Book b = new Book();
    	ArrayList<String> al3 = new ArrayList<String>();
    	al3.add("Ehrig, Hartmut");
    	al3.add("Ehrig, Karsten");
    	al3.add("Prange, Ulrike");
    	al3.add("Taentzer, Gabriele");
    	b.setTitle("Fundamentals of Algebraic Graph Transformation");
    	b.setAuthors(al3);
    	b.setPublisher("Springer");
    	b.setYear(2006);
    	b.setSeries("Monographs in Theoretical Computer Science");
    	b.setAddress("Berlin [u.a.]");
    	
    	insertBook(b);
    	
    	Booklet bklt = new Booklet();
    	bklt.setTitle("The Comprehensive LaTeX Symbol List");
    	ArrayList<String> al4 = new ArrayList<String>();
    	al4.add("Pakin, Scott");
    	bklt.setAuthors(al4);
    	bklt.setHowpublished("tug.ctan.org/info/symbols/comprehensive/symbols-a4.pdf");
    	bklt.setYear(2017);
    	
    	insertBooklet(bklt);
    	
    	ArrayList<Article> articleList = getAllArticles();
    	ArrayList<Book> bookList = getAllBooks();
    	ArrayList<Booklet> bookletList = getAllBooklets();
    	
    	System.out.println(articleList.toString());
    	System.out.println(bookList.toString());
    	System.out.println(bookletList.toString());
    	
    }  	
    
    private void insertAuthors(int id, String type, ArrayList<String> al) {
    	for (int i = 0; i < al.size(); i++) {
    		pubAuthMapper.insertPublicationAuthor(id, type, al.get(i));
    	}
    }
    
    private void insertEditors(int id, String type, ArrayList<String> al) {
    	for (int i = 0; i < al.size(); i++) {
    		pubEdMapper.insertPublicationEditor(id, type, al.get(i));
    	}
    }
    
    private void insertArticle(Article article) {
    	articleMapper.insertArticle(article);
    	insertAuthors(article.getId(), "article", article.getAuthors());
    }
    
    private void insertBook(Book book) {
    	bookMapper.insertBook(book);
    	if (!book.getAuthors().isEmpty()) {
    		insertAuthors(book.getId(), "book", book.getAuthors());
    	} else {
    		insertEditors(book.getId(), "book", book.getEditors());
    	}
    }
    
    private void insertBooklet(Booklet booklet) {
    	bookletMapper.insertBooklet(booklet);
    	if (!booklet.getAuthors().isEmpty()) {
    		insertAuthors(booklet.getId(), "booklet", booklet.getAuthors());
    	}
    }
    
    private ArrayList<Article> getAllArticles() {
    	ArrayList<Article> list = articleMapper.getAllArticles();
    	for (int i = 0; i < list.size(); i++) {
    		Article a = list.get(i);
    		ArrayList<String> al = pubAuthMapper.getAllPublicationAuthors(a.getId(), "article");
    		a.setAuthors(al);
    	}
    	return list;
    }
    
    private ArrayList<Book> getAllBooks(){
    	ArrayList<Book> list = bookMapper.getAllBooks();
    	for (int i = 0; i < list.size(); i++) {
    		Book b = list.get(i);
    		ArrayList<String> al = pubAuthMapper.getAllPublicationAuthors(b.getId(), "book");
    		if (!al.isEmpty()) {
    			b.setAuthors(al);
    		} else {
    			al = pubEdMapper.getAllPublicationEditors(b.getId(), "book");
    			b.setEditors(al);
    		}
    	}
    	return list;
    }
    
    private ArrayList<Booklet> getAllBooklets(){
    	ArrayList<Booklet> list = bookletMapper.getAllBooklets();
    	for (int i = 0; i < list.size(); i++) {
    		Booklet b = list.get(i);
    		ArrayList<String> al = pubAuthMapper.getAllPublicationAuthors(b.getId(), "booklet");
    		if (!al.isEmpty()) {
    			b.setAuthors(al);
    		}
    	}
    	return list;
    }
    
    private void clearAll() {
    	articleMapper.clear();
    	bookMapper.clear();
    	pubAuthMapper.clear();
    	pubEdMapper.clear();
    }
    
    private void cleanUp() {
    	session.close();
    }
}
