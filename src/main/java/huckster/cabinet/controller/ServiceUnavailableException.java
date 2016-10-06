package huckster.cabinet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by PerevalovaMA on 29.08.2016.
 */
@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE, reason="Service temporary unavailable")  // 503
public class ServiceUnavailableException extends RuntimeException {
    // ...
}
