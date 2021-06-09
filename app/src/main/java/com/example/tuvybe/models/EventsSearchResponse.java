
package com.example.tuvybe.models;

//import javax.annotation.Generated;

import com.example.tuvybe.models.Logo;
import com.example.tuvybe.models.Name;
import com.example.tuvybe.models.Start;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Generated;

@Generated("jsonschema2pojo")
public class EventsSearchResponse {

    @SerializedName("name")
    @Expose
    public Name name;
    @SerializedName("description")
    @Expose
    public Description description;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("start")
    @Expose
    public Start start;
    @SerializedName("end")
    @Expose
    public End end;
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("changed")
    @Expose
    public String changed;
    @SerializedName("published")
    @Expose
    public String published;
    @SerializedName("capacity")
    @Expose
    public Object capacity;
    @SerializedName("capacity_is_custom")
    @Expose
    public Object capacityIsCustom;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("listed")
    @Expose
    public Boolean listed;
    @SerializedName("shareable")
    @Expose
    public Boolean shareable;
    @SerializedName("online_event")
    @Expose
    public Boolean onlineEvent;
    @SerializedName("tx_time_limit")
    @Expose
    public Integer txTimeLimit;
    @SerializedName("hide_start_date")
    @Expose
    public Boolean hideStartDate;
    @SerializedName("hide_end_date")
    @Expose
    public Boolean hideEndDate;
    @SerializedName("locale")
    @Expose
    public String locale;
    @SerializedName("is_locked")
    @Expose
    public Boolean isLocked;
    @SerializedName("privacy_setting")
    @Expose
    public String privacySetting;
    @SerializedName("is_series")
    @Expose
    public Boolean isSeries;
    @SerializedName("is_series_parent")
    @Expose
    public Boolean isSeriesParent;
    @SerializedName("inventory_type")
    @Expose
    public String inventoryType;
    @SerializedName("is_reserved_seating")
    @Expose
    public Boolean isReservedSeating;
    @SerializedName("show_pick_a_seat")
    @Expose
    public Boolean showPickASeat;
    @SerializedName("show_seatmap_thumbnail")
    @Expose
    public Boolean showSeatmapThumbnail;
    @SerializedName("show_colors_in_seatmap_thumbnail")
    @Expose
    public Boolean showColorsInSeatmapThumbnail;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("is_free")
    @Expose
    public Boolean isFree;
    @SerializedName("version")
    @Expose
    public Object version;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("logo_id")
    @Expose
    public String logoId;
    @SerializedName("organizer_id")
    @Expose
    public String organizerId;
    @SerializedName("venue_id")
    @Expose
    public Object venueId;
    @SerializedName("category_id")
    @Expose
    public String categoryId;
    @SerializedName("subcategory_id")
    @Expose
    public String subcategoryId;
    @SerializedName("format_id")
    @Expose
    public String formatId;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("resource_uri")
    @Expose
    public String resourceUri;
    @SerializedName("is_externally_ticketed")
    @Expose
    public Boolean isExternallyTicketed;
    @SerializedName("series_id")
    @Expose
    public String seriesId;
    @SerializedName("logo")
    @Expose
    public Logo logo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EventsSearchResponse() {
    }

    /**
     * 
     * @param shareable
     * @param showSeatmapThumbnail
     * @param showPickASeat
     * @param description
     * @param isReservedSeating
     * @param source
     * @param locale
     * @param seriesId
     * @param capacity
     * @param organizationId
     * @param privacySetting
     * @param organizerId
     * @param txTimeLimit
     * @param isFree
     * @param isLocked
     * @param venueId
     * @param subcategoryId
     * @param logo
     * @param end
     * @param currency
     * @param id
     * @param summary
     * @param capacityIsCustom
     * @param inventoryType
     * @param created
     * @param isExternallyTicketed
     * @param start
     * @param hideStartDate
     * @param showColorsInSeatmapThumbnail
     * @param published
     * @param resourceUri
     * @param version
     * @param url
     * @param isSeries
     * @param formatId
     * @param listed
     * @param isSeriesParent
     * @param name
     * @param hideEndDate
     * @param onlineEvent
     * @param logoId
     * @param categoryId
     * @param changed
     * @param status
     */
    public EventsSearchResponse(Name name, Description description, String url, Start start, End end, String organizationId, String created, String changed, String published, Object capacity, Object capacityIsCustom, String status, String currency, Boolean listed, Boolean shareable, Boolean onlineEvent, Integer txTimeLimit, Boolean hideStartDate, Boolean hideEndDate, String locale, Boolean isLocked, String privacySetting, Boolean isSeries, Boolean isSeriesParent, String inventoryType, Boolean isReservedSeating, Boolean showPickASeat, Boolean showSeatmapThumbnail, Boolean showColorsInSeatmapThumbnail, String source, Boolean isFree, Object version, String summary, String logoId, String organizerId, Object venueId, String categoryId, String subcategoryId, String formatId, String id, String resourceUri, Boolean isExternallyTicketed, String seriesId, Logo logo) {
        super();
        this.name = name;
        this.description = description;
        this.url = url;
        this.start = start;
        this.end = end;
        this.organizationId = organizationId;
        this.created = created;
        this.changed = changed;
        this.published = published;
        this.capacity = capacity;
        this.capacityIsCustom = capacityIsCustom;
        this.status = status;
        this.currency = currency;
        this.listed = listed;
        this.shareable = shareable;
        this.onlineEvent = onlineEvent;
        this.txTimeLimit = txTimeLimit;
        this.hideStartDate = hideStartDate;
        this.hideEndDate = hideEndDate;
        this.locale = locale;
        this.isLocked = isLocked;
        this.privacySetting = privacySetting;
        this.isSeries = isSeries;
        this.isSeriesParent = isSeriesParent;
        this.inventoryType = inventoryType;
        this.isReservedSeating = isReservedSeating;
        this.showPickASeat = showPickASeat;
        this.showSeatmapThumbnail = showSeatmapThumbnail;
        this.showColorsInSeatmapThumbnail = showColorsInSeatmapThumbnail;
        this.source = source;
        this.isFree = isFree;
        this.version = version;
        this.summary = summary;
        this.logoId = logoId;
        this.organizerId = organizerId;
        this.venueId = venueId;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.formatId = formatId;
        this.id = id;
        this.resourceUri = resourceUri;
        this.isExternallyTicketed = isExternallyTicketed;
        this.seriesId = seriesId;
        this.logo = logo;
    }

}
