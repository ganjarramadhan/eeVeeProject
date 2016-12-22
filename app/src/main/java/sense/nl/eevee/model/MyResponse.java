package sense.nl.eevee.model;

import java.util.List;

/**
 * Created by ganjarramadhan on 12/22/16.
 */

public class MyResponse<T> {

    private List<T> results;
    private Info info;

    public static class Info {
        private String seeds;
        private int results;
        private int page;
        private String version;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
