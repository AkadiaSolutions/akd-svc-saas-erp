package br.akd.svc.akadia.repositories.sistema.colaboradores.impl;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.ColaboradorEntity;
import br.akd.svc.akadia.repositories.sistema.colaboradores.ColaboradorRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ColaboradorRepositoryImpl {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public List<ColaboradorEntity> implementaBuscaTodosOsColaboradores() {
        log.debug("Método de serviço que implementa busca por todos os colaboradores cadastrados acessado");
        return colaboradorRepository.findAll();
    }

    @Transactional
    public ColaboradorEntity implementaPersistencia(ColaboradorEntity colaborador) {
        log.debug("Método de serviço que implementa persistência do colaborador acessado");
        return colaboradorRepository.save(colaborador);
    }

    @Transactional
    public void implementaPersistenciaEmMassa(List<ColaboradorEntity> colaboradores) {
        log.debug("Método de serviço que implementa persistência em massa do colaborador acessado");
        colaboradorRepository.saveAll((colaboradores));
    }

    public ColaboradorEntity implementaBuscaPorId(Long id, Long idEmpresa) {
        log.debug("Método que implementa busca de colaborador por id acessado. Id: {}", id);

        Optional<ColaboradorEntity> colaboradorOptional = colaboradorRepository.buscaPorId(id, idEmpresa);

        ColaboradorEntity colaboradorEntity;
        if (colaboradorOptional.isPresent()) {
            colaboradorEntity = colaboradorOptional.get();
            log.debug("Colaborador encontrado: {}", colaboradorEntity);
        } else {
            log.warn("Nenhum colaborador foi encontrado com o id {}", id);
            throw new ObjectNotFoundException("Nenhum colaborador foi encontrado com o id informado");
        }
        log.debug("Retornando o colaborador encontrado...");
        return colaboradorEntity;
    }

}
