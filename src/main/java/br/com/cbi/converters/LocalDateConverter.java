/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author tiago.teixeira
 */
@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {

    /**
     * Método que converte o atributo para o banco de dados
     *
     * @param attribute
     * @return
     */
    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null) {
            return attribute.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }

    /**
     * Método que converte o atributo para a classe de entidade
     *
     * @param dbData
     * @return
     */
    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        if (!dbData.equals("")) {
            return LocalDate.parse(dbData, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return null;
    }

}
