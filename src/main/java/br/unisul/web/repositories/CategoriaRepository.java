package br.unisul.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.unisul.web.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	@Query("SELECT categoria FROM Categoria categoria WHERE categoria.nome LIKE %:nome%")
	List<Categoria> findLikeNome(String nome);
}
