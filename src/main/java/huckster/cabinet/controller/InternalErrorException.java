package huckster.cabinet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by PerevalovaMA on 29.08.2016.
 */
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)  // 500
public class InternalErrorException extends RuntimeException {
    // ...
}
