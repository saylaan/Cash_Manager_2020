package com.cashmanager.group.repository;

import com.cashmanager.group.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
