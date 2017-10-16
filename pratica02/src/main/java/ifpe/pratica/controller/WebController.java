package ifpe.pratica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller	
public class WebController {

	@RequestMapping( method = RequestMethod.GET, value = {"/hello"})
	public String hellworld() 
	{
		return "hello";
		
	}
	
}
