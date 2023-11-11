package br.akd.svc.akadia.models.dto.sistema.clientes.requests.mocks;

import br.akd.svc.akadia.models.dto.global.mocks.EnderecoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.modules.erp.clientes.models.dto.request.ClienteRequest;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.StatusClienteEnum;
import br.akd.svc.akadia.modules.erp.clientes.models.enums.TipoPessoaEnum;

public class ClienteRequestBuilder {

    ClienteRequestBuilder() {
    }

    ClienteRequest clienteRequest;

    public static ClienteRequestBuilder builder() {
        ClienteRequestBuilder builder = new ClienteRequestBuilder();
        builder.clienteRequest = new ClienteRequest();
        builder.clienteRequest.setId(1L);
        builder.clienteRequest.setDataNascimento("1998-07-21");
        builder.clienteRequest.setNome("Gabriel Lagrota");
        builder.clienteRequest.setCpfCnpj("582.645.389-32");
        builder.clienteRequest.setInscricaoEstadual("145574080114");
        builder.clienteRequest.setEmail("gabrielafonso@mail.com.br");
        builder.clienteRequest.setStatusCliente(StatusClienteEnum.COMUM);
        builder.clienteRequest.setTipoPessoa(TipoPessoaEnum.FISICA);
        builder.clienteRequest.setEndereco(null);
        builder.clienteRequest.setTelefone(null);
        return builder;
    }

    public ClienteRequestBuilder comEndereco() {
        this.clienteRequest.setEndereco(EnderecoDtoBuilder.builder().build());
        return this;
    }

    public ClienteRequestBuilder comTelefone() {
        this.clienteRequest.setTelefone(TelefoneDtoBuilder.builder().build());
        return this;
    }

    public ClienteRequest build() {
        return this.clienteRequest;
    }

}
