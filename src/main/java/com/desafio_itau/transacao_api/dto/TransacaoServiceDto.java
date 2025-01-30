package com.desafio_itau.transacao_api.dto;

import java.time.OffsetDateTime;

public record TransacaoServiceDto(Double valor, OffsetDateTime dataHora) {

}
