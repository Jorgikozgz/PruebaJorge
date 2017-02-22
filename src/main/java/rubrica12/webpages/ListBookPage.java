package rubrica12.webpages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import rubrica12.model.Book;
import rubrica12.service.BookService;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class ListBookPage extends WebPage {
	private static final long serialVersionUID = -1935854748907274886L;

	@SpringBean
	BookService bookService;

	private static final Logger logger = LogManager.getLogger(ListBookPage.class.getName());

	private String currentNameSearchBook = null;

	private List listBook = Collections.emptyList();

	public ListBookPage(PageParameters parameters) {
		currentNameSearchBook = parameters.get("currentSearchTerm").toString();
		logger.debug("Cargando la pagina con el parametro " + currentNameSearchBook);
		initComponents();
	}

	public ListBookPage() {
		initComponents();
	}

	private void initComponents() {
		addFormBook();
		addFeedBackPanel();
		addListBookView();
	}

	private void addFormBook() {
		Form form = new Form("formListBook", new CompoundPropertyModel(new Book())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listBook.clear();
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("currentSearchTerm", ((Book) getModelObject()).getTitle());
				pageParameters.add("currentSearchTerm", ((Book) getModelObject()).getIsbn());
				pageParameters.add("currentSearchTerm", ((Book) getModelObject()).getIdAuthor());
				setResponsePage(ListAuthorPage.class, pageParameters);
			}
		};
		form.add(new TextField("title"));
		form.add(new TextField("isbn"));
		form.add(new TextField("idAuthor"));
		add(form);
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addListBookView() {
		Book book = new Book();// service.newEntity()
		book.setTitle(currentNameSearchBook);
		listBook = bookService.findBooksByidAuthor(book.getIdAuthor());
		if (StringUtils.isNumeric(currentNameSearchBook)) {
			book.setIsbn(Integer.parseInt(currentNameSearchBook));
		}
		listBook = bookService.findBooks(book);
		ListView listview = new ListView("book-group", listBook) {
			@Override
			protected void populateItem(ListItem item) {
				Book book = (Book) item.getModelObject();
				item.add(new Label("title", book.getTitle()));
				item.add(new Label("isbn", book.getIsbn()));
				item.add(new Label("idAuthor", book.getIdAuthor()));
			}
		};
		add(listview);
	}

}
