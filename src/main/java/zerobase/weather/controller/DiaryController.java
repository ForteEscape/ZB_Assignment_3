package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @PostMapping("/create/diary")
    public void createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식: yyyy-mm-dd", example = "2023-05-24") LocalDate date,
            @RequestBody @ApiParam(value = "일기 내용") String text
    ){
        diaryService.createDiary(date, text);
    }

    @ApiOperation(value = "선택한 날의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    public List<Diary> readDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식: yyyy-mm-dd", example = "2023-05-24") LocalDate date
    ){
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    public List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 시작 날짜", example = "2023-05-01") LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 마지막 날짜", example = "2023-05-31") LocalDate endDate
    ){
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날의 일기를 수정할 수 있습니다.")
    @PutMapping("/update/diary")
    public void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "수정할 일기의 날짜", example = "2023-05-24") LocalDate date,
            @RequestBody String text
    ){
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날의 일기를 삭제할 수 있습니다.")
    @DeleteMapping("/delete/diary")
    public void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "삭제할 일기의 날짜", example = "2023-05-24") LocalDate date
    ){
        diaryService.deleteDiary(date);
    }
}
