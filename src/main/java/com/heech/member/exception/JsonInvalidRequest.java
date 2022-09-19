package com.heech.member.exception;

import com.heech.member.common.exception.CommonException;
import com.heech.member.common.json.JsonError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class JsonInvalidRequest extends CommonException {

    public static final String MESSAGE = HttpStatus.BAD_REQUEST.getReasonPhrase();

    public JsonInvalidRequest() {
        super(MESSAGE);
    }

    public JsonInvalidRequest(List<JsonError> errors) {
        super(MESSAGE);
        addError(errors);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
