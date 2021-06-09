
package com.example.tuvybe.models;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class Start {

    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("local")
    @Expose
    public String local;
    @SerializedName("utc")
    @Expose
    public String utc;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Start() {
    }

    /**
     * 
     * @param utc
     * @param timezone
     * @param local
     */
    public Start(String timezone, String local, String utc) {
        super();
        this.timezone = timezone;
        this.local = local;
        this.utc = utc;
    }

}
