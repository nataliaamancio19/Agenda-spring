package ifpe.pratica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifpe.pratica.entity.*;

public interface AgendaRepository extends JpaRepository<Contato, Long> {
	
	 @Override
	 List<Contato> findAll();

	
}
