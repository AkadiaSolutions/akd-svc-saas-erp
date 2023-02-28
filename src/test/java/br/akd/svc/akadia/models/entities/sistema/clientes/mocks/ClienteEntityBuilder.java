package br.akd.svc.akadia.models.entities.sistema.clientes.mocks;

import br.akd.svc.akadia.models.entities.sistema.clientes.ClienteEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClienteEntityBuilder {

    ClienteEntityBuilder() {
    }

    ClienteEntity clienteEntity;

    public static ClienteEntityBuilder builder() {
        ClienteEntityBuilder builder = new ClienteEntityBuilder();
        builder.clienteEntity = new ClienteEntity();
        builder.clienteEntity.setId(1L);
        builder.clienteEntity.setDataCadastro(LocalDate.of(2023, 2, 27).toString());
        builder.clienteEntity.setHoraCadastro(LocalTime.of(17, 40).toString());
        builder.clienteEntity.setDataNascimento("1998-07-21");
        builder.clienteEntity.setNome("Gabriel Lagrota");
        builder.clienteEntity.setCpfCnpj("582.645.389-32");
        builder.clienteEntity.setInscricaoEstadual("145574080114");
        builder.clienteEntity.setEmail("gabrielafonso@mail.com.br");
        builder.clienteEntity.setEndereco(null);
        builder.clienteEntity.setTelefone(null);
        builder.clienteEntity.setColaboradorResponsavel(null);
        builder.clienteEntity.setEmpresa(null);
        return builder;
    }

    public ClienteEntity build() {
        return this.clienteEntity;
    }

}
