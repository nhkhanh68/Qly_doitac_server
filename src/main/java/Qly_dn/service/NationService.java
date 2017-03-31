package Qly_dn.service;

import Qly_dn.DTO.ContinentDTO;
import Qly_dn.DTO.NationDTO;
import Qly_dn.model.Continent;
import Qly_dn.model.Nation;
import Qly_dn.repository.ContinentRepository;
import Qly_dn.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nhkha on 25/03/2017.
 */
@Service
public class NationService {
    private final
    ContinentRepository continentRepository;

    private final
    NationRepository nationRepository;

    @Autowired
    public NationService(ContinentRepository continentRepository, NationRepository nationRepository) {
        this.continentRepository = continentRepository;
        this.nationRepository = nationRepository;
    }
    public Continent createContinent(ContinentDTO continentDTO) {
        Continent continent = continentRepository.findByContinentName(continentDTO.getContinentName());
        if(continent == null){
            Continent newContinent = new Continent();
            newContinent.setContinentName(continentDTO.getContinentName());
            return continentRepository.save(newContinent);
        } else{
            throw new NullPointerException("Châu lục đã tồn tại!");
        }
    }

    public void createNation(List<NationDTO> listNationDTO, int continentId){
        Continent continent = continentRepository.findOne(continentId);
        if(continent == null){
            throw new NullPointerException("Châu lục không tồn tại");
        } else{
            Set<Nation> setNation = new HashSet<>();
//            add(new Book("Book B1", categoryB));
            for(NationDTO nationDTO: listNationDTO){
                if(nationRepository.findByNationName(nationDTO.getNationName()) == null){
                    setNation.add(new Nation(nationDTO.getNationName(), continent));
                }
            }
            continent.setNation(setNation);
            continentRepository.save(continent);
        }
    }

    public List<Continent> getAllContinent(){
//        List<User> allUsers = (List<User>)
        return (List<Continent>) continentRepository.findAll();
    }

    public void deleteNation(int nationId) {
        Nation nation = nationRepository.findOne(nationId);
        if(nation != null){
            nationRepository.delete(nationId);
        } else{
            throw new NullPointerException("Không tim thấy quốc gia cần được xóa!");
        }
    }
}
