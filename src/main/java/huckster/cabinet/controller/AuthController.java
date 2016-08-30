package huckster.cabinet.controller;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Perevalova Marina on 28.08.2016.
 */
abstract class AuthController {
    int getCompanyId(@RequestParam(value = "token") String token) {
        //auth
        return 1;
    }
}
