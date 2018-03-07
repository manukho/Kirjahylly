package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import pub.Article;
import pub.Book;
import pub.Booklet;
import pub.Inbook;
import pub.Incollection;
import pub.Inproceedings;
import pub.Manual;
import pub.Mastersthesis;
import pub.Misc;
import pub.Phdthesis;
import pub.Proceedings;
import pub.Publication;
import pub.Techreport;
import pub.Unpublished;

public class DBManagement {
	
    private static final String DRIVER = "org.h2.Driver";
    private static final String JDBC_URL = "jdbc:h2:~/Kirjahylly/database/db";
    private static final String USER = "";
    private static final String PASSWORD = "";
	
    private static DBManagement instance;
    private Connection connection;
	public SqlSessionFactory factory = null;
	private SqlSession session;
	private ArticleMapper articleMapper;
	private BookMapper bookMapper;
	private BookletMapper bookletMapper;
	private InbookMapper inbookMapper;
	private IncollectionMapper incollectionMapper;
	
	private PublicationAuthorMapper pubAuthMapper;
	private PublicationEditorMapper pubEdMapper;
	
	
	public DBManagement() {
		configurelog4j();
		init();
		test();
	}
	
    public void init() {
    	JdbcConnectionPool cp = null;
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver class not found");
			e.printStackTrace();
		}
    	cp = JdbcConnectionPool.create(JDBC_URL, USER, PASSWORD);
    	try {
			connection = cp.getConnection();
		} catch (SQLException e) {
			System.err.println("Could not get connection.");
			e.printStackTrace();
		}
    	try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			System.err.println("could not set autocommit to true.");
			e.printStackTrace();
		}
    	
        String config = "Configuration.xml";
        InputStream is;
        try {
            is = Resources.getResourceAsStream(config);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        factory = new SqlSessionFactoryBuilder().build(is);
        factory.getConfiguration().addMapper(ArticleMapper.class);
        factory.getConfiguration().addMapper(BookMapper.class);
        factory.getConfiguration().addMapper(BookletMapper.class);
        factory.getConfiguration().addMapper(InbookMapper.class);
        factory.getConfiguration().addMapper(IncollectionMapper.class);
        
        factory.getConfiguration().addMapper(PublicationAuthorMapper.class);
        factory.getConfiguration().addMapper(PublicationEditorMapper.class);
        
        
    	session = factory.openSession(true);
    	articleMapper = session.getMapper(ArticleMapper.class);
    	bookMapper = session.getMapper(BookMapper.class);
    	bookletMapper = session.getMapper(BookletMapper.class);
    	inbookMapper = session.getMapper(InbookMapper.class);
    	incollectionMapper = session.getMapper(IncollectionMapper.class);
    	
    	pubAuthMapper = session.getMapper(PublicationAuthorMapper.class);
    	pubEdMapper = session.getMapper(PublicationEditorMapper.class);
    }
    
    private void configurelog4j() {
    	Logger.getLogger(DBManagement.class);
    	BasicConfigurator.configure();
    }
    
    private void test() {
    	clearAll();
    	
    	Article a = new Article();
    	a.setTitle("Efficient models for timetable information in public transportation systems");
    	ArrayList<String> al = new ArrayList<String>();
    	al.add("Pyrga, Evangelina");
    	al.add("Schulz, Frank");
    	al.add("Wagner, Dorothea");
    	al.add("Zaroliagis, Christos");
    	a.setAuthors(al);
    	a.setJournal("Journal of Experimental Algorithmics");
    	a.setYear(2008);
    	a.setVolume(12);
    	
    	insertArticle(a);
    	
    	Article a2 = new Article();
    	a2.setTitle("A theory of normed simulations");
    	ArrayList<String> al2 = new ArrayList<String>();
    	al2.add("Griffioen, David");
    	al2.add("Vaandrager, Frits");
    	a2.setAuthors(al2);
    	a2.setJournal("ACM Transactions on Computational Logic");
    	a2.setYear(2004);
    	a2.setVolume(5);
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
    	
    	b = new Book();
    	al3.clear();
    	al3.add("Pepper, Peter");
    	b.setAuthors(al3);
    	b.setTitle("Funktionale Programmierung in OPAL, ML, HASKELL und GOFER");
    	b.setPublisher("Springer");
    	b.setYear(1999);
    	b.setAddress("Berlin");
    	
    	insertBook(b);
    	
    	Booklet bklt = new Booklet();
    	bklt.setTitle("The Comprehensive LaTeX Symbol List");
    	ArrayList<String> al4 = new ArrayList<String>();
    	al4.add("Pakin, Scott");
    	bklt.setAuthors(al4);
    	bklt.setHowpublished("tug.ctan.org/info/symbols/comprehensive/symbols-a4.pdf");
    	bklt.setYear(2017);
    	
    	insertBooklet(bklt);
    	
    	Inbook ib = new Inbook();
    	ib.setTitle("Multiagent Systems: Algorithmic, Game-Theoretic, and Logical Foundations");
    	ArrayList<String> al5 = new ArrayList<String>();
    	al5.add("Shoham, Yoav");
    	al5.add("Leyton-Brown, Kevin");
    	ib.setAuthors(al5);
    	ib.setYear(2009);
    	ib.setChapter(5);
    	ib.setFirstPage(117);
    	ib.setLastPage(146);
    	ib.setPublisher("Cambridge University Press");
    	
    	insertInbook(ib);
    	
    	
    	Incollection ic = new Incollection();
    	ic.setTitle("Models for Concurrency");
    	ic.setBooktitle("Handbook of Logic in Computer Science");
    	ic.setVolume(4);
    	ArrayList<String> al6 = new ArrayList<String>();
    	al6.add("Winskel, Glynn");
    	al6.add("Nielsen, Mogens");
    	ic.setAuthors(al6);
    	ArrayList<String> al7 = new ArrayList<String>();
    	al7.add("Abramsky, S.");
    	al7.add("Gabbay, Dov M.");
    	al7.add("Maibaum, T.S.E.");
    	ic.setEditors(al7);
    	ic.setYear(1995);
    	ic.setFirstPage(1);
    	ic.setLastPage(148);
    	ic.setPublisher("Oxford University Press");
    	ic.setAddress("Oxford, UK");
    	
    	insertIncollection(ic);
    	
    	ArrayList<Article> articleList = getAllArticles();
    	ArrayList<Book> bookList = getAllBooks();
    	ArrayList<Booklet> bookletList = getAllBooklets();
    	ArrayList<Inbook> inbookList = getAllInbooks();
    	ArrayList<Incollection> incollectionList = getAllIncollections();
    	
    	System.out.println(articleList.toString());
    	System.out.println(bookList.toString());
    	System.out.println(bookletList.toString());
    	System.out.println(inbookList);
    	System.out.println(incollectionList);
    	
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
    
    public void insertArticle(Article article) {
    	articleMapper.insertArticle(article);
    	insertAuthors(article.getId(), "article", article.getAuthors());
    }
    
    public void insertBook(Book book) {
    	bookMapper.insertBook(book);
    	if (!book.getAuthors().isEmpty()) {
    		insertAuthors(book.getId(), "book", book.getAuthors());
    	} else {
    		insertEditors(book.getId(), "book", book.getEditors());
    	}
    }
    
    public void insertBooklet(Booklet booklet) {
    	bookletMapper.insertBooklet(booklet);
    	if (!booklet.getAuthors().isEmpty()) {
    		insertAuthors(booklet.getId(), "booklet", booklet.getAuthors());
    	}
    }
    
    public void insertInbook(Inbook inbook) {
    	inbookMapper.insertInbook(inbook);
    	if (!inbook.getAuthors().isEmpty()) {
    		insertAuthors(inbook.getId(), "inbook", inbook.getAuthors());
    	} else {
    		insertEditors(inbook.getId(), "inbook", inbook.getEditors());
    	}
    }
    
    public void insertIncollection(Incollection ic) {
    	incollectionMapper.insertIncollection(ic);
    	insertAuthors(ic.getId(), "incollection", ic.getAuthors());
    	if (!ic.getEditors().isEmpty()) {
    		insertEditors(ic.getId(), "incollection", ic.getEditors());
    	}
    }
    
    public void updateArticle(Article a) {
    	articleMapper.updateArticle(a);
    	System.out.println("article updated, now the authors.");
    	System.out.println(a.getId());
    	System.out.println(pubAuthMapper.toString());
    	pubAuthMapper.deleteAllPublicationAuthors(a.getId(), "article");
    	insertAuthors(a.getId(), "article", a.getAuthors());
    }
    
    public void updateBook(Book b) {
    	bookMapper.updateBook(b);
    	pubAuthMapper.deleteAllPublicationAuthors(b.getId(), "book");
    	pubEdMapper.deleteAllPublicationEditors(b.getId(), "book");
    	if (b.getAuthors() != null && b.getAuthors().isEmpty()) {
    		insertAuthors(b.getId(), "book", b.getAuthors());
    	} else {
    		insertEditors(b.getId(), "book", b.getEditors());
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
    
    private ArrayList<Inbook> getAllInbooks(){
    	ArrayList<Inbook> list = inbookMapper.getAllInbooks();
    	for (int i = 0; i < list.size(); i++) {
    		Inbook ib = list.get(i);
    		ArrayList<String> al = pubAuthMapper.getAllPublicationAuthors(ib.getId(), "book");
    		if (!al.isEmpty()) {
    			ib.setAuthors(al);
    		} else {
    			al = pubEdMapper.getAllPublicationEditors(ib.getId(), "book");
    			ib.setEditors(al);
    		}
    	}
    	
    	return list;
    }
    
    private ArrayList<Incollection> getAllIncollections(){
    	ArrayList<Incollection> list = incollectionMapper.getAllIncollections();
    	for (int i = 0; i < list.size(); i++) {
    		Incollection ic = list.get(i);
    		ArrayList<String> al = pubAuthMapper.getAllPublicationAuthors(ic.getId(), "incollection");
    		ic.setAuthors(al);
    		al = pubEdMapper.getAllPublicationEditors(ic.getId(), "incollection");
    		if (!al.isEmpty()) {
    			ic.setEditors(al);
    		}
    	}
    	return list;
    }
    
    private Article getArticle(int id) {
    	Article a = articleMapper.getArticle(id);
    	ArrayList<String> al = pubAuthMapper.getAllPublicationAuthors(id, "article");
    	a.setAuthors(al);
    	return a;
    }
    
    private void clearAll() {
    	articleMapper.clear();
    	bookMapper.clear();
    	bookletMapper.clear();
    	inbookMapper.clear();
    	incollectionMapper.clear();
    	
    	pubAuthMapper.clear();
    	pubEdMapper.clear();
    }
    
    public void cleanUp() {
    	session.close();
    }
    
    public static DBManagement getInstance() {
    	if (instance == null) instance = new DBManagement();
    	return instance;
    }
    
    /* for convenience */
    public void insertPublication(Publication p) {
    	Class<? extends Publication> c = p.getClass();
    	if (c == Article.class) insertArticle((Article) p);
    	if (c == Book.class) insertBook((Book) p);
    	if (c == Booklet.class) insertBooklet((Booklet) p);
    	if (c == Inbook.class) insertInbook((Inbook) p);
    	if (c == Incollection.class) insertIncollection((Incollection) p);
//    	if (c == Inproceedings.class) insertInproceedings((insertInproceedings) p);
//    	if (c == Manual.class) insertManual((Manual) p);
//    	if (c == Mastersthesis.class) insertMastersthesis((Mastersthesis) p);
//    	if (c == Misc.class) insertMisc((Misc) p);
//    	if (c == Phdthesis.class) insertPhdthesis((Phdthesis) p);
//    	if (c == Proceedings.class) insertProceedings((Proceedings) p);
//    	if (c == Techreport.class) insertTechreport((Techreport) p);
//    	if (c == Unpublished.class) insertUnpublished((Unpublished) p);
    }
    
    /* for convenience */
    public void updatePublication(Publication p) {
    	Class<? extends Publication> c = p.getClass();
    	if (c == Article.class) updateArticle((Article) p);
    	if (c == Book.class) updateBook((Book) p);
//    	if (c == Booklet.class) updateBooklet((Booklet) p);
//    	if (c == Inbook.class) updateInbook((Inbook) p);
//    	if (c == Incollection.class) updateIncollection((Incollection) p);
//    	if (c == Inproceedings.class) insertInproceedings((insertInproceedings) p);
//    	if (c == Manual.class) insertManual((Manual) p);
//    	if (c == Mastersthesis.class) insertMastersthesis((Mastersthesis) p);
//    	if (c == Misc.class) insertMisc((Misc) p);
//    	if (c == Phdthesis.class) insertPhdthesis((Phdthesis) p);
//    	if (c == Proceedings.class) insertProceedings((Proceedings) p);
//    	if (c == Techreport.class) insertTechreport((Techreport) p);
//    	if (c == Unpublished.class) insertUnpublished((Unpublished) p);
    }
    
    public Publication getById(int i, String ttype) {
    	if (ttype.equals("article")) return getArticle(i);
    	return null;
    }
}
