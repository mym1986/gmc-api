package com.gmc.gmccoin.api.users.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepoExImpl implements UserRepoEx {
    private final JPAQueryFactory jpaQueryFactory;

}
