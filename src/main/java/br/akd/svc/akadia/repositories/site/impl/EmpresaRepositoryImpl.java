package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.repositories.site.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaRepositoryImpl {

    @Autowired
    EmpresaRepository empresaRepository;

    public Optional<EmpresaEntity> implementaBuscaPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public Optional<EmpresaEntity> implementaBuscaPorEndpoint(String endpoint) {
        return empresaRepository.findByEndpoint(endpoint);
    }

}
