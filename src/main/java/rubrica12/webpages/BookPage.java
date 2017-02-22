package rubrica12.webpages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import rubrica12.model.Book;

public class BookPage extends WebPage {
	
	public BookPage() {
		Form form = new Form("formInsertBook", new CompoundPropertyModel(new Book()));
		form.add(new Label("titleLabel", getString("title")));
		form.add(new Label("isbnLabel", getString("isbn")));
		form.add(new Label("idAuthorLabel", getString("idAuthor")));
		form.add(new RequiredTextField("title"));
		form.add(new RequiredTextField("isbn"));
		form.add(new RequiredTextField("idAuthor"));
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);

		add(form);

	}

}
