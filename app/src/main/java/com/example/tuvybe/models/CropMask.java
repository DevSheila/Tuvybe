
package com.example.tuvybe.models;

//import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class CropMask {

    @SerializedName("top_left")
    @Expose
    public TopLeft topLeft;
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
    public CropMask() {
    }

    /**
     * 
     * @param topLeft
     * @param width
     * @param height
     */
    public CropMask(TopLeft topLeft, Integer width, Integer height) {
        super();
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

}
