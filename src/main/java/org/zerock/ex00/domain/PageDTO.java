package org.zerock.ex00.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    private int startPage; // 시작 페이지 번호
    private int endPage;   // 화면에 보여질 마지막 페이지 번호
    private boolean prev, next;

    private int total;
    private Criteria cri;

    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        // endPage 계산
        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

        // startPage 계산
        this.startPage = this.endPage - 9;

        // 실제 마지막 페이지 번호
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        // endPage 조정
        if (realEnd <= this.endPage) {
            this.endPage = realEnd;
        }

        // 이전 페이지 버튼 활성화 여부
        this.prev = this.startPage > 1;

        // 다음 페이지 버튼 활성화 여부
        this.next = this.endPage < realEnd;
    }
}
