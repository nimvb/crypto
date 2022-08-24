package com.nimvb.app.cryptoprice.repository;

import com.nimvb.app.cryptoprice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,String> {

    Optional<Token> findByUser_Id(Long user_id);
}
