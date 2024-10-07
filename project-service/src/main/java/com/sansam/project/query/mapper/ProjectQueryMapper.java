package com.sansam.project.query.mapper;


import com.sansam.project.query.dto.AdminProjectQueryDTO;
import com.sansam.project.query.dto.ProjectAllQueryDTO;
import com.sansam.project.query.dto.ProjectQueryDTO;
import com.sansam.team.command.infrastructure.dto.ProjectMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectQueryMapper {

    List<ProjectAllQueryDTO> findAllProjectForAdmin();

    List<ProjectAllQueryDTO> findAllProjectForUser(Long userSeq);

    AdminProjectQueryDTO findProjectByIdForAdmin(Long projectSeq);

    ProjectQueryDTO findProjectByIdForUser(Long projectSeq);

    List<ProjectMemberDTO> findProjectMemberByProjectSeq(Long projectSeq);
}