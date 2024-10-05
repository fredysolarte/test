package com.test.test.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.test.test.queries.ProductQuery.*;


@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private JdbcTemplate getJdbcTemplate() {
        return namedParameterJdbcTemplate.getJdbcTemplate();
    }

    private Product toModel(Map<String, Object> map) {
        return objectMapper.convertValue(map, Product.class);
    }

    public List<Product> getAllProducts() {
        return getJdbcTemplate().query(GET_ALL_PRODUCTS, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setNombre(rs.getString("nombre"));
            product.setSuplierId(rs.getLong("suplier_id"));
            product.setCategoriaId(rs.getLong("categoria_id"));
            product.setCantidad(rs.getInt("cantidad"));
            product.setPrecio(rs.getDouble("precio"));
            product.setUnidadStock(rs.getInt("unidad_stock"));
            product.setUnidadesOrden(rs.getInt("unidades_orden"));
            return product;
        });
    }

    public List<Product> getAllProducts(int page, int size) {
        String sql = GET_ALL_PRODUCTS + LIMIT_PRODUCTS;
        return getJdbcTemplate().query(sql, new Object[]{size, page * size}, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setNombre(rs.getString("nombre"));
            product.setSuplierId(rs.getLong("suplier_id"));
            product.setCategoriaId(rs.getLong("categoria_id"));
            product.setCantidad(rs.getInt("cantidad"));
            product.setPrecio(rs.getDouble("precio"));
            product.setUnidadStock(rs.getInt("unidad_stock"));
            product.setUnidadesOrden(rs.getInt("unidades_orden"));
            return product;
        });
    }

    public Product getProductById(Long id) {
        try {
            Map<String, Object> result = getJdbcTemplate().queryForMap(
                    GET_PRODUCT_BY_ID,
                    new Object[]{id},
                    new int[]{Types.BIGINT}
            );
            return toModel(result);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void insertProduct(Product product) {
        new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName(Product.TABLE)
                .usingGeneratedKeyColumns(Product.ID)
                .execute(new BeanPropertySqlParameterSource(product));
    }

    public void insertInBatch(List<Product> products) {
        List<BeanPropertySqlParameterSource> entries = products.stream()
                .map(BeanPropertySqlParameterSource::new)
                .collect(Collectors.toList());

        new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName(Product.TABLE)
                .usingGeneratedKeyColumns(Product.ID)
                .executeBatch(entries.toArray(new BeanPropertySqlParameterSource[0]));
    }

    public Product getProductWithCategoryPhoto(Long id) {
        Map<String, Object> result = getJdbcTemplate().queryForMap(GET_PRODUCT_BY_ID_PHOTO, new Object[]{id});
        return toModel(result);
    }
}
