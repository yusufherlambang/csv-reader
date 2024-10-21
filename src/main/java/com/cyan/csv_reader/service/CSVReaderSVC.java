package com.cyan.csv_reader.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVReaderSVC {
    public List<String[]> readCsvFile(String filePath) {
        List<String[]> records = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            System.out.println("----------------Input----------------------" + br);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                records.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }

    public List<String[]> filterData(List<String[]> data) {
        List<String[]> filteredData = new ArrayList<>();


        for (String[] record : data) {
            // Example filter: Add only rows where the value in the last column is "selected" or "Yes"
            if ("selected".equals(record[record.length -1]) || "Yes".equals(record[record.length -1])) {
                filteredData.add(record);
            }
        }

        System.out.println("-------------record size----------- : "+filteredData.stream().count());

        return filteredData;
    }

    public void writeCsvFile(String outputFilePath, List<String[]> filteredData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String[] record : filteredData) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
