package org.com.br.filipe.vendas.rest;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrors {

    private List<String> errors;

    public ApiErrors() {
    }

    public ApiErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }


}
