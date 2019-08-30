package ujr.flightstore.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class FlightStoreErrorAdvice {
	
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }
	
	@ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException e) {
        return error(NOT_FOUND, e);
    }
	
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception at FlightStore: ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }

}
