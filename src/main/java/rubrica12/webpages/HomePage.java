package rubrica12.webpages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class HomePage extends WebPage {
	
	public HomePage() {
		add(new BookmarkablePageLink<String>("AuthorPage", AuthorPage.class));
		add(new BookmarkablePageLink<String>("ListAuthorPage", ListAuthorPage.class));
		
		add(new BookmarkablePageLink<String>("BookPage", BookPage.class));
		add(new BookmarkablePageLink<String>("ListBookPage", ListBookPage.class));
		
		add(new BookmarkablePageLink<String>("FilmPage", FilmPage.class));
		add(new BookmarkablePageLink<String>("ListFilmPage", ListFilmPage.class));
	}
}