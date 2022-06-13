package com.defensoria.integral.repository.sigedepu2;

import java.util.List;

import com.defensoria.integral.model.subtecnica.Materia;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MateriasRepository extends JpaRepository<Materia,Long> {

    List<Materia> findByDescMateriaLike(String descMateria);
    
}
