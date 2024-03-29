package br.akd.svc.akadia.modules.erp.despesas.repository.impl;

import br.akd.svc.akadia.modules.erp.despesas.models.entity.DespesaEntity;
import br.akd.svc.akadia.modules.erp.despesas.repository.DespesaRepository;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
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

    public List<DespesaEntity> implementaBuscaPorTodos(Long idEmpresa) {
        log.debug("Método que implementa busca por todas as despesas acessado");
        return despesaRepository.buscaTodos(idEmpresa);
    }

    public List<DespesaEntity> implementaBuscaPorIdEmMassa(List<Long> ids) {
        log.debug("Método que implementa busca de despesa por id em massa acessado. Ids: {}", ids.toString());

        List<DespesaEntity> despesas = despesaRepository.findAllById(ids);

        if (!despesas.isEmpty()) {
            log.debug("{} Despesas encontradas", despesas.size());
        } else {
            log.warn("Nenhuma despesa foi encontrada");
            throw new ObjectNotFoundException("Nenhuma despesa foi encontrada com os ids informados");
        }
        log.debug("Retornando as despesas encontradas...");
        return despesas;
    }

}
