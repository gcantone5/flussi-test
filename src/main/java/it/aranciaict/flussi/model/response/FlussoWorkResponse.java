package it.aranciaict.flussi.model.response;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

@Document
public class FlussoWorkResponse {

	private String msgType;
	private BasicDBObject doc;
	private String token;
	private Integer docCnt;

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public BasicDBObject getDoc() {
		return doc;
	}

	public void setDoc(BasicDBObject doc) {
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
