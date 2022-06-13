/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.adminpersonal.Municipios;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
@Entity
@Table(name="tblmunicipios")
public interface MunicipiosRepository extends JpaRepository<Municipios, Long>{
     @Query("SELECT m FROM Municipios m  where m.descMunicipio like:descMunicipio")
	List<Municipios>findDescMunicipios(@Param("descMunicipio")String descMunicipio);
    
}
