package com.mistershorr.retrofittest;

import java.util.List;

/**
 * Created by gshorr on 1/10/18.
 */

public class RecipeResponse {
    private String title;
    private double version;
    private String href;
    private List<Recipe> results;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }
}
