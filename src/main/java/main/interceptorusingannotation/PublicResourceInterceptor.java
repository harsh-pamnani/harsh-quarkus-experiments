package main.interceptorusingannotation;

import io.smallrye.mutiny.Uni;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lombok.extern.jbosslog.JBossLog;

@PublicResource
@Interceptor
@JBossLog
public class PublicResourceInterceptor {
    @AroundInvoke
    public Object logAndSafeguardFailures(InvocationContext context) throws Throwable {
        log.info("HPInter : interceptor called");
        try {
            Object result = context.proceed();

            if (result instanceof Uni) {
                return ((Uni<?>) result).onFailure().recoverWithUni(error -> Uni.createFrom()
                                                                                .failure(processException(error)));
            }

            return result;
        } catch (Exception error) {
            throw processException(error);
        }
    }

    private Throwable processException(Throwable error) {
        log.error("Some error occurred: ", error);
        return new RuntimeException("An unexpected error occurred. Try again later.");
    }
}
