package com.ebstudytemplates3week.service;

import com.ebstudytemplates3week.vo.Category;
import com.ebstudytemplates3week.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    /**
     * 카테고리 목록 출력
     * @return List<Category>
     */
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }
}
