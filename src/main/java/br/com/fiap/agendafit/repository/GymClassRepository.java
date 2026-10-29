package br.com.fiap.agendafit.repository;

import br.com.fiap.agendafit.model.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GymClassRepository extends JpaRepository<GymClass, Long>, JpaSpecificationExecutor<GymClass> {}
