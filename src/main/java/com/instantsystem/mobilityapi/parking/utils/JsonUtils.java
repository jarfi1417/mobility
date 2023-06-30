package com.instantsystem.mobilityapi.parking.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instantsystem.mobilityapi.parking.entity.Root;
import com.instantsystem.mobilityapi.parking.exception.MobilityException;

import lombok.experimental.UtilityClass;

/**
 * JSON utiliy class
 * 
 * @author jearfi
 */
@UtilityClass
public class JsonUtils {

    /**
     * Allows to create a new {@link Root} object containing parking data from
     * the json obtained with the given url
     * 
     * @param url the link url
     * @return a new {@link Root} object containing parking
     * @throws MobilityException if an error occurred
     */
    public static Root jsonToRoot(String url) throws MobilityException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(getJsonStringFromUrl(url), Root.class);
        } catch (Exception e) {
            throw new MobilityException("An error occured during the creation of the Root object", e);
        }
    }

    /**
     * Allows to get a json string from a given URL
     * 
     * @param urlString the url to read
     * @return a json string
     * @throws MalformedURLException if the url is malformed
     * @throws MobilityException if an error occurred
     */
    private static String getJsonStringFromUrl(String urlString) throws MobilityException, MalformedURLException {
        String result = null;
        URL url = new URL(urlString);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = br.read(chars)) != -1)
                buffer.append(chars, 0, read);

            result = buffer.toString();
        } catch (Exception e) {
            throw new MobilityException("An error occured reading the URL: " + urlString);
        }
        return result;
    }

}
