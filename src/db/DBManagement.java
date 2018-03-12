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

import pub.*;

/**
 * DBManagement is the class that manages the connection to the database.
 * It also contains methods to select, insert, update and delete items to/from the database
 *
 * @author Manuela Hopp
 */
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
	private InproceedingsMapper inproceedingsMapper;
	private ManualMapper manualMapper;
	private MastersthesisMapper mastersthesisMapper;
	private MiscMapper miscMapper;
	private PhdthesisMapper phdthesisMapper;
	private ProceedingsMapper proceedingsMapper;
	private TechreportMapper techreportMapper;
	private UnpublishedMapper unpublishedMapper;
	
	private PublicationAuthorMapper pubAuthMapper;
	private PublicationEditorMapper pubEdMapper;
	
	
	public DBManagement() {
		configurelog4j();
		init();
		//test();
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
        factory.getConfiguration().addMapper(InproceedingsMapper.class);
        factory.getConfiguration().addMapper(ManualMapper.class);
        factory.getConfiguration().addMapper(MastersthesisMapper.class);
        factory.getConfiguration().addMapper(MiscMapper.class);
        factory.getConfiguration().addMapper(PhdthesisMapper.class);
        factory.getConfiguration().addMapper(ProceedingsMapper.class);
        factory.getConfiguration().addMapper(TechreportMapper.class);
        factory.getConfiguration().addMapper(UnpublishedMapper.class);
        
        factory.getConfiguration().addMapper(PublicationAuthorMapper.class);
        factory.getConfiguration().addMapper(PublicationEditorMapper.class);
        
        
    	session = factory.openSession(true);
    	articleMapper = session.getMapper(ArticleMapper.class);
    	bookMapper = session.getMapper(BookMapper.class);
    	bookletMapper = session.getMapper(BookletMapper.class);
    	inbookMapper = session.getMapper(InbookMapper.class);
    	incollectionMapper = session.getMapper(IncollectionMapper.class);
    	inproceedingsMapper = session.getMapper(InproceedingsMapper.class);
    	manualMapper = session.getMapper(ManualMapper.class);
    	mastersthesisMapper = session.getMapper(MastersthesisMapper.class);
    	miscMapper = session.getMapper(MiscMapper.class);
    	phdthesisMapper = session.getMapper(PhdthesisMapper.class);
    	proceedingsMapper = session.getMapper(ProceedingsMapper.class);
    	techreportMapper = session.getMapper(TechreportMapper.class);
    	unpublishedMapper = session.getMapper(UnpublishedMapper.class);
    	
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
    	
    	insertPublication(a);
    	
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
    	
    	insertPublication(a2);

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
    	
    	insertPublication(b);
    	
    	b = new Book();
    	al3.clear();
    	al3.add("Pepper, Peter");
    	b.setAuthors(al3);
    	b.setTitle("Funktionale Programmierung in OPAL, ML, HASKELL und GOFER");
    	b.setPublisher("Springer");
    	b.setYear(1999);
    	b.setAddress("Berlin");
    	
    	insertPublication(b);
    	
    	Booklet bklt = new Booklet();
    	bklt.setTitle("The Comprehensive LaTeX Symbol List");
    	ArrayList<String> al4 = new ArrayList<String>();
    	al4.add("Pakin, Scott");
    	bklt.setAuthors(al4);
    	bklt.setHowpublished("tug.ctan.org/info/symbols/comprehensive/symbols-a4.pdf");
    	bklt.setYear(2017);
    	
    	insertPublication(bklt);
    	
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
    	
    	insertPublication(ib);
    	
    	
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
    	
    	insertPublication(ic);
    }  	
    
    private void insertAuthors(int id, String type, ArrayList<String> al) {
    	if (al == null || al.isEmpty()) return;
    	for (int i = 0; i < al.size(); i++) {
    		pubAuthMapper.insertPublicationAuthor(id, type, al.get(i));
    	}
    }
    
    private void insertEditors(int id, String type, ArrayList<String> al) {
    	if (al == null || al.isEmpty()) return;
    	for (int i = 0; i < al.size(); i++) {
    		pubEdMapper.insertPublicationEditor(id, type, al.get(i));
    	}
    }
    
    public ArrayList<Publication> searchByTitle(String s){
    	ArrayList<Publication> list = new ArrayList<Publication>();
    	s = "%" + s + "%";
    	list.addAll(articleMapper.searchByTitle(s));
    	list.addAll(bookMapper.searchByTitle(s));
    	list.addAll(bookletMapper.searchByTitle(s));
    	list.addAll(inbookMapper.searchByTitle(s));
    	list.addAll(incollectionMapper.searchByTitle(s));
    	list.addAll(inproceedingsMapper.searchByTitle(s));
    	list.addAll(manualMapper.searchByTitle(s));
    	list.addAll(mastersthesisMapper.searchByTitle(s));
    	list.addAll(miscMapper.searchByTitle(s));
    	list.addAll(phdthesisMapper.searchByTitle(s));
    	list.addAll(proceedingsMapper.searchByTitle(s));
    	list.addAll(techreportMapper.searchByTitle(s));
    	list.addAll(unpublishedMapper.searchByTitle(s));
    	
    	for (Publication pub : list) {
    		pub.setAuthors(pubAuthMapper.getAllPublicationAuthors(pub.getId(), pub.getType()));
    		pub.setEditors(pubEdMapper.getAllPublicationEditors(pub.getId(), pub.getType()));
    	}
    	
    	return list;
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
    
    public void insertPublication(Publication p) {
    	Class<? extends Publication> c = p.getClass();
    	if (c == Article.class) articleMapper.insertArticle((Article) p);
    	if (c == Book.class) bookMapper.insertBook((Book) p);
    	if (c == Booklet.class) bookletMapper.insertBooklet((Booklet) p);
    	if (c == Inbook.class) inbookMapper.insertInbook((Inbook) p);
    	if (c == Incollection.class) incollectionMapper.insertIncollection((Incollection) p);
    	if (c == Inproceedings.class) inproceedingsMapper.insertInproceedings((Inproceedings) p);
    	if (c == Manual.class) manualMapper.insertManual((Manual) p);
    	if (c == Mastersthesis.class) mastersthesisMapper.insertMastersthesis((Mastersthesis) p);
    	if (c == Misc.class) miscMapper.insertMisc((Misc) p);
    	if (c == Phdthesis.class) phdthesisMapper.insertPhdthesis((Phdthesis) p);
    	if (c == Proceedings.class) proceedingsMapper.insertProceedings((Proceedings) p);
    	if (c == Techreport.class) techreportMapper.insertTechreport((Techreport) p);
    	if (c == Unpublished.class) unpublishedMapper.insertUnpublished((Unpublished) p);
    	
    	insertAuthors(p.getId(), p.getType(), p.getAuthors());
    	insertEditors(p.getId(), p.getType(), p.getEditors());
    }
    
    public void updatePublication(Publication p) {
    	Class<? extends Publication> c = p.getClass();
    	if (c == Article.class) articleMapper.updateArticle((Article) p);
    	if (c == Book.class) bookMapper.updateBook((Book) p);
    	if (c == Booklet.class) bookletMapper.updateBooklet((Booklet) p);
    	if (c == Inbook.class) inbookMapper.updateInbook((Inbook) p);
    	if (c == Incollection.class) incollectionMapper.updateIncollection((Incollection) p);
    	if (c == Inproceedings.class) inproceedingsMapper.updateInproceedings((Inproceedings) p);
    	if (c == Manual.class) manualMapper.updateManual((Manual) p);
    	if (c == Mastersthesis.class) mastersthesisMapper.updateMastersthesis((Mastersthesis) p);
    	if (c == Misc.class) miscMapper.updateMisc((Misc) p);
    	if (c == Phdthesis.class) phdthesisMapper.updatePhdthesis((Phdthesis) p);
    	if (c == Proceedings.class) proceedingsMapper.updateProceedings((Proceedings) p);
    	if (c == Techreport.class) techreportMapper.updateTechreport((Techreport) p);
    	if (c == Unpublished.class) unpublishedMapper.updateUnpublished((Unpublished) p);
    	
    	pubAuthMapper.deleteAllPublicationAuthors(p.getId(), p.getType());
    	pubEdMapper.deleteAllPublicationEditors(p.getId(), p.getType());
    	
    	if (p.getAuthors() != null && !p.getAuthors().isEmpty()) {
    		insertAuthors(p.getId(), p.getType(), p.getAuthors());
    	} 
    	if (p.getEditors() != null && !p.getEditors().isEmpty()) {
    		insertEditors(p.getId(), p.getType(), p.getEditors());
    	}
    }
    
    public void deletePublication(Publication p) {
    	Class<? extends Publication> c = p.getClass();
    	if (c == Article.class) articleMapper.deleteArticle((Article) p);
    	if (c == Book.class) bookMapper.deleteBook((Book) p);
    	if (c == Booklet.class) bookletMapper.deleteBooklet((Booklet) p);
    	if (c == Inbook.class) inbookMapper.deleteInbook((Inbook) p);
    	if (c == Incollection.class) incollectionMapper.deleteIncollection((Incollection) p);
    	if (c == Inproceedings.class) inproceedingsMapper.deleteInproceedings((Inproceedings) p);
    	if (c == Manual.class) manualMapper.deleteManual((Manual) p);
    	if (c == Mastersthesis.class) mastersthesisMapper.deleteMastersthesis((Mastersthesis) p);
    	if (c == Misc.class) miscMapper.deleteMisc((Misc) p);
    	if (c == Phdthesis.class) phdthesisMapper.deletePhdthesis((Phdthesis) p);
    	if (c == Proceedings.class) proceedingsMapper.deleteProceedings((Proceedings) p);
    	if (c == Techreport.class) techreportMapper.deleteTechreport((Techreport) p);
    	if (c == Unpublished.class) unpublishedMapper.deleteUnpublished((Unpublished) p);
    	
    	pubAuthMapper.deleteAllPublicationAuthors(p.getId(), p.getType());
    	pubEdMapper.deleteAllPublicationEditors(p.getId(), p.getType());
    }
}
