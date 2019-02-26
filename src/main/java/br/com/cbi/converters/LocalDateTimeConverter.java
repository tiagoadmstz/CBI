/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.converters;

import br.com.fs.api.util.Datas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author tiago.teixeira
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute != null) {
            return Datas.getDateTime(attribute.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.S")));
        } else {
            return null;
        }
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        //2018-07-05 16:02:46.000
        if (dbData != null) {
            try {
                return LocalDateTime.parse(Datas.getDateTimeString(dbData), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.S"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
