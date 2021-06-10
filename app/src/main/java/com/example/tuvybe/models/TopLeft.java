
package com.example.tuvybe.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;
import org.parceler.Parcel;

//@Generated("jsonschema2pojo")
@Parcel

public class TopLeft {

    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Integer y;

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

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}
