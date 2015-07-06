package pl.gmatusz.confitura2015.viacom.cvs;

import org.apache.commons.csv.CSVRecord;
import pl.gmatusz.confitura2015.viacom.model.Person;

import java.util.Arrays;

/**
 * Created by gmatusz on 2015-07-06.
 */
public class PersonFactory implements CsvToObjectMapper {

     public Person createFromCSVRecord(CSVRecord record) {
         return new Person(record.get(0), Integer.parseInt(record.get(1).trim()));
     }

    @Override
    public Iterable<String> getColumnsFromObject(Object obj) {
        if (obj instanceof Person) {
            String name = ((Person) obj).getName();
            String age = Integer.toString(((Person) obj).getAge());
            return Arrays.asList(new String [] {name, age});
        } else {
            throw new IllegalArgumentException("Invalid argument type: " + obj.getClass().getCanonicalName());
        }

    }
}
