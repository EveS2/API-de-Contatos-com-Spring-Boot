package br.ifsp.contacts.controller;

import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;

    public AddressController(AddressRepository addressRepository,
                             ContactRepository contactRepository) {

        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contacts/{id}/addresses")
    public ResponseEntity<Address> createAddress(
            @PathVariable Long id,
            @RequestBody Address address) {

        Optional<Contact> contactOptional =
                contactRepository.findById(id);

        if (contactOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Contact contact = contactOptional.get();

        address.setContact(contact);

        Address savedAddress = addressRepository.save(address);

        return ResponseEntity.ok(savedAddress);
    }

    @GetMapping("/contacts/{id}/addresses")
    public ResponseEntity<List<Address>> getAddressesByContact(
            @PathVariable Long id) {

        Optional<Contact> contactOptional =
                contactRepository.findById(id);

        if (contactOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Contact contact = contactOptional.get();

        return ResponseEntity.ok(contact.getAddresses());
    }
}
