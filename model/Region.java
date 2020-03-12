package com.turath.model;

public class Region {
	
	private int idRegion;
	private String nomRegion;
	private String descRegion;
	private String situationRegion;
	private float altitudeR;
	private float longitudeR;
	
	
	
	
	
	public Region(int idRegion, String nomRegion, String descRegion, String situationRegion,
			float altitudeR, float longitudeR)
	{
	
		this.idRegion = idRegion;
		this.nomRegion = nomRegion;
		this.descRegion = descRegion;
		this.situationRegion = situationRegion;
		this.altitudeR = altitudeR;
		this.longitudeR= longitudeR;		
		
	}
	
	
	public int getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	public String getNomRegion() {
		return nomRegion;
	}
	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}
	public String getDescRegion() {
		return descRegion;
	}
	public void setDescRegion(String descRegion) {
		this.descRegion = descRegion;
	}
	public String getSituationRegion() {
		return situationRegion;
	}
	public void setSituationRegion(String situationRegion) {
		this.situationRegion = situationRegion;
	}


	public float getAltitudeR() {
		return altitudeR;
	}


	public void setAltitudeR(float altitudeR) {
		this.altitudeR = altitudeR;
	}


	public float getLongitudeR() {
		return longitudeR;
	}


	public void setLongitudeR(float longitudeR) {
		this.longitudeR = longitudeR;
	}
	
	

}
