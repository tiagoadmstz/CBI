/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.converters;

import br.com.fs.api.util.Datas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author tiago.teixeira
 */
@Converter(autoApply = true)
public class LocalDate_DateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null) {
            return Datas.getDate(attribute.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else {
            return null;
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        //2018-07-05 16:02:46.000
        if (dbData != null) {
            return LocalDate.parse(Datas.getDateString(dbData), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return null;
    }
}
