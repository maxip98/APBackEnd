package com.portfolio.maxi.Security.Repository;

import com.portfolio.maxi.Security.Entity.Rol;
import com.portfolio.maxi.Security.Enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
