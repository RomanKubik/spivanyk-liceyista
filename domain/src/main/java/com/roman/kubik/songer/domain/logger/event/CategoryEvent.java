package com.roman.kubik.songer.domain.logger.event;

public class CategoryEvent {

    private String categoryName;

    public CategoryEvent(final String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
