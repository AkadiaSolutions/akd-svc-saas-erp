package br.akd.svc.akadia.repositories.sistema.colaboradores.impl;

import br.akd.svc.akadia.models.entities.global.ArquivoEntity;
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

    public List<ColaboradorEntity> implementaBuscaPorTodos(Long id) {
        log.debug("Método de serviço que implementa busca por todos os colaboradores cadastrados em uma empresa acessado");
        return colaboradorRepository.buscaTodos(id);
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

    public ArquivoEntity implementaBuscaDeImagemDePerfilPorId(Long id, Long idEmpresa) {
        log.debug("Método que implementa busca de imagem de perfil de colaborador por id acessado. Id: {}", id);

        Optional<ArquivoEntity> arquivoEntityOptional = colaboradorRepository.buscaImagemPerfilPorId(id, idEmpresa);

        ArquivoEntity arquivoEntity;
        if (arquivoEntityOptional.isPresent()) {
            arquivoEntity = arquivoEntityOptional.get();
            log.debug("Imagem de perfil encontrada: {}", arquivoEntity.getNome());
        } else {
            log.warn("Nenhuma imagem de perfil foi encontrada com o id {}", id);
            throw new ObjectNotFoundException("Nenhuma imagem de perfil foi encontrada com o id informado");
        }
        log.debug("Retornando a imagem de perfil encontrada...");
        return arquivoEntity;
    }

    public List<ColaboradorEntity> implementaBuscaPorIdEmMassa(List<Long> ids) {
        log.debug("Método que implementa busca de colaborador por id em massa acessado. Ids: {}", ids.toString());

        List<ColaboradorEntity> colaboradores = colaboradorRepository.findAllById(ids);

        if (!colaboradores.isEmpty()) {
            log.debug("{} Colaboradores encontrados", colaboradores.size());
        } else {
            log.warn("Nenhum colaborador foi encontrado");
            throw new ObjectNotFoundException("Nenhum colaborador foi encontrado com os ids informados");
        }
        log.debug("Retornando os colaboradores encontrados...");
        return colaboradores;
    }

}
