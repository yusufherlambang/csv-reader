package com.cyan.csv_reader.controller;

import com.cyan.csv_reader.service.CSVReaderSVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/readCsv")
public class CSVReaderCTRL {

    @Autowired
    private CSVReaderSVC csvReaderSVC;

//    @GetMapping("/getFile")
//    public List<String[]> readCsv(@RequestParam(defaultValue = "") String filePath) {
//        System.out.println("--------------------- "+filePath);
//        return csvReaderSVC.readCsvFile(filePath);
//    }

    @GetMapping("/process-csv")
    public String processCsv(@RequestParam("inputFilePath") String inputFilePath,
                             @RequestParam("outputFilePath") String outputFilePath) {

        // Read data from the input CSV file
        List<String[]> data = csvReaderSVC.readCsvFile(inputFilePath);

        // Filter the data based on the custom criteria
        List<String[]> filteredData = csvReaderSVC.filterData(data);

        // Write the processed data to the output CSV file
        csvReaderSVC.writeCsvFile(outputFilePath, filteredData);

        return "CSV file processed and saved to: " + outputFilePath;
    }
}
