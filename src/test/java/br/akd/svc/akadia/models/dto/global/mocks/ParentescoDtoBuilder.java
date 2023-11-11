package br.akd.svc.akadia.models.dto.global.mocks;

import br.akd.svc.akadia.modules.global.dto.ParentescoDto;
import br.akd.svc.akadia.modules.global.enums.GrauParentescoEnum;

public class ParentescoDtoBuilder {

    ParentescoDtoBuilder() {
    }

    ParentescoDto parentescoDto;

    public static ParentescoDtoBuilder builder() {
        ParentescoDtoBuilder builder = new ParentescoDtoBuilder();
        builder.parentescoDto = new ParentescoDto();
        builder.parentescoDto.setId(1L);
        builder.parentescoDto.setNome("Heitor Gonçalves Lagrota");
        builder.parentescoDto.setDataNascimento("2021-04-11");
        builder.parentescoDto.setCpf("588.543.987-21");
        builder.parentescoDto.setGrauParentescoEnum(GrauParentescoEnum.FILHO);
        builder.parentescoDto.setTelefone(TelefoneDtoBuilder.builder().build());
        return builder;
    }

    public ParentescoDto build() {
        return parentescoDto;
    }
}
