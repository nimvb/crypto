package com.nimvb.app.cryptoprice.repository;

import com.nimvb.app.cryptoprice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
