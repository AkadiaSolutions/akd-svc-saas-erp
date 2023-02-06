package br.akd.svc.akadia.models.entities.site.mocks;

import br.akd.svc.akadia.models.entities.global.mocks.EnderecoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.models.entities.site.ClienteSistemaEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClienteSistemaEntityBuilder {

    ClienteSistemaEntityBuilder() {
    }

    ClienteSistemaEntity clienteSistemaEntity;

    public static ClienteSistemaEntityBuilder builder() {
        ClienteSistemaEntityBuilder builder = new ClienteSistemaEntityBuilder();
        builder.clienteSistemaEntity = new ClienteSistemaEntity();

        builder.clienteSistemaEntity.setId(1L);
        builder.clienteSistemaEntity.setCodigoClienteAsaas("cus_000005113026");
        builder.clienteSistemaEntity.setDataCadastro(LocalDate.of(2023, 2, 3).toString());
        builder.clienteSistemaEntity.setHoraCadastro(LocalTime.of(10, 40).toString());
        builder.clienteSistemaEntity.setDataNascimento("2023-02-03");
        builder.clienteSistemaEntity.setEmail("fulano@gmail.com");
        builder.clienteSistemaEntity.setNome("Fulano");
        builder.clienteSistemaEntity.setSenha("123");
        builder.clienteSistemaEntity.setCpf("12345678910");
        builder.clienteSistemaEntity.setSaldo(0.00);
        builder.clienteSistemaEntity.setPlano(null);
        builder.clienteSistemaEntity.setTelefone(null);
        builder.clienteSistemaEntity.setEndereco(null);
        builder.clienteSistemaEntity.setCartao(null);
        builder.clienteSistemaEntity.setPagamentos(new ArrayList<>());
        builder.clienteSistemaEntity.setEmpresas(new ArrayList<>());
        return builder;
    }

    public ClienteSistemaEntityBuilder comOutroEmail() {
        this.clienteSistemaEntity.setEmail("ciclano@gmail.com");
        return this;
    }

    public ClienteSistemaEntityBuilder comEndereco() {
        this.clienteSistemaEntity.setEndereco(EnderecoEntityBuilder.builder().build());
        return this;
    }

    public ClienteSistemaEntityBuilder comTelefone() {
        this.clienteSistemaEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        return this;
    }

    public ClienteSistemaEntityBuilder comPlanoComPagamentoNoCredito() {
        this.clienteSistemaEntity.setPlano(PlanoEntityBuilder.builder().pagamentoNoCredito().build());
        this.clienteSistemaEntity.setCartao(CartaoEntityBuilder.builder().build());
        return this;
    }

    public ClienteSistemaEntity build() {
        return clienteSistemaEntity;
    }

}
