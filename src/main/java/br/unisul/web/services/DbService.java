package br.unisul.web.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.web.domain.Categoria;
import br.unisul.web.domain.Cidade;
import br.unisul.web.domain.Cliente;
import br.unisul.web.domain.Endereco;
import br.unisul.web.domain.Estado;
import br.unisul.web.domain.ItemPedido;
import br.unisul.web.domain.Pedido;
import br.unisul.web.domain.Produto;
import br.unisul.web.domain.enums.TipoCliente;
import br.unisul.web.repositories.CategoriaRepository;
import br.unisul.web.repositories.CidadeRepository;
import br.unisul.web.repositories.ClienteRepository;
import br.unisul.web.repositories.EnderecoRepository;
import br.unisul.web.repositories.EstadoRepository;
import br.unisul.web.repositories.ItemPedidoRepository;
import br.unisul.web.repositories.PedidoRepository;
import br.unisul.web.repositories.ProdutoRepository;

@Service
public class DbService {
	
	@Autowired
	private CategoriaRepository catRep;
	
	@Autowired
	private EstadoRepository estRep;
	
	@Autowired
	private ProdutoRepository prodRep;
	
	@Autowired
	private CidadeRepository cidRep;
	
	@Autowired
	private EnderecoRepository endRep;
	
	@Autowired
	private ClienteRepository cliRep;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void inicializaBancoDeDados() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Estado e1 = new Estado(null, "Paraná");
		Estado e2 = new Estado(null, "Santa Catarina");
		Estado e3 = new Estado(null, "Rio Grande do Sul");
		
		Cidade c1 = new Cidade(null, "Curitiba", e1);
		Cidade c2 = new Cidade(null, "Tubarão", e2);
		Cidade c3 = new Cidade(null, "Gravatal", e2);
		Cidade c4 = new Cidade(null, "Laguna", e2);
		Cidade c5 = new Cidade(null, "Porto Alegre", e3);
		Cidade c6 = new Cidade(null, "Guaíba", e3);
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3, c4));
		e3.getCidades().addAll(Arrays.asList(c5, c6));
		
		estRep.saveAll(Arrays.asList(e1,e2,e3));
		cidRep.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		catRep.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		prodRep.saveAll(Arrays.asList(p1,p2,p3));
		
		Cliente cliente = new Cliente(null, "Lucgsdas", "lucgsds@hotgsdmail.com", "110011dsg0010", 
				TipoCliente.PESSOAFISICA);		
		
		Endereco endereco = new Endereco(null, "Rua faskfja fsiAlice da Sfsailva", "343", "Sfasupfasfaerfasmercado Lídfassfaaer", "Rgsgsdio Bfofsfnito", "00000*0", cliente, c1);
		Endereco endereco1 = new Endereco(null, "Av Mdarechal Deodoradfaso", "31", "Lgdsojas Benoiftsgg", "Cesdhnhdftro", "000000", cliente, c2);
		
		cliRep.saveAll(Arrays.asList(cliente));
		endRep.saveAll(Arrays.asList(endereco, endereco1));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente, endereco);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente, endereco1);
		cliente.getPedidos().addAll(Arrays.asList(ped1, ped2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));	
	}

}