package com.duykypaul.painthouse.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResultResp {
    private int status = 1;
    private Object data;
    private String message;

    public ResultResp(Object data) {
        this.data = data;
    }
}
