package com.heech.member.core.repository;

import com.heech.member.core.domain.Gender;
import com.heech.member.core.domain.Member;
import com.heech.member.core.dto.MemberSearchCondition;
import com.heech.member.core.dto.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.heech.member.core.domain.QMember.member;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 회원 목록 조회
     */
    public Page<Member> findMembers(MemberSearchCondition condition, Pageable pageable) {
        List<Member> members = getMemberList(condition, pageable);

        JPAQuery<Long> count = getMemberListCount(condition);

        return PageableExecutionUtils.getPage(members, pageable, count::fetchOne);
    }

    /**
     * 회원 목록
     */
    private List<Member> getMemberList(MemberSearchCondition condition, Pageable pageable) {
        return queryFactory
                .select(member)
                .from(member)
                .where(
                        searchCondition(condition.getSearchCondition(), condition.getSearchKeyword()),
                        searchGenderEq(condition.getSearchGender())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(member.id.desc())
                .fetch();
    }

    /**
     * 회원 목록 카운트
     */
    private JPAQuery<Long> getMemberListCount(MemberSearchCondition condition) {
        return queryFactory
                .select(member.count())
                .from(member)
                .where(
                        searchCondition(condition.getSearchCondition(), condition.getSearchKeyword()),
                        searchGenderEq(condition.getSearchGender())
                );
    }

    /**
     * where searchCondition LIKE '%searchKeyword%'
     */
    private BooleanExpression searchCondition(SearchCondition searchCondition, String searchKeyword) {
        if (searchCondition == null || !hasText(searchKeyword)) {
            return null;
        }

        if (SearchCondition.NAME.equals(searchCondition)) {
            return member.name.contains(searchKeyword);
        } else if (SearchCondition.EMAIL.equals(searchCondition)) {
            return member.email.contains(searchKeyword);
        }

        return null;
    }

    /**
     * where gender == 'searchGender'
     */
    private BooleanExpression searchGenderEq(Gender searchGender) {
        return searchGender == null ? null : member.gender.eq(searchGender);
    }
}
