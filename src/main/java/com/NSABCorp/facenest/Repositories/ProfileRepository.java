package com.NSABCorp.facenest.Repositories;

import com.NSABCorp.facenest.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
