package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, Long> {
}
