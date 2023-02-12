package br.akd.svc.akadia.models.dto.site.empresa.mocks;

import br.akd.svc.akadia.models.dto.global.mocks.EnderecoDtoBuilder;
import br.akd.svc.akadia.models.dto.global.mocks.TelefoneDtoBuilder;
import br.akd.svc.akadia.models.dto.site.empresa.EmpresaDto;
import br.akd.svc.akadia.models.dto.site.empresa.fiscal.mocks.ConfigFiscalEmpresaDtoBuilder;
import br.akd.svc.akadia.models.enums.site.SegmentoEmpresaEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EmpresaDtoBuilder {

    EmpresaDtoBuilder() {
    }

    EmpresaDto empresaDto;

    public static EmpresaDtoBuilder builder() {
        EmpresaDtoBuilder builder = new EmpresaDtoBuilder();
        builder.empresaDto = new EmpresaDto();

        builder.empresaDto.setId(1L);
        builder.empresaDto.setDataCadastro(LocalDate.of(2023, 2, 3).toString());
        builder.empresaDto.setHoraCadastro(LocalTime.of(10, 48).toString());
        builder.empresaDto.setNome("Akadia Solutions");
        builder.empresaDto.setRazaoSocial("AKADIA LTDA");
        builder.empresaDto.setCnpj("12345678000112");
        builder.empresaDto.setEndpoint("akadiasolutions");
        builder.empresaDto.setEmail("akadia@gmail.com");
        builder.empresaDto.setNomeFantasia("Akadia Solutions");
        builder.empresaDto.setInscricaoEstadual("12345667787867");
        builder.empresaDto.setInscricaoMunicipal("12345667787867");
        builder.empresaDto.setNomeResponsavel("Gabriel Lagrota");
        builder.empresaDto.setCpfResponsavel("47153427821");
        builder.empresaDto.setLogo(new byte[]{});
        builder.empresaDto.setDeletada(false);
        builder.empresaDto.setDadosEmpresaDeletada(null);
        builder.empresaDto.setSegmentoEmpresaEnum(SegmentoEmpresaEnum.BATERIA_AUTOMOTIVA);
        builder.empresaDto.setTelefone(null);
        builder.empresaDto.setConfigFiscalEmpresa(null);
        builder.empresaDto.setEndereco(null);
        builder.empresaDto.setChamados(new ArrayList<>());

        return builder;
    }

    public EmpresaDtoBuilder comTelefone() {
        this.empresaDto.setTelefone(TelefoneDtoBuilder.builder().build());
        return this;
    }

    public EmpresaDtoBuilder comEndereco() {
        this.empresaDto.setEndereco(EnderecoDtoBuilder.builder().build());
        return this;
    }

    public EmpresaDtoBuilder comConfigFiscalComTodasNf() {
        this.empresaDto.setConfigFiscalEmpresa(ConfigFiscalEmpresaDtoBuilder.builder()
                .comNfe()
                .comNfce()
                .comNfse()
                .build());
        return this;
    }

    public EmpresaDto build() {
        return empresaDto;
    }

}
