CREATE TABLE categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    foto LONGBLOB
);


CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    suplier_id BIGINT,
    categoria_id BIGINT,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    unidad_stock INT NOT NULL,
    unidades_orden INT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);