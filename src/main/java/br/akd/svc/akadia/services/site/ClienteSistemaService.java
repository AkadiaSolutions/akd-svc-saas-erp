package br.akd.svc.akadia.services.site;

import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.repositories.site.impl.ClienteSistemaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteSistemaService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    public ClienteSistemaEntity primeiraEtapaCadastroCliente(ClienteSistemaDto clienteSistemaDto) {

        ClienteSistemaEntity clienteSistema = (
                ClienteSistemaEntity.builder()
                        .senha(clienteSistemaDto.getSenha())
                        .email(clienteSistemaDto.getEmail())
                        .build()
        );

        return clienteSistemaRepositoryImpl.salvaNovoCliente(clienteSistema);
    }

}
