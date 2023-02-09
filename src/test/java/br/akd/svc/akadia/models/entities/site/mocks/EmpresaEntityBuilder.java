package br.akd.svc.akadia.models.entities.site.mocks;

import br.akd.svc.akadia.models.entities.bckoff.mocks.ChamadoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.EnderecoEntityBuilder;
import br.akd.svc.akadia.models.entities.global.mocks.TelefoneEntityBuilder;
import br.akd.svc.akadia.models.entities.site.EmpresaEntity;
import br.akd.svc.akadia.models.entities.site.fiscal.mocks.ConfigFiscalEmpresaEntityBuilder;
import br.akd.svc.akadia.models.enums.site.SegmentoEmpresaEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EmpresaEntityBuilder {

    EmpresaEntityBuilder() {
    }

    EmpresaEntity empresaEntity;

    public static EmpresaEntityBuilder builder() {
        EmpresaEntityBuilder builder = new EmpresaEntityBuilder();
        builder.empresaEntity = new EmpresaEntity();

        builder.empresaEntity.setId(1L);
        builder.empresaEntity.setDataCadastro(LocalDate.of(2023, 2, 3).toString());
        builder.empresaEntity.setHoraCadastro(LocalTime.of(10, 48).toString());
        builder.empresaEntity.setNome("Akadia Solutions");
        builder.empresaEntity.setRazaoSocial("AKADIA LTDA");
        builder.empresaEntity.setCnpj("12345678000112");
        builder.empresaEntity.setEndpoint("akadiasolutions");
        builder.empresaEntity.setEmail("akadia@gmail.com");
        builder.empresaEntity.setNomeFantasia("Akadia Solutions");
        builder.empresaEntity.setInscricaoEstadual("12345667787867");
        builder.empresaEntity.setInscricaoMunicipal("12345667787867");
        builder.empresaEntity.setNomeResponsavel("Gabriel Lagrota");
        builder.empresaEntity.setCpfResponsavel("47153427821");
        builder.empresaEntity.setLogo(new byte[]{});
        builder.empresaEntity.setSegmentoEmpresaEnum(SegmentoEmpresaEnum.BATERIA_AUTOMOTIVA);
        builder.empresaEntity.setTelefone(null);
        builder.empresaEntity.setConfigFiscalEmpresa(null);
        builder.empresaEntity.setEndereco(null);
        builder.empresaEntity.setChamados(new ArrayList<>());

        return builder;
    }

    public EmpresaEntityBuilder comEndereco() {
        this.empresaEntity.setEndereco(EnderecoEntityBuilder.builder().build());
        return this;
    }

    public EmpresaEntityBuilder comTelefone() {
        this.empresaEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        return this;
    }

    public EmpresaEntityBuilder comConfigFiscalComTodasNf() {
        this.empresaEntity.setConfigFiscalEmpresa(
                ConfigFiscalEmpresaEntityBuilder.builder()
                        .comNfse()
                        .comNfce()
                        .comNfe().build());
        return this;
    }

    public EmpresaEntityBuilder comChamado() {
        this.empresaEntity.getChamados().add(ChamadoEntityBuilder.builder().build());
        return this;
    }

    public EmpresaEntity build() {
        return empresaEntity;
    }

}
