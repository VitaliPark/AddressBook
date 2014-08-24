package controller;

import constants.DefaultValues;

public enum PageDrawer {

	INSTANCE;
	
	public PageConfig getPageConfig(int contactCount, int currentPage){
		PageConfig pageConfig = new PageConfig();
		if(currentPage == 1){
			pageConfig.setHasPrev(false);
		} else {
			pageConfig.setHasPrev(true);
		}
		if(currentPage * DefaultValues.contactsOnPage < contactCount){
			pageConfig.setHasNext(true);
		}
		
		return pageConfig;
		
	}
}
