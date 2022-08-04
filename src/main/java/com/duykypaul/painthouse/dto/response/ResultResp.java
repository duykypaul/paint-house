package com.duykypaul.painthouse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
