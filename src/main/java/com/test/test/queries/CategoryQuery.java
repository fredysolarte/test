package com.test.test.queries;

public class CategoryQuery {

    private CategoryQuery() {};

    public static final String GET_CATEGORIES = "SELECT * FROM categoria WHERE id = ?";

    public static final String GET_ALL_CATEGORIES = "SELECT * FROM categoria";

    public static final String DELETE_CATEGORY_BY_ID = "DELETE FROM categoria WHERE id = ?";

}
