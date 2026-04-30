package br.com.desafio.geozip.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAddressRepository extends JpaRepository<AddressEntity, String> {
}
