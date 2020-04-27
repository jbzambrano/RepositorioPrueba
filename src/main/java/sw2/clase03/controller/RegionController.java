package sw2.clase03.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.clase03.entity.Region;
import sw2.clase03.entity.Shipper;
import sw2.clase03.repository.RegionRepository;
import sw2.clase03.repository.ShipperRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Region> lista = regionRepository.findAll();
        model.addAttribute("regionList", lista);

        return "region/list";
    }

    @GetMapping("/new")
    public String nuevaRegionFrm() {
        return "region/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevaRegion(Region region, RedirectAttributes attr, Model model) {

        Optional<Region> opt =regionRepository.findById(region.getRegionid());

        if (opt.isPresent()) {
            attr.addFlashAttribute("msgId","El ID que desea ingresar ya existe");
            model.addAttribute("region",region);
            return "redirect:/region/list";
        } else {
            regionRepository.save(region);
            attr.addFlashAttribute("msgCreate","Region creada correctamente");
            return "redirect:/region/list";
        }
    }


    @PostMapping("/forcesave")
    public String guardarEdicionRegion(Region region, RedirectAttributes attr, Model model) {

        Optional<Region> opt =regionRepository.findById(region.getRegionid());
            regionRepository.save(region);
            attr.addFlashAttribute("msgUpdate","Region actualizada correctamente");
            return "redirect:/region/list";

    }

    @GetMapping("/edit")
    public String editarRegion(Model model,
                                      @RequestParam("id") String id) {

        Optional<Region> opt = regionRepository.findById(id);

        if (opt.isPresent()) {
            Region region = opt.get();
            model.addAttribute("region", region);
            return "region/editFrm";
        } else {
            return "redirect:/region/list";
        }

    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") String id,
                                      RedirectAttributes attr) {

        Optional<Region> opt = regionRepository.findById(id);

        if (opt.isPresent()) {
            regionRepository.deleteById(id);
            attr.addFlashAttribute("msgDelete","La región ha sido borrada");
        }
        return "redirect:/region/list";

    }

}
