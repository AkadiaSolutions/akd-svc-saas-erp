package br.akd.svc.akadia.repositories.sistema.colaboradores.impl;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColaboradorRepositoryImpl {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public List<ColaboradorEntity> buscaTodosOsColaboradores() {
        return colaboradorRepository.findAll();
    }

    @Transactional
    public ColaboradorEntity implementaPersistencia(ColaboradorEntity colaborador) {
        return colaboradorRepository.save(colaborador);
    }

}