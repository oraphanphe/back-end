package ws.personnel.tax.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice

public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorDto> generateException(ResponseStatusException re)
	{
		ErrorDto dto = new ErrorDto();
		dto.setTimestamp(new Date().toString());
		dto.setStatus( String.valueOf( re.getStatus().value()));
		dto.setErrorMessage(re.getReason());
		
		return new ResponseEntity<ErrorDto>(dto,re.getStatus());
	}
}
