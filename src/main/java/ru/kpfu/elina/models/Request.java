package ru.kpfu.elina.models;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Request {

    private Map<String, String> headers;
    private String method;
    private String url;
    private String body;
    private String datetime;

    public Request(JsonNode json) {
        this.method = json.get("method").asText();
        this.url = json.get("url").asText();

        JsonNode headersNode = json.get("headers");
        if (headersNode != null) {
            this.headers = new HashMap<>();
            headersNode.fields().forEachRemaining(key -> {
                this.headers.put(key.getKey(), key.getValue().asText());
            });
        }

        this.body = !json.get("body").toString().equals("\"\"") ? json.get("body").toPrettyString() : "";
        this.datetime = json.get("datetime").asText();
    }

    public Request(String url, String method, Map<String, String> headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.datetime = new Date().toString();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return method + " - " + url;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, method, body, headers);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Request req = (Request) obj;
        return Objects.equals(url, req.url) &&
                Objects.equals(method, req.method) &&
                Objects.equals(body, req.body) &&
                Objects.equals(headers, req.headers);
    }

}
