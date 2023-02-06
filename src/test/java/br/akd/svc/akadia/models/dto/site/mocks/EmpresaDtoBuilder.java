package br.akd.svc.akadia.models.dto.site.mocks;

import br.akd.svc.akadia.models.dto.site.EmpresaDto;
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
        builder.empresaDto.setEmail("akadia@gmail.com");
        builder.empresaDto.setNomeFantasia("Akadia Solutions");
        builder.empresaDto.setInscricaoEstadual("12345667787867");
        builder.empresaDto.setInscricaoMunicipal("12345667787867");
        builder.empresaDto.setNomeResponsavel("Gabriel Lagrota");
        builder.empresaDto.setCpfResponsavel("47153427821");
        builder.empresaDto.setLogo(new byte[]{});
        builder.empresaDto.setSegmentoEmpresaEnum(SegmentoEmpresaEnum.BATERIA_AUTOMOTIVA);
        builder.empresaDto.setTelefone(null);
        builder.empresaDto.setClienteSistema(null);
        builder.empresaDto.setConfigFiscalEmpresa(null);
        builder.empresaDto.setEndereco(null);
        builder.empresaDto.setChamados(new ArrayList<>());

        return builder;
    }

    public EmpresaDto build() {
        return empresaDto;
    }

}
