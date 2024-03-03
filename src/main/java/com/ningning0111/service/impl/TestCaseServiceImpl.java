package com.ningning0111.service.impl;

import cn.hutool.core.io.FileUtil;
import com.ningning0111.model.dto.TestCaseRequest;
import com.ningning0111.model.entity.TestCase;
import com.ningning0111.model.enums.BaseResponse;
import com.ningning0111.model.enums.ErrorCode;
import com.ningning0111.model.enums.ResultUtils;
import com.ningning0111.repository.TestCaseRepository;
import com.ningning0111.service.TestCaseService;
import com.ningning0111.utils.ZipUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 23:54
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TestCaseServiceImpl implements TestCaseService {
    private final TestCaseRepository repository;
    @Override
    public BaseResponse saveTestCaseByList(TestCaseRequest request) {
        Long questionId = request.getQuestionId();
        List<String> input = request.getInput();
        List<String> output = request.getOutput();
        if(questionId == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"题号不能为空");
        }
        if(output == null || output.isEmpty()){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"题目输出样例不能为空");
        }
        TestCase testCase = TestCase.builder()
                .outputData(output)
                .inputData(input)
                .questionId(questionId)
                .build();
        repository.save(testCase);

        return ResultUtils.success("添加成功");
    }

    @Override
    public BaseResponse saveTestCaseByFile(MultipartFile caseFile) {
        String workDir = System.getProperty("user.dir");
        File zipFile = null;
        if( caseFile == null || caseFile.isEmpty()){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"文件不能为空");
        }
        try {
            // 文件名就是题号
            String caseFileOriginalFilename = caseFile.getOriginalFilename();
            // 去掉后缀
            String questionIdStr = caseFileOriginalFilename.substring(0, caseFileOriginalFilename.indexOf(".zip"));
            Long questionId = Long.valueOf(questionIdStr);
            String zipPath = workDir + File.separator + caseFileOriginalFilename;
            zipFile = FileUtil.newFile(zipPath);
            caseFile.transferTo(zipFile);
            List<String> input = ZipUtils.getContents(zipFile.getPath(), ".in");
            List<String> output = ZipUtils.getContents(zipFile.getPath(), ".out");
            // 输入输出样例不匹配
            if(input != null && output != null && input.size() != output.size()){
                return ResultUtils.error(ErrorCode.PARAMS_ERROR,"测试文件解析异常");

            }
            // 输出样例为空
            if(output == null || output.isEmpty()){
                return ResultUtils.error(ErrorCode.PARAMS_ERROR,"测试文件解析异常");
            }
            TestCase testCase = TestCase.builder()
                    .questionId(questionId)
                    .inputData(input)
                    .outputData(output)
                    .build();
            repository.save(testCase);

            return ResultUtils.success("添加成功");
        }catch (IOException e) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "文件资源异常");
        }catch (Exception e){
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
        }finally {
            if(FileUtil.exist(zipFile)){
                FileUtil.del(zipFile);
            }
        }

    }

    @Override
    public BaseResponse deleteTestCase(Long questionId) {
        repository.deleteTestCaseByQuestionId(questionId);
        return ResultUtils.success("题号: "+questionId+" 删除成功");
    }

    @Override
    public BaseResponse queryTestCase(Long questionId) {
        TestCase testCase = repository.queryTestCaseByQuestionId(questionId);
        return ResultUtils.success(testCase);
    }
}
