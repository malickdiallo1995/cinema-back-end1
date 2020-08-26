package org.sid.cinema.web;


 import lombok.Data;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
 import org.springframework.transaction.annotation.Transactional;
 import org.springframework.web.bind.annotation.*;


 import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
 import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    FilmRepository filmRepository;
    @Autowired
    TicketRepository ticketRepository;


    /**
     * Recuperation d'une image en byte
     * @param id
     * @return
     */
    @GetMapping(value = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws Exception{
        //Get film corresponding to id
        Film film = filmRepository.findById(id).get();
        //Get PhotoName of the film
        String photoName = film.getPhoto();
        //Lecture du fichier contenant l'image
        File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName+".jpg");
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        //List Ticket Vendu
        List<Ticket> ticketList = new ArrayList<>();
        //pour chaque ticket dans la liste des tickets on recupere son id
        ticketForm.getTickets().forEach(idTicket->{
            //recuperer le ticket correspondant
            System.out.println("********** Id Ticket "+idTicket);
            Ticket ticket  = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setCodePayment(ticketForm.getCodePayement());
            ticket.setReserve(true);
            ticketRepository.save(ticket);
            //Ajouter le ticket a la list
            ticketList.add(ticket);
        });
        return ticketList;
    }
}

/**
 * Redefinir le
 */
@Data
class TicketForm{
    private String nomClient;
    private int CodePayement;
    private List<Long> tickets = new ArrayList<>() ;
}
