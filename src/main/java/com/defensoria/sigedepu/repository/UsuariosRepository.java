package com.defensoria.sigedepu.repository;

import com.defensoria.integral.model.constancias.Participantes;
import org.springframework.data.jpa.repository.JpaRepository;

import com.defensoria.sigedepu.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    
    @Query("SELECT p FROM Usuario p  WHERE  CONCAT(p.nombre,' ',p.paterno,' ',p.materno) like:nombre and p.activo=1 ")
    List<Usuario> findByUsuario(@Param("nombre") String nombre);

}
