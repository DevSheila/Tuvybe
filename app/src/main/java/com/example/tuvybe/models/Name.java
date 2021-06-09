
package com.example.tuvybe.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class Name {

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
    public Name() {
    }

    /**
     * 
     * @param html
     * @param text
     */
    public Name(String text, String html) {
        super();
        this.text = text;
        this.html = html;
    }

}
