package zerobase.weather.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemoRepositoryTest {
    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest(){
        //given
        Memo newMemo = new Memo(1, "hello memo");

        //when
        Memo savedMemo = jpaMemoRepository.save(newMemo);

        //then
        List<Memo> memoList = jpaMemoRepository.findAll();

        assertThat(memoList.size()).isEqualTo(1);
        assertThat(memoList).contains(savedMemo);
        System.out.println("memoList.get(0).toString() = " + memoList.get(0).toString());
    }

    @Test
    void findByIdTest(){
        //given
        Memo newMemo = new Memo(11, "jpa");
        //when
        Memo saveMemo = jpaMemoRepository.save(newMemo);

        //then
        Optional<Memo> result = jpaMemoRepository.findById(saveMemo.getId());
        assertThat(result.get()).isEqualTo(saveMemo);
    }
}