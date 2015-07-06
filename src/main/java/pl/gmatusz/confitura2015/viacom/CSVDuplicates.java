package pl.gmatusz.confitura2015.viacom;

import org.apache.commons.cli.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import pl.gmatusz.confitura2015.viacom.cvs.CsvToObjectMapper;
import pl.gmatusz.confitura2015.viacom.cvs.PersonFactory;
import pl.gmatusz.confitura2015.viacom.duplicates.DuplicateProcessor;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gmatusz on 2015-07-06.
 */
public class CSVDuplicates {

    private static CsvToObjectMapper objectFactory = new PersonFactory();

    private static Options options = new Options();

    static {
        options.addOption(OptionBuilder.withArgName("format")
                        .hasArg()
                        .withDescription("input csv format: rfc4180|excel|tdf|mysql|default ")
                        .create("f")
        );
        options.addOption(OptionBuilder.withArgName("inputFile")
                        .hasArg()
                        .withDescription("input csv file ")
                        .create("i")
        );
        options.addOption(OptionBuilder.withArgName("outputFile")
                        .hasArg()
                        .withDescription("output csv file ")
                        .create("o")
        );
    }


    private static void usage() {
        System.out.println("java -jar uniq.jar -f <format> -i <inputFile> -o <outputFile>");
        System.out.println("format :== rfc4180|excel|tdf|mysql|default");

    }

    private static List<Object> readInputList(InputStreamReader in, CSVFormat cvsFormat) throws IOException {
        CSVParser parser = new CSVParser(in, CSVFormat.EXCEL);
        List<CSVRecord> list = parser.getRecords();

        List<Object> result = new LinkedList<Object>();
        for (CSVRecord record : list) {
            result.add(objectFactory.createFromCSVRecord(record));
        }

        return result;
    }

    private static void writeLis(Appendable writer, CSVFormat csvFormat, List<Object> records) throws IOException {
        CSVPrinter csvFilePrinter = new CSVPrinter(writer, csvFormat);

        for (Object obj : records) {
            csvFilePrinter.printRecord(objectFactory.getColumnsFromObject(obj));
        }

    }

    private static CSVFormat strToCSVFormat(String fmt) {
        if ("RFC4180".equalsIgnoreCase(fmt)) {
            return CSVFormat.RFC4180;
        } else if ("EXCEL".equalsIgnoreCase(fmt)) {
            return CSVFormat.EXCEL;
        } else if ("TDF".equalsIgnoreCase(fmt)) {
            return CSVFormat.TDF;
        } else if ("MYSQL".equalsIgnoreCase(fmt)) {
            return CSVFormat.MYSQL;
        } else {
            return CSVFormat.DEFAULT;
        }
    }

    public static void main(String[] args) {
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT;
            InputStreamReader reader = null;
            Appendable output = System.out;

            CommandLineParser cliParser = new GnuParser();

            CommandLine line = cliParser.parse(options, args);

            if (line.hasOption("f")) {
                csvFormat = strToCSVFormat(line.getOptionValue("f"));
            }
            if (line.hasOption("o")) {
                output = new FileWriter(line.getOptionValue("o"));
            }
            if (line.hasOption("i")) {
                reader = new FileReader(line.getOptionValue("i"));

                List list = readInputList(reader, csvFormat);

                DuplicateProcessor duplicateProcessor = new DuplicateProcessor(list.size()/8, 0.5f);

                List result = duplicateProcessor.uniq(list);

                writeLis(output, csvFormat, result);

            } else {
                usage();
            }

        } catch (ParseException exp) {
            System.out.println("Unexpected exception:" + exp.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
