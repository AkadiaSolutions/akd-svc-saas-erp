package br.akd.svc.akadia.models.dto.site.empresa.mocks;

import br.akd.svc.akadia.models.dto.site.empresa.DadosEmpresaDeletadaDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class DadosEmpresaDeletadaDtoBuilder {

    DadosEmpresaDeletadaDtoBuilder() {
    }

    DadosEmpresaDeletadaDto dadosEmpresaDeletadaDto;

    public static DadosEmpresaDeletadaDtoBuilder builder() {
        DadosEmpresaDeletadaDtoBuilder builder = new DadosEmpresaDeletadaDtoBuilder();
        builder.dadosEmpresaDeletadaDto = new DadosEmpresaDeletadaDto();
        builder.dadosEmpresaDeletadaDto.setId(1L);
        builder.dadosEmpresaDeletadaDto.setDataRemocao(LocalDate.of(2023, 2, 12).toString());
        builder.dadosEmpresaDeletadaDto.setHoraRemocao(LocalTime.of(13, 22).toString());
        builder.dadosEmpresaDeletadaDto.setCnpj("12123123000121");
        builder.dadosEmpresaDeletadaDto.setRazaoSocial("KG DIMOPOULOS NOVA ERA LTDA");
        builder.dadosEmpresaDeletadaDto.setInscricaoMunicipal(null);
        builder.dadosEmpresaDeletadaDto.setInscricaoEstadual("145574080114");
        return builder;
    }

    public DadosEmpresaDeletadaDto build() {
        return dadosEmpresaDeletadaDto;
    }

}
