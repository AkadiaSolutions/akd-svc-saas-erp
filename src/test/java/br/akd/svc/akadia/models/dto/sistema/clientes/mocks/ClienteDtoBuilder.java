package br.akd.svc.akadia.models.dto.sistema.clientes.mocks;

import br.akd.svc.akadia.models.dto.global.mocks.EnderecoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.models.dto.sistema.clientes.ClienteDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClienteDtoBuilder {

    ClienteDtoBuilder() {
    }

    ClienteDto clienteDto;

    public static ClienteDtoBuilder builder() {
        ClienteDtoBuilder builder = new ClienteDtoBuilder();
        builder.clienteDto = new ClienteDto();
        builder.clienteDto.setId(1L);
        builder.clienteDto.setDataCadastro(LocalDate.of(2023, 2, 27).toString());
        builder.clienteDto.setHoraCadastro(LocalTime.of(17, 40).toString());
        builder.clienteDto.setDataNascimento("1998-07-21");
        builder.clienteDto.setNome("Gabriel Lagrota");
        builder.clienteDto.setCpfCnpj("582.645.389-32");
        builder.clienteDto.setInscricaoEstadual("145574080114");
        builder.clienteDto.setEmail("gabrielafonso@mail.com.br");
        builder.clienteDto.setExclusaoCliente(null);
        builder.clienteDto.setEndereco(null);
        builder.clienteDto.setTelefone(null);
        builder.clienteDto.setColaboradorResponsavel(null);
        builder.clienteDto.setEmpresa(null);
        return builder;
    }

    public ClienteDtoBuilder comObjetoExclusaoFalse() {
        this.clienteDto.setExclusaoCliente(ExclusaoClienteDtoBuilder.builder().build());
        return this;
    }

    public ClienteDtoBuilder comEndereco() {
        this.clienteDto.setEndereco(EnderecoDtoBuilder.builder().build());
        return this;
    }

    public ClienteDtoBuilder comTelefone() {
        this.clienteDto.setTelefone(TelefoneDtoBuilder.builder().build());
        return this;
    }

    public ClienteDto build() {
        return this.clienteDto;
    }

}
