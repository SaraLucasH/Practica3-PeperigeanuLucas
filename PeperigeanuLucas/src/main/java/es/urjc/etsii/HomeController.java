package es.urjc.etsii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private HechoService hechoService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private TiempoService tiempoService;
    
    @RequestMapping("/")
    public String inicio() {
    	return "index";
    }    
}
