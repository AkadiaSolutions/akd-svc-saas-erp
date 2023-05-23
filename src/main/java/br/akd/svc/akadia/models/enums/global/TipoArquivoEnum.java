package br.akd.svc.akadia.models.enums.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoArquivoEnum {

    JPG(0, "jpeg"),
    PNG(1, "png"),
    PDF(2, "pdf"),
    DOCX(3, "docx");

    private final int code;
    private final String desc;

}
