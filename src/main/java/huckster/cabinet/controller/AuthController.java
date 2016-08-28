package huckster.cabinet.controller;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Perevalova Marina on 28.08.2016.
 */
abstract class AuthController {
    int auth(@RequestParam(value="token") String token) {
        return 1;
    }
}
