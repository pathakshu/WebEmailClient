package com.neu.project.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UserFile {
	
	
	 private CommonsMultipartFile file;
	
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	} 

}
