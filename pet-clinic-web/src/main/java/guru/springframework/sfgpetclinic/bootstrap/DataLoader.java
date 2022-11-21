package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private final OwnerService ownerService;
    @Autowired
    private final VetService vetService;

    @Autowired
    private final PetTypeService petTypeService;
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        System.out.println(dog.getName());

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        System.out.println(cat.getName());

        Owner owner1 = new Owner();
        owner1.setFirstName("OwnerFN1");
        owner1.setLastName("OwnerLN1");
        owner1.setAddress("Owner Adresse 1");
        owner1.setCity("Owner City 1");
        owner1.setTelephone("06000000000");

        Pet  owner1Pet = new Pet();
        owner1Pet.setPetType(saveDogPetType);
        owner1Pet.setOwner(owner1);
        owner1Pet.setBirthDate(LocalDate.now());
        owner1Pet.setName("dodo");
        owner1.getPets().add(owner1Pet);
        /* Pet  owner1Pet2 = new Pet();
        owner1Pet2.setPetType(saveCatPetType);
        owner1Pet2.setOwner(owner1);
        owner1Pet2.setBirthDate(LocalDate.now());
        owner1Pet2.setName("sisi");
        owner1.getPets().add(owner1Pet2);
        */
        ownerService.save(owner1);
        /*
        owner1.getPets().forEach(pet -> {
            System.out.println("owner1 pets :" + pet.getName());
        });
         */
        Owner owner2 = new Owner();
        owner2.setFirstName("OwnerFN2");
        owner2.setLastName("OwnerLN2");
        owner2.setAddress("Owner Adresse 2");
        owner2.setCity("Owner City 2");
        owner2.setTelephone("07000000000");

        Pet  owner2Pet = new Pet();
        owner2Pet.setPetType(saveCatPetType);
        owner2Pet.setOwner(owner2);
        owner2Pet.setBirthDate(LocalDate.now());
        owner2Pet.setName("coco");
        owner2.getPets().add(owner2Pet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners ...");

        Vet vet1 = new Vet();
        vet1.setFirstName("VetFN1");
        vet1.setLastName("vetLN1");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("VetFN2");
        vet2.setLastName("vetLN2");
        vetService.save(vet2);

        System.out.println("Loaded Vets ...");
    }
}
