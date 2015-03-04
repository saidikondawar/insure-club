package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@ManagedBean(name = "iclubMenuController")
@SessionScoped
public class IclubMenuController implements Serializable {

	private static final long serialVersionUID = -5155234741934732730L;
	private static final Logger LOGGER = Logger.getLogger(IclubMenuController.class);
	private String language;

	public void languageValueChangeListener(ValueChangeEvent valueChangeEvent) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: languageValueChangeListener");
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("")) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("languageforlocale", valueChangeEvent.getNewValue().toString());
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(valueChangeEvent.getNewValue().toString()));
		}
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
