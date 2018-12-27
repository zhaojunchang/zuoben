package com.zuoben.dto;

import java.io.Serializable;

/**
 * @author gaoxiang
 * createTime:2018/4/27 0027
 */
public class FileResp implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1934093593923890911L;
	private String fileName;
    private String fileUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
