package com.ningning0111;

import com.ningning0111.model.entity.TestCase;
import com.ningning0111.repository.TestCaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Project: com.ningning0111
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 19:49
 * @Description:
 */
@SpringBootTest
public class MongoDBTest {

    @Autowired
    private TestCaseRepository repository;


    @Test
    public void test1() {
        TestCase testcase = TestCase.builder()
                .questionId(1000L)
                .inputData(List.of("1 3", "2 4", "3 6"))
                .outputData(List.of("4", "6", "9"))
                .build();
        repository.save(testcase);
    }

    @Test
    public void test2(){
        List<TestCase> cases = repository.findAll();
        System.out.println(cases);
    }

    @Test
    public void test3(){
        TestCase testCase = repository.queryTestCaseByQuestionId(1000L);
        System.out.println(testCase);
    }
}
