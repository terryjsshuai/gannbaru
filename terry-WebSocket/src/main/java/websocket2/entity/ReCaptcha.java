package websocket2.entity;

public class ReCaptcha {
	private String imgKey;
	private String imgBase64Str;
	private String prompt;
	private String captValue;

	public String getImgKey() {
		return imgKey;
	}

	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}

	public String getImgBase64Str() {
		return imgBase64Str;
	}

	public void setImgBase64Str(String imgBase64Str) {
		this.imgBase64Str = imgBase64Str;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getCaptValue() {
		return captValue;
	}

	public void setCaptValue(String captValue) {
		this.captValue = captValue;
	}

	
	
}
