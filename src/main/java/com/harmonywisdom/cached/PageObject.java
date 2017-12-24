package com.harmonywisdom.cached;

import java.util.ArrayList;
import java.util.List;

import com.harmonywisdom.model.JSONBasedObject;

public class PageObject extends JSONBasedObject{
	
	public PageObject(){
		pageList=new ArrayList<JSONBasedObject>();
	}
	
	List<JSONBasedObject> pageList;
	
	long total;
	int pageSize;
	int currentPage;
	int maxPage;
	
	
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<JSONBasedObject> getPageList() {
		return pageList;
	}
	public void setPageList(List<JSONBasedObject> pageList) {
		this.pageList = pageList;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
