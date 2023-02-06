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
        builder.clienteSistemaEntity.setPlano(PlanoEntityBuilder.builder().build());
        builder.clienteSistemaEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        builder.clienteSistemaEntity.setEndereco(EnderecoEntityBuilder.builder().build());
        builder.clienteSistemaEntity.setCartao(CartaoEntityBuilder.builder().build());
        builder.clienteSistemaEntity.setPagamentos(new ArrayList<>());
        builder.clienteSistemaEntity.setEmpresas(new ArrayList<>());
        return builder;
    }

    public ClienteSistemaEntityBuilder comPlanoComPagamentoNoCredito() {
        this.clienteSistemaEntity.setPlano(PlanoEntityBuilder.builder().pagamentoNoCredito().build());
        return this;
    }

    public ClienteSistemaEntityBuilder comPagamento() {
        this.clienteSistemaEntity.getPagamentos().add(PagamentoSistemaEntityBuilder.builder().build());
        return this;
    }

    public ClienteSistemaEntityBuilder comEmpresa() {
        this.clienteSistemaEntity.getEmpresas().add(EmpresaEntityBuilder.builder().build());
        return this;
    }

    public ClienteSistemaEntity build() {
        return clienteSistemaEntity;
    }

}
