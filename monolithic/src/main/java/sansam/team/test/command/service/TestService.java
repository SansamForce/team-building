package sansam.team.test.command.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sansam.team.test.command.dto.TestDTO;
import sansam.team.test.command.entity.Test;
import sansam.team.test.command.repository.TestRepository;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final ModelMapper modelMapper;
    /* Test Post 작업*/
    public TestDTO createTest(String content){

        TestDTO testDTO = new TestDTO(content);
        Test test = modelMapper.map(testDTO, Test.class);
        testRepository.save(test);
        return testDTO;
    }

    @Transactional
    public TestDTO updateTest(Long id, String content) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 데이터가 존재하지 않습니다 : " + id));

        test.modifyContent(content);  // 업데이트된 content 설정

        return new TestDTO(test.getContent());  // 업데이트된 DTO 반환
    }

    // Test Delete
    public boolean deleteTest(Long id) {
        if (testRepository.existsById(id)) {
            testRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
