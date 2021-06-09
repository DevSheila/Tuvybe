
package com.example.tuvybe.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class Description {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("html")
    @Expose
    public String html;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Description() {
    }

    /**
     * 
     * @param html
     * @param text
     */
    public Description(String text, String html) {
        super();
        this.text = text;
        this.html = html;
    }

}
