package main.exceptionmapper;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMapperProvider {
    @ServerExceptionMapper
    public RestResponse<String> mapInvalidRowNumberExceptionException(InvalidRowNumberException e) {
        return RestResponse.status(RestResponse.Status.NOT_ACCEPTABLE, e.getMessage());
    }
}
