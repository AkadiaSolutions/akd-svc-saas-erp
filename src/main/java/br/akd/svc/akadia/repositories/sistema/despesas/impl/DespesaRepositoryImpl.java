package br.akd.svc.akadia.repositories.sistema.despesas.impl;

import br.akd.svc.akadia.models.entities.sistema.despesas.DespesaEntity;
import br.akd.svc.akadia.repositories.sistema.despesas.DespesaRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DespesaRepositoryImpl {

    @Autowired
    DespesaRepository despesaRepository;

    @Transactional
    public DespesaEntity implementaPersistencia(DespesaEntity despesaEntity) {
        log.debug("Método de serviço que implementa persistência do despesaEntity");
        return despesaRepository.save(despesaEntity);
    }

    @Transactional
    public void implementaPersistenciaEmMassa(List<DespesaEntity> despesas) {
        log.debug("Método de serviço que implementa persistência em massa de despesas acessado");
        despesaRepository.saveAll((despesas));
    }

    public DespesaEntity implementaBuscaPorId(Long id, Long idEmpresa) {
        log.debug("Método que implementa busca de despesa por id acessado. Id: {}", id);

        Optional<DespesaEntity> despesaOptional = despesaRepository.buscaPorId(id, idEmpresa);

        DespesaEntity despesaEntity;
        if (despesaOptional.isPresent()) {
            despesaEntity = despesaOptional.get();
            log.debug("Despesa encontrada: {}", despesaEntity);
        } else {
            log.warn("Nenhuma despesa foi encontrada com o id {}", id);
            throw new ObjectNotFoundException("Nenhuma despesa foi encontrada com o id informado");
        }
        log.debug("Retornando a despesa encontrada...");
        return despesaEntity;
    }

}
