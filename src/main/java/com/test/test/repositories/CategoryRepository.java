package com.test.test.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.models.Category;
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

import static com.test.test.queries.CategoryQuery.*;

@Repository
public class CategoryRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    public CategoryRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private JdbcTemplate getJdbcTemplate() {
        return namedParameterJdbcTemplate.getJdbcTemplate();
    }

    private Category toModel(Map<String, Object> map) {
        return objectMapper.convertValue(map, Category.class);
    }

    public Category getCategoryId(int id) {
        try {
            Map<String, Object> result = getJdbcTemplate().queryForMap(
                    GET_CATEGORIES,
                    new Object[]{id},
                    new int[]{Types.BIGINT}
            );

            return toModel(result);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Category createCategory(Category category) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName(Category.TABLE)
                .usingGeneratedKeyColumns(Category.ID);

        Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(category));
        category.setId(key.longValue());
        return category;
    }

    public List<Category> getAllCategories() {
        List<Map<String, Object>> result = getJdbcTemplate().queryForList(GET_ALL_CATEGORIES);
        return result.stream().map(this::toModel).collect(Collectors.toList());
    }

    public void deleteCategory(int id) {
        getJdbcTemplate().update(DELETE_CATEGORY_BY_ID, id);
    }

    public void insertInBatch(List<Category> categories) {
        List<BeanPropertySqlParameterSource> entries = categories.stream()
                .map(BeanPropertySqlParameterSource::new)
                .collect(Collectors.toList());

        new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName(Category.TABLE)
                .usingGeneratedKeyColumns(Category.ID)
                .executeBatch(entries.toArray(new BeanPropertySqlParameterSource[0]));
    }
}
