package com.samara.otrs.repository;

import com.samara.otrs.model.Chamado;
import com.samara.otrs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByUsuario(User usuario);
}
