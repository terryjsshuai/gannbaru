package dto;

import java.io.Serializable;

public class ReCaptcha implements Serializable {
    private static final long serialVersionUID = 6155843351105011984L;
    private String imgKey;
    private String imgBase64Str;
    private String prompt;
    private String value;


    public String getImgKey() {
        return imgKey;
    }

    public void setImgKey(final String imgKey) {
        this.imgKey = imgKey;
    }

    public String getImgBase64Str() {
        return imgBase64Str;
    }

    public void setImgBase64Str(final String imgBase64Str) {
        this.imgBase64Str = imgBase64Str;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(final String prompt) {
        this.prompt = prompt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
