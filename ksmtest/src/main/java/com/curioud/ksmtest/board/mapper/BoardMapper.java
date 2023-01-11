package com.curioud.ksmtest.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
  Map<String, Object> selectOneBoardDetail(
    @Param("no") int no
  );

  List<Map<String, Object>> selectAllComment(
    @Param("boardNo") int boardNo
  );

  void insertBoard(
    @Param("subject") String subject,
    @Param("content") String content
  );

  void updateBoard(
    @Param("no") int no,
    @Param("subject") String subject,
    @Param("content") String content
  );

  void insertComment(
    @Param("boardNo") int boardNo,
    @Param("comment") String comment
  );

  void deleteBoard(
    @Param("boardNo") int boardNo
  );

  void updateThumnail(
    @Param("boardNo") int boardNo,
    @Param("fileName") String fileName
  );
}
