package br.ifsp.contacts.repository;

import br.ifsp.contacts.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}