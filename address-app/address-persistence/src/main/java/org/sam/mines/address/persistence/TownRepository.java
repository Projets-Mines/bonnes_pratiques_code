package org.sam.mines.address.persistence;

import org.sam.mines.address.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TownRepository extends JpaRepository<Town, UUID> {
}
