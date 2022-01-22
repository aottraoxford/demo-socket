package com.socket.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Component
public class Response {

    private Object data;
    private int status;
    private String message;
    private int totalPage;
    private Long totalElement;
    private boolean isPage;

    public Response() {
        this.status = 200;
        this.message = "success";
    }

    public Response data(Object obj) {
        if (obj instanceof Page<?>) {
            this.data = ((Page<?>) obj).getContent();
            this.totalElement = ((Page<?>) obj).getTotalElements();
            this.totalPage = ((Page<?>) obj).getTotalPages();
            isPage = true;
        } else {
            this.data = obj;
            isPage = false;
        }
        return this;
    }

    public Response status(int status) {
        this.status = status;
        this.getHttpStatus();
        return this;
    }

    public Response message(String message) {
        this.message = message;
        return this;
    }

    public ResponseEntity<Object> end() {
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        res.put("status", status);
        if (data!=null) {
            res.put("data", data);
            if (isPage) {
                res.put("totalPage", totalPage);
                res.put("totalElement", totalElement);
            }
        }

        HttpStatus httpStatus = this.getHttpStatus();
        this.reset();
        return new ResponseEntity<>(res, httpStatus);
    }

    private HttpStatus getHttpStatus() {
        HttpStatus httpStatus = HttpStatus.OK;
        switch(this.status) {
            case 200: {
                this.message = "success";
                break;
            }
            case 400: {
                this.message = "bad request";
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            }
            case 404: {
                this.message = "not found";
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            }
            default: {
                this.message = "internal server error";
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return httpStatus;
    }

    private void reset() {
        message = "success";
        status = 200;
        data = null;
        totalPage = 0;
        totalElement = 0L;
    }
}
