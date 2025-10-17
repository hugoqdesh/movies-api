package lol.hugoqdesh.kmdb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    String path;
    String message;
    int statusCode;
    LocalDateTime localDateTime;
}
