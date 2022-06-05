package paymentgateway.usermanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import paymentgateway.usermanager.model.Marchand;
import paymentgateway.usermanager.service.MarchandService;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/marchand")
public class MarchandController {

    @Autowired
    MarchandService marchandService;

    /*public MarchandController(MarchandService marchandService) {
        this.marchandService = marchandService;
    }*/
    @GetMapping("/all")
    public ResponseEntity<List<Marchand>> getAllMarchand(){
        List<Marchand> marchands=marchandService.findAllMarchand();
        return new ResponseEntity<>(marchands, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Marchand> getMarchandById(@PathVariable("id")Long id){
        Marchand marchand=marchandService.findMarchandById(id);
        return new ResponseEntity<>(marchand, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Marchand> addMarchand(@RequestBody Marchand marchand) {
        Marchand newMarchand = marchandService.addMarchand(marchand);
        return new ResponseEntity<>(newMarchand, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Marchand> updateEmployee(@RequestBody Marchand marchand) {
        Marchand updateMarchand = marchandService.updateMarchand(marchand);
        return new ResponseEntity<>(updateMarchand, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMarchand(@PathVariable("id") Long id) {
        marchandService.deleteMarchand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}