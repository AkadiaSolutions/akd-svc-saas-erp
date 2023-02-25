package br.akd.svc.akadia.services.bckoff;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.entities.bckoff.LeadEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.enums.bckoff.OrigemLeadEnum;
import br.akd.svc.akadia.repositories.bckoff.impl.LeadRepositoryImpl;
import br.akd.svc.akadia.services.site.ClienteSistemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LeadService {

    @Autowired
    LeadRepositoryImpl leadRepositoryImpl;

    @Autowired
    ClienteSistemaService clienteSistemaService;

    public LeadEntity encaminhaLeadParaPersistencia(ClienteSistemaDto clienteSistemaDto) {
        log.debug("Método de tratamento de lead e encaminhamento para persistência acessado");

        log.debug("Iniciando construção do objeto lead com base no cliente recebido: {}", clienteSistemaDto);
        LeadEntity lead = (LeadEntity.builder()
                .origemLeadEnum(OrigemLeadEnum.PRE_CADASTRO)
                .email(clienteSistemaDto.getEmail())
                .telefone(TelefoneEntity.builder()
                        .tipoTelefoneEnum(clienteSistemaDto.getTelefone().getTipoTelefoneEnum())
                        .prefixo(clienteSistemaDto.getTelefone().getPrefixo())
                        .numero(clienteSistemaDto.getTelefone().getNumero())
                        .build())
                .build());
        log.debug("Lead construído com sucesso: {}", lead);

        log.debug("Iniciando acesso ao método de validação se e-mail já existe...");
        clienteSistemaService.validaSeEmailJaExiste(clienteSistemaDto);

        log.debug("Iniciando acesso ao método de implementação de persistência do lead...");
        LeadEntity leadPersistido = leadRepositoryImpl.implementaPersistencia(lead);

        log.info("Lead persistido com sucesso...");
        log.debug("Lead persistido: {}", leadPersistido);
        return leadPersistido;
    }

}
