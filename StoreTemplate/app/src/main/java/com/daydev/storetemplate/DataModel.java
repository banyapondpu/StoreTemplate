package com.daydev.storetemplate;

import java.util.HashMap;
import java.util.Map;

class DataModel {
    String title;
    String content;
    String thumbnail;
    String key;

    public DataModel(){ }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public DataModel(String title, String content, String thumbnail, String key) {
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.key = key;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("title",title);
        result.put("content",content);
        result.put("thumbnail",thumbnail);
        result.put("key",key);
        return result;
    }
}
