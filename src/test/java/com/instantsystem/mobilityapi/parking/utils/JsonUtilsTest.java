package com.instantsystem.mobilityapi.parking.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.instantsystem.mobilityapi.parking.entity.Root;
import com.instantsystem.mobilityapi.parking.exception.MobilityException;

/**
 * JSON Utility test class
 * 
 * @author jearfi
 */
class JsonUtilsTest {

    @Test
    void testJsonToRoot() throws Exception {

        String url = "https://data.grandpoitiers.fr/api/records/1.0/search/" +
                "?dataset=mobilite-parkings-grand-poitiers-donnees-metiers&rows=1000&" +
                "facet=nom_du_parking&facet=zone_tarifaire&facet=statut2&facet=statut3";

        Root root = JsonUtils.jsonToRoot(url);
        assertNotNull(root);
        assertNotNull(root.getRecords());
        assertEquals(26, root.getRecords().size());
    }

    @Test
    void testJsonToRootWrongUrl() throws Exception {

        String url = "https://wrong.url";

        Exception exception = assertThrows(MobilityException.class, () -> {
            JsonUtils.jsonToRoot(url);
        });

        String expectedMessage = "An error occured during the creation of the Root object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
