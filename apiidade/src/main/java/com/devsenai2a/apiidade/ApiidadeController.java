package com.devsenai2a.apiidade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ApiidadeController {

    @PostMapping("/converter")
    @ResponseBody
    public Map<String, Object>converterJson(
        @RequestParam double idade,
        @RequestParam String formatoIdade,
        @RequestParam String formatoConversao
    ) {
        
        double resultado = 0;
        String erro = null;

        if(idade > 120 || idade < 0 ){
            erro = "Idade Inválida";
        } else {

            switch(formatoIdade + formatoConversao){
                case "anos" + "meses" : resultado = idade * 12; break;
                case "anos" + "dias" : resultado = idade * 365; break;
                case "meses" + "anos" : resultado = idade / 12;  break;
                case "meses" + "dias" : resultado = idade * 30;  break;
                case "dias" + "anos" : resultado = idade * 365;  break;
                case "dias" + "meses" : resultado = idade / 30; break;
            }

			if(resultado > 18){
                 maiorMenor = "maior de idade";
             } else{
                 maiorMenor = "menor de idade";
             }
           
        }

        Map<String, Object> resp = new HashMap<>();
	    resp.put ("resultado", resultado);
		resp.put ("menor ou maior de idade", maiorMenor);
	    resp.put ("erro", erro);

        return resp;
    }
    
   

}
