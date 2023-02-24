package br.akd.svc.akadia.repositories.site.impl;

import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;
import br.akd.svc.akadia.repositories.site.ClienteSistemaRepository;
import br.akd.svc.akadia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteSistemaRepositoryImpl {

    @Autowired
    ClienteSistemaRepository clienteSistemaRepository;

    public List<ClienteSistemaEntity> buscaTodosClientes() {
        return clienteSistemaRepository.findAll();
    }

    @Transactional
    public ClienteSistemaEntity implementaPersistencia(ClienteSistemaEntity clienteSistema) {
        return clienteSistemaRepository.save(clienteSistema);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorEmail(String email) {
        return clienteSistemaRepository.findByEmail(email);
    }

    public Optional<ClienteSistemaEntity> implementaBuscaPorCpf(String cpf) {
        return clienteSistemaRepository.findByCpf(cpf);
    }

    public ClienteSistemaEntity implementaBuscaPorId(Long id) {
        Optional<ClienteSistemaEntity> clienteOptional =
                clienteSistemaRepository.findById(id);

        ClienteSistemaEntity clienteSistema;
        if (clienteOptional.isPresent()) clienteSistema = clienteOptional.get();
        else throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o id informado");

        return clienteSistema;
    }

    public ClienteSistemaEntity implementaBuscaPorCodigoClienteAsaas(String codigoClienteAsaas) {
        Optional<ClienteSistemaEntity> clienteOptional =
                clienteSistemaRepository.findByCodigoClienteAsaas(codigoClienteAsaas);

        ClienteSistemaEntity clienteSistema;
        if (clienteOptional.isPresent()) clienteSistema = clienteOptional.get();
        else throw new ObjectNotFoundException("Nenhum cliente foi encontrado com o codigo Asaas informado");

        return clienteSistema;
    }

    public void implementaBuscaPorPlanosVencidosAtivos() {
        clienteSistemaRepository
                .buscaPorClientesComPlanosVencidosAtivos("ATIVO", LocalDate.now().toString());
    }

}
