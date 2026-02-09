package com.devsenai2a.apiidade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ApiidadeController {

    @PostMapping("/calcular")
    @ResponseBody
    public Map<String, Object>converterJson(
            @RequestParam double valor,
            @RequestParam double desconto,
    ) {

        double produtocomdesconto = 0;
        String erro = null;

        if( desconto > 50 || desconto < 0){
            erro = "Desconto inválido!";
        } else if (valor < 0){
            erro ="Valor do produto inválido!"
        }
        else {
            double descontoemporcentagem = desconto/100;
            produtocomdesconto = valor - (valor * descontoemporcentagem);
            Map<String, Object> resp = new HashMap<>();
            resp.put("Valor do produto: ", valor);
            resp.put("Valor do desconto: ", desconto+"%");
            resp.put("Valor final a pagar: ", produtocomdesconto)
            resp.put("erro", erro);

            return resp;
        }
    }



}