
package com.example.tuvybe.models;

//import javax.annotation.Generated;

import com.example.tuvybe.models.Original;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class Logo {

    @SerializedName("crop_mask")
    @Expose
    public CropMask cropMask;
    @SerializedName("original")
    @Expose
    public Original original;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("aspect_ratio")
    @Expose
    public String aspectRatio;
    @SerializedName("edge_color")
    @Expose
    public String edgeColor;
    @SerializedName("edge_color_set")
    @Expose
    public Boolean edgeColorSet;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Logo() {
    }

    /**
     * 
     * @param edgeColor
     * @param original
     * @param aspectRatio
     * @param id
     * @param edgeColorSet
     * @param url
     * @param cropMask
     */
    public Logo(CropMask cropMask, Original original, String id, String url, String aspectRatio, String edgeColor, Boolean edgeColorSet) {
        super();
        this.cropMask = cropMask;
        this.original = original;
        this.id = id;
        this.url = url;
        this.aspectRatio = aspectRatio;
        this.edgeColor = edgeColor;
        this.edgeColorSet = edgeColorSet;
    }

}
