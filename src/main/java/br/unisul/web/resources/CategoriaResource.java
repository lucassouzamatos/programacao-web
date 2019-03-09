package br.unisul.web.resources;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.unisul.web.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> getAll() {
		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(new Categoria(1, "Informática"));
		lista.add(new Categoria(1, "Test"));
		
		return lista;
	}
}
