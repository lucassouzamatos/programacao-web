package br.unisul.web.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.web.domain.Cidade;
import br.unisul.web.dtos.CidadeDto;
import br.unisul.web.resources.utils.URL;
import br.unisul.web.services.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeResource {
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cidade> find(@PathVariable Integer id) {
		Cidade cidade = cidadeService.find(id);
		return ResponseEntity.ok().body(cidade);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Cidade> create(@RequestBody Cidade cidade) {
		cidade = cidadeService.create(cidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(cidade.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cidade obj, @PathVariable Integer id){
		obj.setId(id);
		obj = cidadeService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		cidadeService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDto>> findAll() {
		List<Cidade> lista = cidadeService.findAll();

		List<CidadeDto> listaDTO = lista.stream().map(cidade -> new CidadeDto(cidade))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}

	@RequestMapping(value="search", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDto>> find(
			@RequestParam(value = "nome", defaultValue = "") String nome) {

		String nomeDecoded = URL.decodeParam(nome);
		List<Cidade> list = cidadeService.search(nomeDecoded);
		List<CidadeDto> listDto = new ArrayList<CidadeDto>();

		for (Cidade c : list) {
			listDto.add(new CidadeDto(c));
		}

		return ResponseEntity.ok().body(listDto);
	}
}