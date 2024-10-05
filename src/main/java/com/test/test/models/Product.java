package com.test.test.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id = -1L;
    private String nombre;
    private Long suplierId;
    private Long categoriaId;
    private Integer cantidad;
    private Double precio;
    private Integer unidadStock;
    private Integer unidadesOrden;

    public static final String TABLE = "productos";
    public static final String ID = "id";
}
