package br.akd.svc.akadia.models.entities.global.mocks;

import br.akd.svc.akadia.modules.global.entity.ParentescoEntity;
import br.akd.svc.akadia.modules.global.enums.GrauParentescoEnum;

public class ParentescoEntityBuilder {

    ParentescoEntityBuilder(){}
    ParentescoEntity parentescoEntity;

    public static ParentescoEntityBuilder builder() {
        ParentescoEntityBuilder builder = new ParentescoEntityBuilder();
        builder.parentescoEntity = new ParentescoEntity();
        builder.parentescoEntity.setId(1L);
        builder.parentescoEntity.setNome("Heitor Gonçalves Lagrota");
        builder.parentescoEntity.setDataNascimento("2021-04-11");
        builder.parentescoEntity.setCpf("588.543.987-21");
        builder.parentescoEntity.setGrauParentescoEnum(GrauParentescoEnum.FILHO);
        builder.parentescoEntity.setTelefone(TelefoneEntityBuilder.builder().build());
        return builder;
    }

    public ParentescoEntity build() {
        return parentescoEntity;
    }
}
