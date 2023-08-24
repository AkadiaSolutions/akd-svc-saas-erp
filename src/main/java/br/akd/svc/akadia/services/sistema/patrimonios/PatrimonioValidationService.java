package br.akd.svc.akadia.services.sistema.patrimonios;

import br.akd.svc.akadia.models.entities.sistema.patrimonios.PatrimonioEntity;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatrimonioValidationService {
    public void validaSeObjetoEstaExcluido(PatrimonioEntity patrimonio, String mensagemCasoEstejaExcluido) {
        log.debug("Método de validação de patrimonio excluído acessado");
        if (patrimonio.getExclusao() != null) {
            log.debug("Patrimônio de id {}: Validação de patrimonio já excluído falhou. Não é possível realizar operações " +
                    "em um patrimonio que já foi excluído.", patrimonio.getId());
            throw new InvalidRequestException(mensagemCasoEstejaExcluido);
        }
        log.debug("O patrimonio de id {} não está excluído", patrimonio.getId());
    }
}
