package com.hunaynr.plant.data;

import com.google.gson.annotations.SerializedName;

public class DataPojo {
    @SerializedName("id")
    private String plantId;
    @SerializedName("name")
    private String name;
    @SerializedName("desc")
    private String description;
    @SerializedName("gzNumber")
    private int growZoneNumber;
    @SerializedName("family")
    private String family;
    @SerializedName("genus")
    private String genus;
    @SerializedName("sciname")
    private String sciname;
    @SerializedName("img")
    private String imageUrl = "";

    public String getPlantId() {
        return plantId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    public String getFamily() {
        return family;
    }

    public String getGenus() {
        return genus;
    }

    public String getSciname() {
        return sciname;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
