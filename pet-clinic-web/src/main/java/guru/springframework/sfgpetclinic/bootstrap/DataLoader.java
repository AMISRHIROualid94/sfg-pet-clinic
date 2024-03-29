package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialityService specialitiesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService, SpecialityService specialitiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }

    }

    private void loadData() {

        /* Pet Types start*/
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        System.out.println(dog.getName());

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        System.out.println(cat.getName());
        /* Pet Types end*/

        /* Owners start*/
        Owner owner1 = new Owner();
        owner1.setFirstName("OwnerFN1");
        owner1.setLastName("OwnerLN1");
        owner1.setAddress("Owner Adresse 1");
        owner1.setCity("Owner City 1");
        owner1.setTelephone("06000000000");


        Pet owner1Pet = new Pet();
        owner1Pet.setPetType(saveDogPetType);
        owner1Pet.setOwner(owner1);
        owner1Pet.setBirthDate(LocalDate.now());
        owner1Pet.setName("dodo");
        owner1.getPets().add(owner1Pet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("OwnerFN2");
        owner2.setLastName("OwnerLN2");
        owner2.setAddress("Owner Adresse 2");
        owner2.setCity("Owner City 2");
        owner2.setTelephone("07000000000");

        Pet owner2Pet = new Pet();
        owner2Pet.setPetType(saveCatPetType);
        owner2Pet.setOwner(owner2);
        owner2Pet.setBirthDate(LocalDate.now());
        owner2Pet.setName("coco");
        owner2.getPets().add(owner2Pet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners ...");
        /* Owners end */

        /* Visit Start */

        Visit visit1 = new Visit();
        visit1.setPet(owner1Pet);
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Visit 1 Description");
        visitService.save(visit1);

        /* Visit End */

        /* Specialities start */
        Speciality speciality1 = new Speciality();
        speciality1.setDescription("Speciality1");
        Speciality savedSpec1 = specialitiesService.save(speciality1);

        Speciality speciality2 = new Speciality();
        speciality2.setDescription("Speciality2");
        Speciality savedSpec2 = specialitiesService.save(speciality2);

        /* Specialities end */

        /* Vets start */
        Vet vet1 = new Vet();
        vet1.setFirstName("VetFN1");
        vet1.setLastName("vetLN1");
        vet1.getSpecialities().add(savedSpec1);
        vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setFirstName("VetFN2");
        vet2.setLastName("vetLN2");
        vet2.getSpecialities().add(savedSpec2);
        vetService.save(vet2);

        /*System.out.println("vets specialities: ");
        vet2.getSpecialities().forEach(speciality -> {
            System.out.println(speciality.getId());
        });*/
        System.out.println("Loaded Vets ...");
        /* Vets end */
    }
}
