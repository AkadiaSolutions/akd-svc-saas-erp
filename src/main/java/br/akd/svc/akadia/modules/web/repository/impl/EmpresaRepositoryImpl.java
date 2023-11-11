package br.akd.svc.akadia.modules.web.repository.impl;

import br.akd.svc.akadia.modules.web.models.entity.empresa.EmpresaEntity;
import br.akd.svc.akadia.modules.web.repository.EmpresaRepository;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmpresaRepositoryImpl {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<EmpresaEntity> buscaTodasEmpresas() {
        log.debug("Método que implementa busca por todas as empresas cadastradas acessado");
        return empresaRepository.findAll();
    }

    @Transactional
    public EmpresaEntity implementaPersistencia(EmpresaEntity empresaEntity) {
        log.debug("Método que implementa persistência de nova empresa acessado. Empresa: {}", empresaEntity);
        return empresaRepository.save(empresaEntity);
    }

    public Optional<EmpresaEntity> implementaBuscaPorCnpj(String cnpj) {
        log.debug("Método que implementa busca de empresa por cnpj acessado. Cnpj: {}", cnpj);
        return empresaRepository.findByCnpj(cnpj);
    }

    public Optional<EmpresaEntity> implementaBuscaPorEndpoint(String endpoint) {
        log.debug("Método que implementa busca de empresa por endpoint acessado. Endpoint: {}", endpoint);
        return empresaRepository.findByEndpoint(endpoint);
    }

    public Optional<EmpresaEntity> implementaBuscaPorRazaoSocial(String razaoSocial) {
        log.debug("Método que implementa busca de empresa por razão social acessado. Razão social: {}", razaoSocial);
        return empresaRepository.findByRazaoSocial(razaoSocial);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoEstadual(String inscricaoEstadual) {
        log.debug("Método que implementa busca de empresa por inscrição estadual acessado. Inscrição estaudal: {}", inscricaoEstadual);
        return empresaRepository.findByInscricaoEstadual(inscricaoEstadual);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoMunicipal(String inscricaoMunicipal) {
        log.debug("Método que implementa busca de empresa por inscrição municipal acessado. Inscrição municipal: {}", inscricaoMunicipal);
        return empresaRepository.findByInscricaoMunicipal(inscricaoMunicipal);
    }

    public EmpresaEntity implementaBuscaPorId(Long id) {

        log.debug("Método que implementa busca de empresa por id acessado. Id: {}", id);

        Optional<EmpresaEntity> empresaEntity =
                empresaRepository.findById(id);

        EmpresaEntity empresa;
        if (empresaEntity.isPresent()) {
            empresa = empresaEntity.get();
            log.debug("Empresa encontrada: {}", empresa);
        }
        else {
            log.warn("Nenhuma empresa foi encontrada com o id {}", id);
            throw new ObjectNotFoundException("Nenhuma empresa foi encontrada com o id informado");
        }

        log.debug("Retornando a empresa encontrada...");
        return empresa;
    }

}
