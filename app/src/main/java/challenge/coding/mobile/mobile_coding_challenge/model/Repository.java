package challenge.coding.mobile.mobile_coding_challenge.model;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("full_name")
    private String  full_name;

    @SerializedName("description")
    private String  description;

    @SerializedName("stargazers_count")
    private int  stargazers_count;

    @SerializedName("owner")
    private Owner  owner;

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public Owner getOwner() {
        return owner;
    }
}
