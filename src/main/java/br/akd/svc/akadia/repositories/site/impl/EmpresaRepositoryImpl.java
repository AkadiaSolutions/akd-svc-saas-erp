package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.repositories.site.EmpresaRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaRepositoryImpl {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<EmpresaEntity> buscaTodasEmpresas() {
        return empresaRepository.findAll();
    }

    @Transactional
    public EmpresaEntity implementaPersistencia(EmpresaEntity empresaEntity) {
        return empresaRepository.save(empresaEntity);
    }

    public Optional<EmpresaEntity> implementaBuscaPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public Optional<EmpresaEntity> implementaBuscaPorEndpoint(String endpoint) {
        return empresaRepository.findByEndpoint(endpoint);
    }

    public Optional<EmpresaEntity> implementaBuscaPorRazaoSocial(String razaoSocial) {
        return empresaRepository.findByRazaoSocial(razaoSocial);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoEstadual(String inscricaoEstadual) {
        return empresaRepository.findByInscricaoEstadual(inscricaoEstadual);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoMunicipal(String inscricaoMunicipal) {
        return empresaRepository.findByInscricaoMunicipal(inscricaoMunicipal);
    }

    public EmpresaEntity implementaBuscaPorId(Long id) {
        Optional<EmpresaEntity> empresaEntity =
                empresaRepository.findById(id);

        EmpresaEntity empresa;
        if (empresaEntity.isPresent()) empresa = empresaEntity.get();
        else throw new ObjectNotFoundException("Nenhuma empresa foi encontrada com o id informado");

        return empresa;
    }

}
