package com.turath.model;

import java.util.List;

public class Site extends EltPatri{
	private String surfaceSite;
	
	public Site(int idEltPatri,String descEltPatri,float altitude,
			float longitude, String dateConstruction,String périodeConstruction, String surfaceSite, 
			List<String> appels, List<String> images)
	{
		super(idEltPatri,descEltPatri,altitude,longitude,
				dateConstruction,périodeConstruction,appels,images);
		this.surfaceSite= surfaceSite;
	
	}

	public String getSurfaceSite() {
		return surfaceSite;
	}

	public void setSurfaceSite(String surfaceSite) {
		this.surfaceSite = surfaceSite;
	}
	
	
}
