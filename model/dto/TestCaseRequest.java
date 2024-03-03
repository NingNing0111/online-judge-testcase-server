package com.ningning0111.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 23:46
 * @Description:
 */
@Data
public class TestCaseRequest {
    private Long questionId;
    private List<String> input;
    private List<String> output;
}
