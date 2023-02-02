package br.akd.svc.akadia.services.bckoff;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.entities.bckoff.LeadEntity;
import br.akd.svc.akadia.models.entities.global.TelefoneEntity;
import br.akd.svc.akadia.models.enums.bckoff.OrigemLeadEnum;
import br.akd.svc.akadia.repositories.bckoff.impl.LeadRepositoryImpl;
import br.akd.svc.akadia.services.site.ClienteSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {

    @Autowired
    LeadRepositoryImpl leadRepositoryImpl;

    @Autowired
    ClienteSistemaService clienteSistemaService;

    public LeadEntity encaminhaLeadParaPersistencia(ClienteSistemaDto clienteSistemaDto) {

        LeadEntity lead = (LeadEntity.builder()
                .origemLeadEnum(OrigemLeadEnum.PRE_CADASTRO)
                .email(clienteSistemaDto.getEmail())
                .telefone(TelefoneEntity.builder()
                        .tipoTelefoneEnum(clienteSistemaDto.getTelefone().getTipoTelefoneEnum())
                        .prefixo(clienteSistemaDto.getTelefone().getPrefixo())
                        .numero(clienteSistemaDto.getTelefone().getNumero())
                        .build())
                .build());

        clienteSistemaService.validaSeEmailJaExiste(clienteSistemaDto);
        return leadRepositoryImpl.implementaPersistencia(lead);
    }

}
