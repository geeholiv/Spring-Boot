package com.devsenai2a.apiidade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ApimedianotasController {

    @PostMapping("/calcular")
    @ResponseBody
    public Map<String, Object>calcularJson(
            @RequestParam double nota1,
            @RequestParam double nota2,
            @RequestParam double nota3,
            @RequestParam double nota4,
            @RequestParam String nome
    ) {
        Map<String, Object> resp = new HashMap<>();
        
        double media = (nota1+nota2+nota3+nota4) / 4;
        String situfinal;

        if ( media  >= 7){
            situfinal = "Aprovado.";
        } else if (media >= 5 && media < 7) {
            situfinal = "Recuperação.";
        } else {
            situfinal = "Reprovado.";
        }
        
        resp.put("Aluno: ", nome);
        resp.put("Notas: ", nota1 +","+nota2+","+nota3+","+nota4);
        resp.put("Média: ", media);
        resp.put("Situação final: ", situfinal);

        return resp;
    }



}