package com.totvs.totvsapi.auth.infrastructure.repository;

import com.totvs.totvsapi.auth.infrastructure.model.TokenModel;
import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

@Repository
@Scope(WebApplicationContext.SCOPE_REQUEST)
public interface TokenRepository extends JpaRepository<TokenModel, UUID>
{

}
