package dev.malkon.events.error.domain

import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.time.LocalDateTime

class RequestErrorTest extends Specification {

    def 'should create object and get proper fields values - RequestError(HttpStatus, Throwable)'() {
        given:
        def throwableMsg = "Test purpose throwable"
        def requestError = new RequestError(HttpStatus.BAD_REQUEST, new Throwable(throwableMsg))

        expect:
        requestError.getStatus() == HttpStatus.BAD_REQUEST
        requestError.getTimestamp().isBefore(LocalDateTime.now())
        requestError.getMessage() == throwableMsg
        requestError.getDebugMessage() == throwableMsg
        requestError.getSubErrors() != null
        requestError.getSubErrors().size() == 0
    }

    def 'should create object and get proper fields values - RequestError(HttpStatus, Throwable, List<RequestSubError)'() {
        given:
        def throwableMsg = "Test purpose throwable"
        def subErrors = [new ValidationError(null, null, null, null)]
        def requestError = new RequestError(HttpStatus.BAD_REQUEST, new Throwable(throwableMsg), subErrors)

        expect:
        requestError.getStatus() == HttpStatus.BAD_REQUEST
        requestError.getTimestamp().isBefore(LocalDateTime.now())
        requestError.getMessage() == throwableMsg
        requestError.getDebugMessage() == throwableMsg
        requestError.getSubErrors() != null
        requestError.getSubErrors().size() == 1
    }
}
