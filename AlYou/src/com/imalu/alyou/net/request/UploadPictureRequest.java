package com.imalu.alyou.net.request;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.imalu.alyou.net.NetObject;

public class UploadPictureRequest extends NetObject{
	
	private String extension;
	private  ByteArrayOutputStream stream;
	

	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}


	public ByteArrayOutputStream getStream() {
		return stream;
	}


	public void setStream(ByteArrayOutputStream stream) {
		this.stream = stream;
	}


	public Map<String,String> getParams(){
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("userkey", extension);
		map.put("friUserKey", String.valueOf(stream));
		return map;
	}

}
