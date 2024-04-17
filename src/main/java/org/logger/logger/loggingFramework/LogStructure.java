package org.logger.logger.loggingFramework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.mysql.cj.log.Log;
import lombok.*;
import org.logger.logger.TraceIdMw;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.service.annotation.GetExchange;

import java.io.PrintWriter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class LogStructure {
    LogLevel logLevel;
    long timeStamp;
    UUID reqId;
    String tag;
    Object val;

    public static ThreadLocal<LogStructure> requestIdHolder = new ThreadLocal<>();

    public static void log(LogLevel logLevel, Long timeStamp, String tag, Object val) {
        LogStructure logStructure = new LogStructure();
        logStructure.logLevel = logLevel;
        logStructure.timeStamp = timeStamp;
        logStructure.tag = tag;
        logStructure.val = val;

        LogStructure reqIdLogStructure = TraceIdMw.getLogWithRequestId();
        logStructure.reqId = reqIdLogStructure.reqId;

        Gson gson = new Gson();
        String logInString = gson.toJson(logStructure);
        System.out.println(logInString);
    }


    public static void logInfo(String tag, Object val) {
        long timeStamp = System.currentTimeMillis();
        log(LogLevel.INFO, timeStamp, tag, val);
    }

    public static void logIncomingApi(Object request, Object response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json1 = mapper.writeValueAsString(request);
        String json2 = mapper.writeValueAsString(response);
        ObjectNode combinedJson = mapper.createObjectNode();
        combinedJson.set("request_body", mapper.readTree(json1));
        combinedJson.set("response_body", mapper.readTree(json2));

        long timeStamp = System.currentTimeMillis();
        log(LogLevel.INFO, timeStamp, "INCOMING_API",combinedJson);
    }

}
