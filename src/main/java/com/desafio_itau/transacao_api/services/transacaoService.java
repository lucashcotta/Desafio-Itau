package com.desafio_itau.transacao_api.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

import com.desafio_itau.transacao_api.dto.TransacaoServiceDto;
import com.desafio_itau.transacao_api.exceptions.UnprocessableEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransacaoService {
    
    private final List<TransacaoServiceDto> listaTransacoes = new ArrayList();

    public void adicionarTransacoes(TransacaoServiceDto dto){
        log.error("Iniciado o processo de gravar transações!");
       

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data e hora atuais");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        }
        if(dto.valor() < 0 ){
            log.error("Valor não pode ser menor do que 0.");
            throw new UnprocessableEntity("Valor não pode ser menor do que 0.");
        }

        listaTransacoes.add(dto);

    }

    public void deletarTransacoes(){
        listaTransacoes.clear();
    }

    public List<TransacaoServiceDto> buscaTransacoes(int time){

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(time);

        return listaTransacoes.stream().filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();

    }
    
    
}
