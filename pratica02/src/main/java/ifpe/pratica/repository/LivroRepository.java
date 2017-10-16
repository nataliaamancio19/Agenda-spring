package ifpe.pratica.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifpe.pratica.entity.*;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	
	List<Livro> findByAutor(String autor);
	
	List<Livro> findByTitulo(String titulo);
	
	List<Livro> findByIsbn(String isbn);
	
}
