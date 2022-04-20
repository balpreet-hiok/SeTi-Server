package org.bstechnologies.SeTiServer.Permission;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PermissionManager {
    public PermissionManager() throws IOException, ParseException {
        File file = new File("data/permissions.txt");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(file));
    }
}
