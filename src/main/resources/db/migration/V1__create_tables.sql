CREATE TABLE categoria (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    foto BYTEA
);


CREATE TABLE productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    suplier_id BIGINT,
    categoria_id BIGINT,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    unidad_stock INT NOT NULL,
    unidades_orden INT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);