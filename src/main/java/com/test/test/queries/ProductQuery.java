package com.test.test.queries;

public class ProductQuery {
    private ProductQuery() {}

    public static final String GET_ALL_PRODUCTS = "SELECT * FROM productos";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM productos WHERE id = ?";
    public static final String LIMIT_PRODUCTS = "LIMIT ? OFFSET ?";
    public static final String GET_PRODUCT_BY_ID_PHOTO = "SELECT p.*, c.foto FROM productos p JOIN categoria c ON p.categoria_id = c.id WHERE p.id = ?";
}
