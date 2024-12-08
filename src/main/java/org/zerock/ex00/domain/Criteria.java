package org.zerock.ex00.domain;

import lombok.Data;

@Data // Lombok을 사용하여 Getter, Setter, equals, hashCode, toString 등을 자동 생성합니다.
public class Criteria {

    private int pageNum = 1; // 현재 페이지 번호를 나타내는 필드, 기본값은 1입니다.
    private int amount = 10; // 한 페이지당 보여줄 게시물 수를 나타내는 필드, 기본값은 10입니다.

    private String[] types; // 검색 조건(제목, 내용, 작성자)을 배열로 저장하는 필드
    private String keyword; // 검색 키워드를 저장하는 필드

    private String typeStr; // 검색 조건(types)을 문자열로 변환하여 저장하는 필드

    // types 배열을 문자열로 변환하여 typeStr 필드에 저장하는 메서드
    public void setTypes(String[] types) {
        this.types = types;

        if (types != null && types.length > 0) {
            typeStr = String.join("", types); // types 배열을 빈 문자열("")로 구분하여 하나의 문자열로 결합
        }
    }

    // pageNum 필드의 Getter 메서드
    public int getPageNum() {
        return pageNum;
    }

    // pageNum 필드의 Setter 메서드
    public void setPageNum(int pageNum) {
        if (pageNum <= 0) { // 페이지 번호가 0 이하일 경우
            this.pageNum = 1; // 기본값으로 1을 설정
            return;
        }
        this.pageNum = pageNum;
    }

    // amount 필드의 Getter 메서드
    public int getAmount() {
        return amount;
    }

    // amount 필드의 Setter 메서드
    public void setAmount(int amount) {
        if (amount <= 10 || amount > 100) { // amount가 10 이하이거나 100을 초과할 경우
            this.amount = 10; // 기본값으로 10을 설정
            return;
        }
        this.amount = amount;
    }

    // 현재 페이지의 시작 위치를 계산하여 반환하는 메서드
    public int getSkip() {
        return (this.pageNum - 1) * this.amount; // (현재 페이지 번호 - 1) * 한 페이지당 보여줄 게시물 수
    }
}
