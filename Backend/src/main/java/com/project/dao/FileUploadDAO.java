package com.project.dao;

import com.project.model.UploadFile;

public interface FileUploadDAO {
	void save(UploadFile uploadFile);
	UploadFile getFile(String username);
}
