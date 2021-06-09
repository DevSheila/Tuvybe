
package com.example.tuvybe.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class Original {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Original() {
    }

    /**
     * 
     * @param width
     * @param url
     * @param height
     */
    public Original(String url, Integer width, Integer height) {
        super();
        this.url = url;
        this.width = width;
        this.height = height;
    }

}
