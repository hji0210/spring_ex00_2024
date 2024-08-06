package org.zerock.ex00.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleDTOList {



    // SampleDTO 객체들의 리스트를 저장할 필드
    private List<SampleDTO> list = new ArrayList<>();
}
