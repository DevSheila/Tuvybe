
package com.example.tuvybe.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class TopLeft {

    @SerializedName("x")
    @Expose
    public Integer x;
    @SerializedName("y")
    @Expose
    public Integer y;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TopLeft() {
    }

    /**
     * 
     * @param x
     * @param y
     */
    public TopLeft(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
    }

}
