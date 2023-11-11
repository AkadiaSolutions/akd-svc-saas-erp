package br.akd.svc.akadia.utils;

import br.akd.svc.akadia.modules.global.entity.AcessoSistemaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.enums.PermissaoEnum;
import br.akd.svc.akadia.exceptions.UnauthorizedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityUtil {

    SecurityUtil(){}

    public static void verificaSePodeRealizarAlteracoes(AcessoSistemaEntity acesso) {
        if (!acesso.getPermissaoEnum().equals(PermissaoEnum.LEITURA_AVANCADA_ALTERACAO)
                && !acesso.getPermissaoEnum().equals(PermissaoEnum.LEITURA_BASICA_ALTERACAO))
            throw new UnauthorizedAccessException("Você não possui permissão para realizar alterações nos dados do sistema. " +
                    "Entre em contato com o administrador e tente novamente");
    }

}
