package zerobase.weather.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void save() {
        //given
        Memo hello = new Memo(1, "hello");

        //when
        Memo savedMemo = jdbcMemoRepository.save(hello);
        Memo memo = jdbcMemoRepository.findById(savedMemo.getId()).get();
        //then
        assertThat(memo.getId()).isEqualTo(savedMemo.getId());
        assertThat(memo.getText()).isEqualTo(savedMemo.getText());
    }

    @Test
    void findAll() {
        Memo hello1 = new Memo(1, "hello");
        Memo hello2 = new Memo(2, "this is memo");

        jdbcMemoRepository.save(hello1);
        jdbcMemoRepository.save(hello2);

        List<Memo> memoList = jdbcMemoRepository.findAll();

        assertThat(memoList).isNotNull();
        assertThat(memoList.size()).isEqualTo(2);
        assertThat(memoList).contains(hello1, hello2);
    }
}