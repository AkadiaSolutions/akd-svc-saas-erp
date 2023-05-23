package br.akd.svc.akadia.utils;

import br.akd.svc.akadia.models.entities.sistema.colaboradores.AcessoSistemaEntity;
import br.akd.svc.akadia.models.enums.sistema.colaboradores.PermissaoEnum;
import br.akd.svc.akadia.services.exceptions.UnauthorizedAccessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {

    SecurityUtil(){}

    public static void verificaSePodeRealizarAlteracoes(AcessoSistemaEntity acesso) {
        if (!acesso.getPermissaoEnum().equals(PermissaoEnum.LEITURA_AVANCADA_ALTERACAO)
                && !acesso.getPermissaoEnum().equals(PermissaoEnum.LEITURA_BASICA_ALTERACAO))
            throw new UnauthorizedAccessException("Você não possui permissão para realizar alterações nos dados do sistema. " +
                    "Entre em contato com o administrador e tente novamente");
    }

}
