package it.mwt.hirelance.business;

import java.io.Serializable;

public class RequestGrid implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int iDisplayStart;
	private int iDisplayLength;
	private String sEcho;
	private String sSearch; //stringa di ricerca
	private String sortCol; //colonna di ordinamento
	private String sortDir; //direzione di ordinamento
	
	public RequestGrid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestGrid(int iDisplayStart, int iDisplayLength, String sEcho,
			String sSearch, String sortCol, String sortDir) {
		super();
		this.iDisplayStart = iDisplayStart;
		this.iDisplayLength = iDisplayLength;
		this.sEcho = sEcho;
		this.sSearch = sSearch;
		this.sortCol = sortCol;
		this.sortDir = sortDir;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}


}
