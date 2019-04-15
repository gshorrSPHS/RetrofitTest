package com.mistershorr.retrofittest;

/**
 * Created by gshorr on 1/10/18.
 */

public class Recipe {
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;

    public Recipe() {
    }

    public Recipe(String title, String ingredients, String thumbnail) {
        this.title = title;
        this.ingredients = ingredients;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
