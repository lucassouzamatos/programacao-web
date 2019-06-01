package br.unisul.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.web.domain.Cidade;
import br.unisul.web.repositories.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository rep;

	public List<Cidade> findByEstado(Integer estadoId) {
		return rep.findCidades(estadoId);
	}
	
	public Cidade find(Integer id) {
		Optional<Cidade> cidade = rep.findById(id);
		return cidade.orElse(null);
	}

	public Cidade create(Cidade cidade) {
		return rep.save(cidade);
	}

	public Cidade update(Cidade cidade) {
		find(cidade.getId());
		return rep.save(cidade);
	}

	public void delete (Integer id) {
		find(id);
		rep.deleteById(id);
	}

	public List<Cidade> findAll(){
		return rep.findAll();
	}

	public List<Cidade> search(String nome) {
		return rep.findDistinctByNomeContaining(nome);
	}
}