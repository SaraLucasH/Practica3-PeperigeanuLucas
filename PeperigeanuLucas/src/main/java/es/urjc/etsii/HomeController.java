package es.urjc.etsii;

import org.springframework.beans.factory.annotation.Autowired;

public class HomeController {

    @Autowired
    private HechoService hechoService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private TiempoService tiempoService;
}
