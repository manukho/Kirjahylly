package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

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
		//configurelog4j();
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
    	Logger l = Logger.getLogger(DBManagement.class);
    	l.setAdditivity(false);
    	BasicConfigurator.configure();
    }
    
    @SuppressWarnings("unused")
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
	
	public ArrayList<Publication> search(String title, String author, Integer year){
		ArrayList<Publication> list = new ArrayList<Publication>();
		String[] a;
		
		if (title != null) { 
			title = "%" + title + "%";
		}
		
		if (title != null && year == null) {
	    	list.addAll(articleMapper.searchByTitle(title));
	    	list.addAll(bookMapper.searchByTitle(title));
	    	list.addAll(bookletMapper.searchByTitle(title));
	    	list.addAll(inbookMapper.searchByTitle(title));
	    	list.addAll(incollectionMapper.searchByTitle(title));
	    	list.addAll(inproceedingsMapper.searchByTitle(title));
	    	list.addAll(manualMapper.searchByTitle(title));
	    	list.addAll(mastersthesisMapper.searchByTitle(title));
	    	list.addAll(miscMapper.searchByTitle(title));
	    	list.addAll(phdthesisMapper.searchByTitle(title));
	    	list.addAll(proceedingsMapper.searchByTitle(title));
	    	list.addAll(techreportMapper.searchByTitle(title));
	    	list.addAll(unpublishedMapper.searchByTitle(title));
		}
		
		if (title != null && year != null) {
	    	list.addAll(articleMapper.searchByTitleAndYear(title, year));
	    	list.addAll(bookMapper.searchByTitleAndYear(title, year));
	    	list.addAll(bookletMapper.searchByTitleAndYear(title, year));
	    	list.addAll(inbookMapper.searchByTitleAndYear(title, year));
	    	list.addAll(incollectionMapper.searchByTitleAndYear(title, year));
	    	list.addAll(inproceedingsMapper.searchByTitleAndYear(title, year));
	    	list.addAll(manualMapper.searchByTitleAndYear(title, year));
	    	list.addAll(mastersthesisMapper.searchByTitleAndYear(title, year));
	    	list.addAll(miscMapper.searchByTitleAndYear(title, year));
	    	list.addAll(phdthesisMapper.searchByTitleAndYear(title, year));
	    	list.addAll(proceedingsMapper.searchByTitleAndYear(title, year));
	    	list.addAll(techreportMapper.searchByTitleAndYear(title, year));
	    	list.addAll(unpublishedMapper.searchByTitleAndYear(title, year));
		}
		
		if (title == null && year != null) {
			list.addAll(articleMapper.searchByYear(year));
	    	list.addAll(bookMapper.searchByYear(year));
	    	list.addAll(bookletMapper.searchByYear(year));
	    	list.addAll(inbookMapper.searchByYear(year));
	    	list.addAll(incollectionMapper.searchByYear(year));
	    	list.addAll(inproceedingsMapper.searchByYear(year));
	    	list.addAll(manualMapper.searchByYear(year));
	    	list.addAll(mastersthesisMapper.searchByYear(year));
	    	list.addAll(miscMapper.searchByYear(year));
	    	list.addAll(phdthesisMapper.searchByYear(year));
	    	list.addAll(proceedingsMapper.searchByYear(year));
	    	list.addAll(techreportMapper.searchByYear(year));
	    	list.addAll(unpublishedMapper.searchByYear(year));
		}
		
		if (title == null && year == null) {
			list.addAll(articleMapper.getAll());
			list.addAll(bookMapper.getAll());
			list.addAll(bookletMapper.getAll());
	    	list.addAll(inbookMapper.getAll());
	    	list.addAll(incollectionMapper.getAll());
	    	list.addAll(inproceedingsMapper.getAll());
	    	list.addAll(manualMapper.getAll());
	    	list.addAll(mastersthesisMapper.getAll());
	    	list.addAll(miscMapper.getAll());
	    	list.addAll(phdthesisMapper.getAll());
	    	list.addAll(proceedingsMapper.getAll());
	    	list.addAll(techreportMapper.getAll());
	    	list.addAll(unpublishedMapper.getAll());
		}
		
		ArrayList<Publication> result;
		
		if (author == null) {
			result = list;
		} else {
			result = new ArrayList<Publication>();
			author.replaceAll(",", "");
			author.replace(";", "");
			author.replace("and", "");
			author.replace("AND", "");
			a = author.split(" ");
			for (int i = 0; i < a.length; i++) {
				a[i] = "%" + a[i].trim() + "%";
			}
			for (int i = 0; i < list.size(); i++) {
				Publication p = list.get(i);
				int count = 0;
				for (String s : a) {
					count += pubAuthMapper.countIdName(p.getId(), p.getType(), s);
				}
				if (count > 0) {
					p.setWeight(count);
					result.add(p);
				}
			}
		}
		
		result.sort(new PublicationComparator());
		
    	for (Publication pub : result) {
    		pub.setAuthors(pubAuthMapper.getAllPublicationAuthors(pub.getId(), pub.getType()));
    		pub.setEditors(pubEdMapper.getAllPublicationEditors(pub.getId(), pub.getType()));
    	}
		
		return result;
	}
	
	public ArrayList<Publication> searchAll(String text){
		ArrayList<Publication> list = new ArrayList<Publication>();
		text.replaceAll(",", "");
		text.replace(";", "");
		text.replace("and", "");
		text.replace("AND", "");
		String[] part = text.split(" ");
		for (int i = 0; i < part.length; i++) {
			part[i] = "%" + part[i].trim() + "%";
		}
		
		for (String s : part) {
	    	list = searchTitleOrAuthor(list, s);
		}
		
		list.sort(new PublicationComparator());
		
    	for (Publication pub : list) {
    		pub.setAuthors(pubAuthMapper.getAllPublicationAuthors(pub.getId(), pub.getType()));
    		pub.setEditors(pubEdMapper.getAllPublicationEditors(pub.getId(), pub.getType()));
    	}
		
		return list;
	}
	
	private ArrayList<Publication> searchTitleOrAuthor(ArrayList<Publication> list, String s){
		ArrayList<Publication> al = new ArrayList<Publication>();

		al.addAll(articleMapper.searchByTitle(s));
		al.addAll(bookMapper.searchByTitle(s));
		al.addAll(bookletMapper.searchByTitle(s));
		al.addAll(inbookMapper.searchByTitle(s));
		al.addAll(incollectionMapper.searchByTitle(s));
		al.addAll(inproceedingsMapper.searchByTitle(s));
    	al.addAll(manualMapper.searchByTitle(s));
    	al.addAll(mastersthesisMapper.searchByTitle(s));
    	al.addAll(miscMapper.searchByTitle(s));
    	al.addAll(phdthesisMapper.searchByTitle(s));
    	al.addAll(proceedingsMapper.searchByTitle(s));
    	al.addAll(techreportMapper.searchByTitle(s));
    	al.addAll(unpublishedMapper.searchByTitle(s));
    	
    	for (Publication p : al) {
    		list = addItem(list, p);
    	}

    	al.clear();
    	
		al.addAll(articleMapper.getAll());
		al.addAll(bookMapper.getAll());
		al.addAll(bookletMapper.getAll());
		al.addAll(inbookMapper.getAll());
		al.addAll(incollectionMapper.getAll());
		al.addAll(inproceedingsMapper.getAll());
		al.addAll(manualMapper.getAll());
		al.addAll(mastersthesisMapper.getAll());
		al.addAll(miscMapper.getAll());
		al.addAll(phdthesisMapper.getAll());
		al.addAll(proceedingsMapper.getAll());
		al.addAll(techreportMapper.getAll());
		al.addAll(unpublishedMapper.getAll());
		
    	for (Publication p : al) {
    		if (pubAuthMapper.countIdName(p.getId(), p.getType(), s) > 0) {
    			list = addItem(list, p);
    		}
    	}
		return list;
	}
	
	private ArrayList<Publication> addItem(ArrayList<Publication> list, Publication p){
		for (int i = 0; i < list.size(); i++) {
			if ( (list.get(i).getId().equals(p.getId())) 
					&& (list.get(i).getType().equals(p.getType())) ) {
				list.get(i).incrementWeight();
				return list;
			}
		}
		p.incrementWeight();
		list.add(p);
		return list;
	}
	
	private class PublicationComparator implements Comparator<Publication>{

		@Override
		public int compare(Publication p1, Publication p2) {
			return p2.getWeight()-p1.getWeight();
		}
	}
}
