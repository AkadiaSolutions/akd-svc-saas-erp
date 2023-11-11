package br.akd.svc.akadia.modules.web.models.entity.empresa.mocks;

import br.akd.svc.akadia.modules.web.models.entity.empresa.DadosEmpresaDeletadaEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class DadosEmpresaDeletadaEntityBuilder {

    DadosEmpresaDeletadaEntityBuilder() {
    }

    DadosEmpresaDeletadaEntity dadosEmpresaDeletadaEntity;

    public static DadosEmpresaDeletadaEntityBuilder builder() {
        DadosEmpresaDeletadaEntityBuilder builder = new DadosEmpresaDeletadaEntityBuilder();
        builder.dadosEmpresaDeletadaEntity = new DadosEmpresaDeletadaEntity();
        builder.dadosEmpresaDeletadaEntity.setId(1L);
        builder.dadosEmpresaDeletadaEntity.setDataRemocao(LocalDate.of(2023, 2, 13).toString());
        builder.dadosEmpresaDeletadaEntity.setHoraRemocao(LocalTime.of(14, 41).toString());
        builder.dadosEmpresaDeletadaEntity.setCnpj("11111111000111");
        builder.dadosEmpresaDeletadaEntity.setRazaoSocial("UNIGRUBS LTDA");
        builder.dadosEmpresaDeletadaEntity.setInscricaoMunicipal(null);
        builder.dadosEmpresaDeletadaEntity.setInscricaoEstadual("145544071114");
        return builder;
    }

    public DadosEmpresaDeletadaEntity build() {
        return dadosEmpresaDeletadaEntity;
    }

}
