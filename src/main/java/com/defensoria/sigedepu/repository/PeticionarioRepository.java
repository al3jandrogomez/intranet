package com.defensoria.sigedepu.repository;

import com.defensoria.sigedepu.model.Peticionario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeticionarioRepository extends JpaRepository<Peticionario,Long>{
    
}
