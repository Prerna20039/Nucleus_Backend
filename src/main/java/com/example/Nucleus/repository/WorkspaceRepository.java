package com.example.Nucleus.repository;

import com.example.Nucleus.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    @Query("""
        SELECT DISTINCT w
        FROM Workspace w
        LEFT JOIN w.projects p
        LEFT JOIN p.users u
        WHERE w.user.id = :userId
           OR u.id = :userId
    """)
    List<Workspace> findAccessibleWorkspaces(@Param("userId") Long userId);

    Optional<Workspace> findById(Long id);
}