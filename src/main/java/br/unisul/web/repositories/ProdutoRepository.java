package br.unisul.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisul.web.domain.Categoria;
import br.unisul.web.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	List<Produto> findDistinctByNomeContainingAndCategoriasIn(
		String nome,
		List<Categoria> categorias
	);
}