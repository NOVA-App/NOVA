package com.sehbeomschool.nova.domain.news.dao;

import com.sehbeomschool.nova.domain.news.domain.News;
import com.sehbeomschool.nova.domain.news.domain.NewsInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewsInfoRepository extends JpaRepository<NewsInfo, Long> {

}
