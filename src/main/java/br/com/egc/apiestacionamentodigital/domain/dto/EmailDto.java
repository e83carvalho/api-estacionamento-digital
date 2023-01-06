package br.com.egc.apiestacionamentodigital.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailDto implements Serializable {

    private String emailTo;
    private String subject;
    private String text;
    private String name;

}
