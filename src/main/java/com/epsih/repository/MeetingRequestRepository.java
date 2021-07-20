package com.epsih.repository;

import com.epsih.model.meeting.MeetingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRequestRepository extends JpaRepository<MeetingRequest, Long> {
}
