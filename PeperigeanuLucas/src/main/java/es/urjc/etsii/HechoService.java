package es.urjc.etsii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HechoService {

    @Autowired
    private HechoRepository hechoRepository;

    public HechoService(){}
}
