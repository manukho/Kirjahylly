package bibtex;

import java.util.ArrayList;
import pub.Publication;
import pub.Article;

public class BibTexExporter {
	
	public String toBibTex(Publication p) {
		StringBuilder bibtex = new StringBuilder("@");
		bibtex.append(p.getType());
		bibtex.append("{");
		bibtex.append(p.getKey());
		bibtex.append(",\n");
		
		// add fields
		if (!p.getTitle().isEmpty()) bibtex.append(getFieldText("title", p.getTitle()));
		if (!p.getAuthorString().isEmpty()) bibtex.append(getNameFieldText("author", p.getAuthors()));
		if (!p.getEditorString().isEmpty()) bibtex.append(getNameFieldText("editor", p.getEditors()));
		if (p.getType().equals("journal")) bibtex.append(getFieldText("journal", ((Article) p).getJournal()));
		if (p.getYearString().isEmpty()) bibtex.append(getFieldText("year", p.getYearString()));
		if (!p.getVolumeString().isEmpty()) bibtex.append(getFieldText("volume", p.getVolumeString()));
		if (!p.getNumberString().isEmpty()) bibtex.append(getFieldText("number", p.getNumberString()));
		if (!p.getPageString().isEmpty()) bibtex.append(getFieldText("pages", p.getPageString()));
		if (!p.getMonth().isEmpty()) bibtex.append(getFieldText("month", p.getMonth()));
		if (!p.getPublisher().isEmpty()) bibtex.append(getFieldText("publisher", p.getPublisher()));
		if (!p.getSeries().isEmpty()) bibtex.append(getFieldText("series", p.getSeries()));
		if (!p.getAddress().isEmpty()) bibtex.append(getFieldText("address", p.getAddress()));
		if (!p.getEdition().isEmpty()) bibtex.append(getFieldText("edition", p.getEdition()));
		if (!p.getUrl().isEmpty()) bibtex.append(getFieldText("url", p.getUrl()));
		if (!p.getHowpublished().isEmpty()) bibtex.append(getFieldText("howpublished", p.getHowpublished()));
		if (!p.getChapterString().isEmpty()) bibtex.append(getFieldText("chapter", p.getChapterString()));
		if (!p.getPType().isEmpty()) bibtex.append(getFieldText("type", p.getPType()));
		if (!p.getBooktitle().isEmpty()) bibtex.append(getFieldText("booktitle", p.getBooktitle()));
		if (!p.getOrganization().isEmpty()) bibtex.append(getFieldText("organization", p.getOrganization()));
		if (!p.getSchool().isEmpty()) bibtex.append(getFieldText("school", p.getSchool()));
		if (!p.getInstitution().isEmpty()) bibtex.append(getFieldText("institution", p.getInstitution()));
		
		bibtex.append("}\n");
		return bibtex.toString();
	}
	
	private String getFieldText(String type, String content) {
		if (type.equals("title"))
				return "\ttitle = {" + content + "},\n";
		return "";
	}
	
	private String getNameFieldText(String type, ArrayList<String> names) {
		String last = names.get(names.size()-1);
		StringBuilder s = new StringBuilder("\t" + type + " = ");
		for (String name : names) {
			s.append(name);
			if (!name.equals(last))
				s.append(" AND ");
		}
		s.append("},\n");
		return s.toString();
	}

	 public String toBibTex(ArrayList<Publication> plist) {
		 String bibtex = "";
		 for (Publication p : plist) {
			 bibtex.concat(toBibTex(p));
			 toBibTex(p);
		 }
		 return bibtex;
	 }
}
