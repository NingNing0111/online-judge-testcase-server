package com.ningning0111.controller;

import com.ningning0111.annotation.AuthCheck;
import com.ningning0111.model.dto.TestCaseRequest;
import com.ningning0111.model.enums.BaseResponse;
import com.ningning0111.model.enums.ErrorCode;
import com.ningning0111.model.enums.ResultUtils;
import com.ningning0111.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 00:18
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/case")
public class TestCaseController {

    private final TestCaseService testCaseService;

    @PostMapping("/add")
    @AuthCheck
    public BaseResponse add(@RequestBody TestCaseRequest request){
        if(request == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"参数不能为空");
        }
        return testCaseService.saveTestCaseByList(request);
    }

    @PostMapping("/addFile")
    @AuthCheck
    public BaseResponse add(MultipartFile caseFile){
        return testCaseService.saveTestCaseByFile(caseFile);
    }

    @AuthCheck
    @GetMapping("/{questionId}")
    public BaseResponse find(@PathVariable Long questionId){
        return testCaseService.queryTestCase(questionId);
    }

    @AuthCheck
    @DeleteMapping("/{questionId}")
    public BaseResponse delete(@PathVariable Long questionId){
        return testCaseService.deleteTestCase(questionId);
    }
}
