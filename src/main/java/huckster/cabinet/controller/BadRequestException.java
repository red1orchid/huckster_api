package huckster.cabinet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by PerevalovaMA on 29.08.2016.
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No such Order")  // 404
public class BadRequestException extends RuntimeException {
    // ...
}
