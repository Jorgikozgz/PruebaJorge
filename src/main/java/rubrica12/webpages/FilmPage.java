package rubrica12.webpages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;

import rubrica12.model.Film;

public class FilmPage extends WebPage {
	public FilmPage() {

		Form form = new Form("formInsertFilm", new CompoundPropertyModel(new Film()));
		form.add(new Label("nameFilmLabel", getString("nameFilm")));
		form.add(new Label("dateOfPremierLabel", getString("dateOfPremier")));
		form.add(new RequiredTextField("nameFilm"));
		DatetimePicker datetimePicker = new DatetimePicker("dateOfPremier", "yyyy-MM-dd");
		form.add(datetimePicker);
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		
		add(form);
	}
	

}
