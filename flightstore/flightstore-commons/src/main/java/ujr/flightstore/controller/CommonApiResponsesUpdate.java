package ujr.flightstore.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ApiResponses(value = {
	@ApiResponse(code = 202, message = "Request accepted"),
    @ApiResponse(code = 401, message = "Not authorized"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 403, message = "Access forbidden"),
	@ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Internal Server Error")
})
public @interface CommonApiResponsesUpdate {
}