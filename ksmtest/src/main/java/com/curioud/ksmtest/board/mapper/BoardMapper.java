package com.curioud.ksmtest.board.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
  Map<String, Object> selectOneBoardDetail(
    @Param("no") int no
  );
}
