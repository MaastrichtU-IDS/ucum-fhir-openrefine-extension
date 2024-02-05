package org.um.ids.utils;

import org.fhir.ucum.UcumEssenceService;
import org.fhir.ucum.UcumService;

import java.io.InputStream;

public class UCUMHelper {
    static private UcumService ucumService;

    static public UcumService getUcumService() {
        if (ucumService == null) {
            try (InputStream ucumStream = UCUMHelper.class.getClassLoader().getResourceAsStream("ucum-essence.xml")) {
                ucumService = new UcumEssenceService(ucumStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ucumService;
    }
}
