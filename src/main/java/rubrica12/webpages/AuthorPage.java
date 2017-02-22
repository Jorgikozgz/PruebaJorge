package rubrica12.webpages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;


import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePicker;
import rubrica12.model.Author;

public class AuthorPage extends WebPage {
	
	public AuthorPage() {

		Form form = new Form("formInsertAuthor", new CompoundPropertyModel(new Author()));
		form.add(new Label("nameAuthorLabel", getString("nameAuthor")));
		form.add(new Label("dateOfBirthLabel", getString("dateOfBirth")));
		form.add(new RequiredTextField("nameAuthor"));
		DatetimePicker datetimePicker = new DatetimePicker("dateOfBirth", "yyyy-MM-dd");
		form.add(datetimePicker);
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		
		add(form);
	}
}