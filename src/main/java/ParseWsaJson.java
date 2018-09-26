/*
 * Copyright 2016-17 by Cisco Systems
 * All rights reserved.
 * This software is the confidential and proprietary information
 * of Cisco Systems,  ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Cisco Systems.
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseWsaJson {

    private static final Pattern pattern = Pattern.compile("(.\\/wlc_?.*_)(\\d{13})(.json:1:)");

    //this is grep out if collected json with "grep -arnw '.' -e '<search String>'"
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/niumapat/Downloads/APRF.txt"));
        CSVPrinter csvPrinter = new CSVPrinter(new BufferedWriter(
                new FileWriter("/Users/niumapat/Downloads/APRF.csv")), CSVFormat.EXCEL.withHeader(
                        "timestamp", "apMac", "slot", "txBytes", "rxBytes"));
        String pathname = "/Users/niumapat/Downloads/APRF_data/";
        File file = new File(pathname);
        if (!file.exists()) {
            file.mkdirs();
        }

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            String prefix = line.substring(0, line.indexOf(".json:1:") + 8);
            String json = line.substring(prefix.length());
            Matcher matcher = pattern.matcher(prefix);
            String fileName = prefix.substring(2, prefix.length() - 3);
            String timestamp = "";
            if (matcher.find()) {
                timestamp = matcher.group(2);
            }
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
            try {
                JsonPath.read(document, "$.payload.wlc.ops.wireless.ap-rf-stats.aps[*].radio[*].statistics.traffic.tx-bytes");
                List apArray = JsonPath.read(document, "$.payload.wlc.ops.wireless.ap-rf-stats.aps[*]");
                for (Object element : apArray) {
                    Map apMap = (Map) element;
                    String mac = (String) apMap.get("mac");
                    List radios = (List) apMap.get("radio");
                    if (radios == null) continue;
                    for (Object radio : radios) {
                        try {
                            String slot = String.valueOf(((Map) radio).get("slot"));
                            String txBytes = String.valueOf(
                                    ((Map) ((Map) ((Map) radio).get("statistics")).get("traffic")).get("tx-bytes"));
                            String rxBytes = String.valueOf(
                                    ((Map) ((Map) ((Map) radio).get("statistics")).get("traffic")).get("rx-bytes"));
                            csvPrinter.printRecord(timestamp, mac, slot, txBytes, rxBytes);
                        } catch (NullPointerException ex) {
                            continue;
                        }
                    }
                }
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter(pathname + fileName));
                fileWriter.write(json);
                fileWriter.close();

            } catch (PathNotFoundException e) {
                continue;
            }

        }
        reader.close();
        csvPrinter.flush();
    }
}
