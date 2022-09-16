package com.heech.member.exception;

import com.heech.member.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class EntityNotFound extends CommonException {

    public EntityNotFound(String entityName, Long entityId) {
        super("존재하지 않은 " + entityName + "입니다. id = " + entityId);
    }

    public EntityNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
