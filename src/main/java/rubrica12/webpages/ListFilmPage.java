package rubrica12.webpages;

import java.util.Collections;
import java.util.List;

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

import rubrica12.model.Film;
import rubrica12.service.FilmService;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class ListFilmPage extends WebPage {
	private static final long serialVersionUID = -1935854748907274886L;
	
	@SpringBean
	FilmService filmService;
	
	private static final Logger logger = LogManager.getLogger(ListFilmPage.class.getName());
	
	private String currentNameSearch = null;
	
	private List listFilm = Collections.emptyList();
	
	public ListFilmPage(PageParameters parameters) {
		currentNameSearch = parameters.get("currentSearchTerm").toString();
		logger.debug("Cargando la pagina con el parametro " + currentNameSearch);
		initComponents();
	}
	
	public ListFilmPage() {
		initComponents();
	}

	private void initComponents() {
		addForm();
		addFeedBackPanel();
		addListFilmView();
	}
	
	private void addForm() {
		Form form = new Form("formListFilm", new CompoundPropertyModel(new Film())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listFilm.clear();
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("currentSearchTerm", ((Film) getModelObject()).getNameFilm());
				pageParameters.add("currentSearchTerm", ((Film) getModelObject()).getDateOfPremier());
				setResponsePage(ListFilmPage.class, pageParameters);
			}
		};
		form.add(new TextField("nameFilm"));
		form.add(new TextField("dateOfPremier"));
		add(form);
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addListFilmView() {
		Film film = new Film();// service.newEntity()
		film.setNameFilm(currentNameSearch);
		//author.setDateOfBirth(currentNameSearch);
		listFilm = filmService.findFilms(film);
		//listAuthor = authorService.findAuthorsByName(author.getDateOfBirth());
		ListView listview = new ListView("film-group", listFilm) {
			@Override
			protected void populateItem(ListItem item) {
				Film film = (Film) item.getModelObject();
				item.add(new Label("filmName", film.getNameFilm()));
				item.add(new Label("dateOfPremier", film.getDateOfPremier()));
			}
		};
		add(listview);
	}


}
