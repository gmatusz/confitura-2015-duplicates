package pl.gmatusz.confitura2015.viacom.cvs;

import org.apache.commons.csv.CSVRecord;
import pl.gmatusz.confitura2015.viacom.model.Person;

/**
 * Created by gmatusz on 2015-07-06.
 */
public interface CsvToObjectMapper {
    Object createFromCSVRecord(CSVRecord record);

    Iterable<String> getColumnsFromObject(Object obj);
}
