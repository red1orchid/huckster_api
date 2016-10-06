package huckster.cabinet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Marina on 06.10.16.
 */
@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Wrong authorization token")  // 403
public class UnauthorizedException extends RuntimeException {
    // ...
}
