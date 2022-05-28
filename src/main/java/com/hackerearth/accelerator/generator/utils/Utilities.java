package com.hackerearth.accelerator.generator.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@UtilityClass
@Slf4j
public class Utilities {
    public void printResults(Process process) throws IOException {
        try (InputStream inputStream = process.getInputStream();
             InputStreamReader isr = new InputStreamReader(inputStream);
             InputStream errorStream = process.getErrorStream();
             InputStreamReader esr = new InputStreamReader(errorStream)) {

            int n1;
            char[] c1 = new char[1024];
            StringBuilder stableOutput = new StringBuilder();
            while ((n1 = isr.read(c1)) > 0) {
                stableOutput.append(c1, 0, n1);
            }
            log.debug("Stable Output: " + stableOutput);

            int n2;
            char[] c2 = new char[1024];
            StringBuilder stableError = new StringBuilder();
            while ((n2 = esr.read(c2)) > 0) {
                stableError.append(c2, 0, n2);
            }
            log.error("Stable Error: " + stableError.toString());
        } catch (IOException exception) {
            log.error("Error printing message");
        }

    }

}
