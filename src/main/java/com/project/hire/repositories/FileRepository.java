package com.project.hire.repositories;

import com.project.hire.models.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
}
