package com.myhotel.challenge.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;

@Slf4j
public class TestUtils {

    public static boolean matchJson(String responseString, String expectedString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode response = mapper.readTree(responseString);
            JsonNode expected = mapper.readTree(expectedString);
            if (ObjectUtils.notEqual(response, expected)) {
                log.error("expected and response are not equal!");
                return false;
            } else {
                return true;
            }
        } catch (IOException var5) {
            return false;
        }
    }
}
