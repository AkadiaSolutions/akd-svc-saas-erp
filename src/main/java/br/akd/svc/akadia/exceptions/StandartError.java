package br.akd.svc.akadia.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandartError {
    private LocalDateTime localDateTime;
    private Integer status;
    private String error;
    private String path;
}
