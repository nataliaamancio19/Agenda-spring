package ifpe.pratica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ifpe.pratica.entity.Livro;
import ifpe.pratica.repository.LivroRepository;

@Controller
@RequestMapping(value = "/livro")
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@RequestMapping(value = "/{autor}", method = RequestMethod.GET)
	public String listaLivros(@PathVariable("autor") String autor, Model model) 
	{
		List<Livro> listaLivros = livroRepository.findByAutor(autor);
		
		if(listaLivros != null) 
		{
			// Primeiro atributo é como vamos chamar essa lista lá no html.
			model.addAttribute("livros", listaLivros);
		}
		
		return "listaLivros";
	}
	
	
	@RequestMapping(value = "/{autor}", method = RequestMethod.POST)
	public String adicionaLivroAutor(@PathVariable("autor") String autor, Livro livro)
	{
		// O SAVE serve tanto para o salvar quanto para o atualizar.
		livro.setAutor(autor);
		livroRepository.save(livro);
		
		return "redirect:/{autor}";
	}
}
