package com.ningning0111.service;

import com.ningning0111.model.dto.TestCaseRequest;
import com.ningning0111.model.enums.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 23:46
 * @Description:
 */
public interface TestCaseService {

    // 保存

    /**
     * 通过列表形式存储
     * @param request
     * @return
     */
    BaseResponse saveTestCaseByList(TestCaseRequest request);

    /**
     * 通过文件形式存储
     * @param caseFile
     * @return
     */
    BaseResponse saveTestCaseByFile(MultipartFile caseFile);
    // 删除
    BaseResponse deleteTestCase(Long questionId);
    // 改

    // 查
    BaseResponse queryTestCase(Long questionId);

}
