package ifpe.pratica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import ifpe.pratica.entity.Contato;
import ifpe.pratica.entity.Endereco;
import ifpe.pratica.repository.AgendaRepository;


@Controller
@RequestMapping(value = "/agenda")
public class AgendaController {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@RequestMapping(value = "/{contatos}", method = RequestMethod.GET)
	public String listaContatos(Model model) 
	{
		List<Contato> listaContatos = agendaRepository.findAll();
		
		if(listaContatos != null) 
		{
			// Primeiro atributo é como vamos chamar essa lista lá no html.
			model.addAttribute("contatos", listaContatos);
		}
		
		return "listaContatos";
	}
	
	@RequestMapping(value = "/adicionaContato", method = RequestMethod.GET)
	   public String redirecionaAdicionarContato() {
		return "adicionaContato";
	   }
	
	@RequestMapping(value = "/visualizarContato", method = RequestMethod.GET)
	   public String redirecionaVisualizarContato() {
		return "visualizarContato";
	   }
	
	@RequestMapping(value = "/listaContatos", method = RequestMethod.GET)
	   public String redirecionaListaContato(Model model) {
		
		List<Contato> listaContatos = agendaRepository.findAll();
		
		if(listaContatos != null) 
		{
			// Primeiro atributo é como vamos chamar essa lista lá no html.
			model.addAttribute("contatos", listaContatos);
		}
		return "listaContatos";
	   }
	
	@RequestMapping(value = { "/{adicionaContato}", "/{visualizarContato}"}, method = RequestMethod.POST)
	public String adicionaContato(Contato contato)
	{
		String url;
		
		url = "https://viacep.com.br/ws/" + contato.getCep() + "/json/";
		// O SAVE serve tanto para o salvar quanto para o atualizar.
		agendaRepository.save(contato);
		 
			 if(contato.getCep() != null && !contato.getCep().isEmpty()) 
			 {
				 RestTemplate restTemplate = new RestTemplate();
				 Endereco endereco = restTemplate.getForObject(url, Endereco.class);
				 contato.setEndereco(endereco);
			 }
		return "visualizarContato";
	}
}
