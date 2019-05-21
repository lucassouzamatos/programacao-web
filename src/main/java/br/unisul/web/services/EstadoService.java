package br.unisul.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.web.domain.Estado;
import br.unisul.web.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository rep;
	
	public Estado find (Integer id) {
		Optional<Estado> obj = rep.findById(id);
		return obj.orElse(null);
	}
	
	public Estado insert (Estado obj) {
		obj.setId(null);
		return rep.save(obj);
	}
	
	public Estado update (Estado obj) {
		find(obj.getId());
		return rep.save(obj);
	}
	
	public void delete (Integer id) {
		find(id);
		rep.deleteById(id);
	}
	
	public List<Estado> findAll(){
		return rep.findAll();
	}
	
	public List<Estado> findByNome(String nome) {
		List<Estado> list = rep.findLikeNome(nome);
		return list;
	}
}
