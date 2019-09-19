package it.aranciaict.flussi.model.response;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

@Document
public class FlussoExportResponse {

	private String msgType;
	private List<BasicDBObject> doc;
	private String token;
	private Integer docCnt;
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public List<BasicDBObject> getDoc() {
		return doc;
	}
	public void setDoc(List<BasicDBObject> doc) {
		this.doc = doc;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getDocCnt() {
		return docCnt;
	}
	public void setDocCnt(Integer docCnt) {
		this.docCnt = docCnt;
	} 
	
	
	
}
