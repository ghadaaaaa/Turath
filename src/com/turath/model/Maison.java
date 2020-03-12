package com.turath.model;

import java.util.List;

public class Maison  extends EltPatri
{
	
	private String surfaceSol;
	private String surfaceMaison;

	public Maison(int idEltPatri,String descEltPatri,float altitude,
			float longitude, String dateConstruction,String périodeConstruction, 
			String surfaceSol, String surfaceMaison,List<String> appels, 
			List<String> images)
	{
		super(idEltPatri,descEltPatri,altitude,longitude,
				dateConstruction,périodeConstruction,  appels,  images);
		
		this.surfaceSol= surfaceSol;
		this.surfaceMaison = surfaceMaison;
	}

	
	
	public String getSurfaceSol() {
		return surfaceSol;
	}

	public void setSurfaceSol(String surfaceSol) {
		this.surfaceSol = surfaceSol;
	}

	public String getSurfaceMaison() {
		return surfaceMaison;
	}

	public void setSurfaceMaison(String surfaceMaison) {
		this.surfaceMaison = surfaceMaison;
	}
	
	

}
