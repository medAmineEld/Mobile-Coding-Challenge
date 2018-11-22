package challenge.coding.mobile.mobile_coding_challenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostStarredRepo {
    @SerializedName("items")
    private List<Repository> items;

    public void setItems(List<Repository> items) {
        this.items = items;
    }

    public List<Repository> getItems() {
        return items;
    }
}
