package com.devsenai1A.ConversorMoedas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConversorMoedasController {
    
    @GetMapping("/realParaDolar")
    public double realParaDolar(@RequestParam double real) {
        return real * 0.19;
    }
    @GetMapping("/realParaEuro")
    public double realParaEuro(@RequestParam double real) {
        return real * 0.16;
    }
    @GetMapping("/dolarParaReal")
    public double dolarParaReal(@RequestParam double dolar) {
        return dolar * 5;
    }
    @GetMapping("/dolarParaEuro")
    public double dolarParaEuro(@RequestParam double dolar) {
        return dolar * 0.86;
    }
    @GetMapping("/euroParaDolar")
    public double euroParaDolar(@RequestParam double euro) {
        return euro * 6.23;
    }
    @GetMapping("/euroParaReal")
    public double euroParaReal(@RequestParam double euro) {
        return euro * 1.17;
    }

    
    @PostMapping("/ConversorMoedas")
    @ResponseBody
    
    public Map<String, Object> calcularJson(
            @RequestParam double real,
            @RequestParam double dolar,
            @RequestParam double euro,
            @RequestParam String operacao){
        
        double resultado=0;
        String erro= null;
        
        switch(operacao){
            case "realParaDolar": resultado = real * 0.19; break;
            case "realParaEuro": resultado = real * 0.16; break;
            case "dolarParaReal": resultado = dolar * 5; break;
            case "dolarParaEuro": resultado = dolar * 0.86; break;
            case "euroParaReal": resultado = euro * 6.23; break;
            case "euroParaDolar": resultado = euro * 1.17; break;
            default:
                erro="Operação Inválida!";
                
        }
        Map<String, Object> resp = new HashMap<>();
        resp.put("resultado", resultado);
        resp.put("erro", erro);
        return resp;
            
        }
    }