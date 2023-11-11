package br.akd.svc.akadia.modules.erp.patrimonios.repository.impl;

import br.akd.svc.akadia.modules.erp.patrimonios.models.entity.PatrimonioEntity;
import br.akd.svc.akadia.modules.erp.patrimonios.repository.PatrimonioRepository;
import br.akd.svc.akadia.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatrimonioRepositoryImpl {

    @Autowired
    PatrimonioRepository patrimonioRepository;

    @Transactional
    public PatrimonioEntity implementaPersistencia(PatrimonioEntity patrimonioEntity) {
        log.debug("Método de serviço que implementa persistência do objeto");
        return patrimonioRepository.save(patrimonioEntity);
    }

    @Transactional
    public void implementaPersistenciaEmMassa(List<PatrimonioEntity> patrimonioNovo) {
        log.debug("Método de serviço que implementa persistência em massa do patrimônio acessado");
        patrimonioRepository.saveAll((patrimonioNovo));
    }

    public PatrimonioEntity implementaBuscaPorId(Long id, Long idEmpresa) {
        log.debug("Método que implementa busca de patrimônio por id acessado. Id: {}", id);

        Optional<PatrimonioEntity> patrimonioOptional = patrimonioRepository.buscaPorId(id, idEmpresa);

        PatrimonioEntity patrimonioEntity;
        if (patrimonioOptional.isPresent()) {
            patrimonioEntity = patrimonioOptional.get();
            log.debug("Patrimônio encontrado: {}", patrimonioEntity);
        } else {
            log.warn("Nenhum patrimônio foi encontrado com o id {}", id);
            throw new ObjectNotFoundException("Nenhum patrimônio foi encontrado com o id informado");
        }
        log.debug("Retornando o patrimônio encontrado...");
        return patrimonioEntity;
    }

    public List<PatrimonioEntity> implementaBuscaPorIdEmMassa(List<Long> ids) {
        log.debug("Método que implementa busca de patrimônios por id em massa acessado. Ids: {}", ids.toString());

        List<PatrimonioEntity> patrimonios = patrimonioRepository.findAllById(ids);

        if (!patrimonios.isEmpty()) {
            log.debug("{} Patrimônios encontrados", patrimonios.size());
        } else {
            log.warn("Nenhum patrimonio foi encontrado");
            throw new ObjectNotFoundException("Nenhum patrimônio foi encontrado com os ids informados");
        }
        log.debug("Retornando os patrimonios encontrados...");
        return patrimonios;
    }

    public List<PatrimonioEntity> implementaBuscaPorTodos(Long id) {
        log.debug("Método de serviço que implementa busca por todos os patrimonios cadastrados em uma empresa acessado");
        return patrimonioRepository.buscaTodos(id);
    }
}
