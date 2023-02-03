package br.akd.svc.akadia.models.dto.site.mocks;

import br.akd.svc.akadia.models.dto.global.mocks.EnderecoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.models.dto.site.ClienteSistemaDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClienteSistemaDtoBuilder {

    ClienteSistemaDtoBuilder() {
    }

    ClienteSistemaDto clienteSistemaDto;

    public static ClienteSistemaDtoBuilder builder() {
        ClienteSistemaDtoBuilder builder = new ClienteSistemaDtoBuilder();
        builder.clienteSistemaDto = new ClienteSistemaDto();

        builder.clienteSistemaDto.setId(1L);
        builder.clienteSistemaDto.setCodigoClienteAsaas("cus_000005113026");
        builder.clienteSistemaDto.setDataCadastro(LocalDate.of(2023, 2, 3).toString());
        builder.clienteSistemaDto.setHoraCadastro(LocalTime.of(10, 40).toString());
        builder.clienteSistemaDto.setDataNascimento("2023-02-03");
        builder.clienteSistemaDto.setEmail("fulano@gmail.com");
        builder.clienteSistemaDto.setNome("Fulano");
        builder.clienteSistemaDto.setSenha("123");
        builder.clienteSistemaDto.setCpf("12345678910");
        builder.clienteSistemaDto.setSaldo(0.00);
        builder.clienteSistemaDto.setPlano(PlanoDtoBuilder.builder().build());
        builder.clienteSistemaDto.setTelefone(TelefoneDtoBuilder.builder().build());
        builder.clienteSistemaDto.setEndereco(EnderecoDtoBuilder.builder().build());
        builder.clienteSistemaDto.setCartao(CartaoDtoBuilder.builder().build());
        builder.clienteSistemaDto.setPagamentos(new ArrayList<>());
        builder.clienteSistemaDto.setEmpresas(new ArrayList<>());
        return builder;
    }

    public ClienteSistemaDtoBuilder comPagamento() {
        this.clienteSistemaDto.getPagamentos().add(PagamentoSistemaDtoBuilder.builder().build());
        return this;
    }

    public ClienteSistemaDtoBuilder comEmpresa() {
        this.clienteSistemaDto.getEmpresas().add(EmpresaDtoBuilder.builder().build());
        return this;
    }

    public ClienteSistemaDto build() {
        return clienteSistemaDto;
    }

}
