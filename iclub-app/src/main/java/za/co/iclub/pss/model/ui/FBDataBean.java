package za.co.iclub.pss.model.ui;

import java.io.Serializable;

public class FBDataBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736909648452556296L;
	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
