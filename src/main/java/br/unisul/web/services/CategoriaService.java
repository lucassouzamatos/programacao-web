package br.unisul.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.web.domain.Categoria;
import br.unisul.web.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository rep;
	
	public Categoria find (Integer id) {
		Optional<Categoria> obj = rep.findById(id);
		return obj.orElse(null);
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return rep.save(obj);
	}
	
	public Categoria update (Categoria obj) {
		find(obj.getId());
		return rep.save(obj);
	}
	
	public void delete (Integer id) {
		find(id);
		rep.deleteById(id);
	}
	
	public List<Categoria> findAll(){
		return rep.findAll();
	}
	
	public List<Categoria> findByNome(String nome) {
		List<Categoria> list = rep.findLikeNome(nome);
		return list;
	}
}
