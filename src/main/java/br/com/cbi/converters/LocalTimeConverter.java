/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cbi.converters;

import br.com.fs.api.util.Datas;
import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author tiago.teixeira
 */
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime attribute) {
        if (attribute != null) {
            return Datas.localTimeToTime(attribute);
        }
        return null;
    }

    @Override
    public LocalTime convertToEntityAttribute(Time dbData) {
        if (dbData != null) {
            return dbData.toLocalTime();
        }
        return null;
    }

}
