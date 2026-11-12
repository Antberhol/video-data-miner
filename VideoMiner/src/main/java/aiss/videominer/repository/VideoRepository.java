package aiss.videominer.repository;

import aiss.videominer.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    Page<Video> findByNameContaining(String name, Pageable pageable);
    Page<Video> findByDescriptionContaining(String description, Pageable pageable);
}