package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.Proveedor;

public interface ProveedorRepository  extends JpaRepository <Proveedor,Long>{
@Query("SELECT p FROM Proveedor p  WHERE activo like :activo")
public List<Proveedor> findActivo(@Param("activo")String activo);
}
