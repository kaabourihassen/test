package paymentgateway.usermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paymentgateway.usermanager.exception.UserNotFoundException;
import paymentgateway.usermanager.model.Marchand;
import paymentgateway.usermanager.repo.MarchandRepo;

import java.util.List;
import java.util.UUID;

@Service
public class MarchandService {
    private final MarchandRepo marchandRepo;

    @Autowired
    public MarchandService(MarchandRepo marchandRepo) {
        this.marchandRepo = marchandRepo;
    }

    public Marchand addMarchand(Marchand marchand){
        marchand.setMarchandCode(UUID.randomUUID().toString());
        return marchandRepo.save(marchand);
    }
    public List<Marchand> findAllMarchand(){
        return marchandRepo.findAll();
    }
    public Marchand updateMarchand(Marchand marchand){
        return marchandRepo.save(marchand);
    }

    public Marchand findMarchandById(Long id){
        return marchandRepo.findMarchandById(id) .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteMarchand(Long id){
        marchandRepo.deleteMarchandById(id);
    }
}
