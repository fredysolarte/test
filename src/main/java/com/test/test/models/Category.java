package com.test.test.models;


import java.io.Serializable;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id = -1L;
    private String nombre;
    private String descripcion;
    private byte[] foto;

    public static final String TABLE = "categoria";
    public static final String ID = "id";
}
