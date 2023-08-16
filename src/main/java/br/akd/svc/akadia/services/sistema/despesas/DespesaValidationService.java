package br.akd.svc.akadia.services.sistema.despesas;

import br.akd.svc.akadia.models.entities.sistema.despesas.DespesaEntity;
import br.akd.svc.akadia.services.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DespesaValidationService {

    public void validaSeDespesaEstaExcluida(DespesaEntity despesa, String mensagemCasoEstejaExcluido) {
        log.debug("Método de validação de despesa excluído acessado");
        if (despesa.getExclusao() != null) {
            log.debug("Despesa de id {}: Validação de despesa já excluída falhou. Não é possível realizar operações " +
                    "em um despesa que já foi excluída.", despesa.getId());
            throw new InvalidRequestException(mensagemCasoEstejaExcluido);
        }
        log.debug("A despesa de id {} não está excluído", despesa.getId());
    }

}
