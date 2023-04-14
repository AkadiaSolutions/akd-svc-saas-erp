package br.akd.svc.akadia.models.dto.sistema.clientes.responses.mocks;

import br.akd.svc.akadia.models.dto.sistema.clientes.responses.ClienteResponse;
import br.akd.svc.akadia.models.enums.sistema.clientes.StatusClienteEnum;
import br.akd.svc.akadia.models.enums.sistema.clientes.TipoPessoaEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClienteResponseBuilder {
    ClienteResponseBuilder() {
    }

    ClienteResponse clienteResponse;

    public static ClienteResponseBuilder builder() {
        ClienteResponseBuilder builder = new ClienteResponseBuilder();
        builder.clienteResponse = new ClienteResponse();
        builder.clienteResponse.setId(1L);
        builder.clienteResponse.setDataCadastro(LocalDate.of(2023, 2, 27).toString());
        builder.clienteResponse.setHoraCadastro(LocalTime.of(17, 40).toString());
        builder.clienteResponse.setDataNascimento("1998-07-21");
        builder.clienteResponse.setNome("Gabriel Lagrota");
        builder.clienteResponse.setCpfCnpj("582.645.389-32");
        builder.clienteResponse.setInscricaoEstadual("145574080114");
        builder.clienteResponse.setEmail("gabrielafonso@mail.com.br");
        builder.clienteResponse.setStatusCliente(StatusClienteEnum.COMUM);
        builder.clienteResponse.setTipoPessoa(TipoPessoaEnum.FISICA);
        builder.clienteResponse.setQtdOrdensRealizadas(0);
        builder.clienteResponse.setGiroTotal(0.0);
        builder.clienteResponse.setExclusaoCliente(null);
        builder.clienteResponse.setEndereco(null);
        builder.clienteResponse.setTelefone(null);
        builder.clienteResponse.setNomeColaboradorResponsavel("Fulano");
        return builder;
    }

    public ClienteResponse build() {
        return this.clienteResponse;
    }
}
