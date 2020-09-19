package de.meisl.showcase.views.containerlist;

import java.time.LocalDateTime;

public class Container {

    public Container(String id, String recipe)
    {
        this.id = id;
        this.recipe = recipe;
    }

    private String id;

    private String recipe;

    private LocalDateTime updateTime;

    private String status;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
