package com.twohire.payment.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomSave<T> {

    <S extends T> S save(S s);
}
