package model;

import org.json.JSONArray;

import java.io.IOException;

public interface ReaderInterface {
    JSONArray getMax() throws IOException;

    JSONArray getMin() throws IOException;
}
