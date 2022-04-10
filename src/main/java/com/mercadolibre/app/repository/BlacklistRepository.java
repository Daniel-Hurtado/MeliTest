/**
 * 
 */
package com.mercadolibre.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.app.model.Blacklist;

/**
 * 
 * Interface que permite realizar las operaciones CRUD sobre la entidad Blacklist
 * @author Alejandro.Hurtado
 *
 */
@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {

	Optional<Blacklist> findByIpAddress(String ip);
	
	
	Optional<Blacklist> findByBlacklistId(Long blacklistId);
}
