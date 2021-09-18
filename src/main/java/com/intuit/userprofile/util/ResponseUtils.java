package com.intuit.userprofile.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * @author prajwal.kulkarni on 18/09/21
 */
public class ResponseUtils {

    public static ResponseEntity<Object> ok(Map<String, Object> dataMap, String errorMsg, boolean success) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", success ? "SUCCESS" : "ERROR");
        result.put("error", errorMsg);
        result.put("data", dataMap);

        Response.ok(dataMap).build();

        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
}
