package br.akd.svc.akadia.repositories.bckoff.impl;

import br.akd.svc.akadia.repositories.bckoff.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensagemRepositoryImpl {

    @Autowired
    MensagemRepository mensagemRepository;

}
