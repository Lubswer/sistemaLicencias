use railway;
-- =========================
-- 1) USUARIO (login)
-- =========================
CREATE TABLE usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  cedula VARCHAR(10) NOT NULL,
  username VARCHAR(50) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  rol ENUM('ADMIN','ANALISTA') NOT NULL,
  estado ENUM('ACTIVO','INACTIVO') NOT NULL DEFAULT 'ACTIVO',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- 2) SOLICITANTE (ciudadano)
-- =========================
CREATE TABLE solicitante (
  id_solicitante INT AUTO_INCREMENT PRIMARY KEY,
  cedula VARCHAR(10) NOT NULL UNIQUE,
  nombre VARCHAR(100) NOT NULL,
  fecha_nacimiento DATE NOT NULL
);
select * from solicitante;
Update tramite set estado = "LICENCIA_EMITIDA" where id_tramite = 1;
-- =========================
-- 3) TRAMITE (flujo)
-- =========================
CREATE TABLE tramite (
  id_tramite INT AUTO_INCREMENT PRIMARY KEY,

  id_solicitante INT NOT NULL,
  tipo_licencia VARCHAR(20) NOT NULL,
  estado ENUM('PENDIENTE','EN_EXAMENES','APROBADO','REPROBADO','LICENCIA_EMITIDA')
    NOT NULL DEFAULT 'PENDIENTE',

  -- 4.2 Requisitos (checkboxes + observaciones)
  certificado_medico BOOLEAN DEFAULT NULL,
  pago_ok BOOLEAN DEFAULT NULL,
  multas_ok BOOLEAN DEFAULT NULL,
  observaciones TEXT,

  -- 4.3 Exámenes
  nota_teorica DECIMAL(5,2) DEFAULT NULL,
  nota_practica DECIMAL(5,2) DEFAULT NULL,

  -- Auditoría mínima
  created_by INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_tramite_solicitante
    FOREIGN KEY (id_solicitante) REFERENCES solicitante(id_solicitante),

  CONSTRAINT fk_tramite_created_by
    FOREIGN KEY (created_by) REFERENCES usuario(id_usuario)
);

select * from solicitante;
select * from tramite;
select * from licencia;
describe solicitante;

-- =========================
-- 4) LICENCIA (resultado)
-- =========================
CREATE TABLE licencia (
  id_licencia INT AUTO_INCREMENT PRIMARY KEY,

  id_tramite INT NOT NULL UNIQUE,                -- 1 licencia por trámite
  numero_licencia VARCHAR(30) NOT NULL UNIQUE,   -- no duplicados

  fecha_emision DATE NOT NULL,
  fecha_vencimiento DATE NOT NULL,

  created_by INT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_licencia_tramite
    FOREIGN KEY (id_tramite) REFERENCES tramite(id_tramite),

  CONSTRAINT fk_licencia_created_by
    FOREIGN KEY (created_by) REFERENCES usuario(id_usuario)
);

-- =========================
-- 5) Stored Procedure mínimo (reporte diario)
-- Retorna total de licencias emitidas en una fecha
-- =========================
DELIMITER $$

CREATE PROCEDURE sp_total_licencias_emitidas(IN p_fecha DATE)
BEGIN
  SELECT COUNT(*) AS total_licencias_emitidas
  FROM licencia
  WHERE fecha_emision = p_fecha;
END$$

DELIMITER ;