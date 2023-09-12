package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropResponseDto;
import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.service.CropService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of all methods in the /crops route.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private CropService cropService;

  /**
   * CropController controller constructor.
   *
   * @param cropService receives the service layer by
   *        dependency injection.
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Map the GET /crops route with the function of returning a list of
   * all the plantations in the database.
   *
   * @return returns a CropResponseDto list of all the crops.
   */
  @GetMapping
  public ResponseEntity<List<CropResponseDto>> getAllCrops() {

    List<Crop> allCrops = this.cropService.getAllCrops();

    List<CropResponseDto> cropsResponse = allCrops.stream()
        .map(crop -> new CropResponseDto(crop.getId(), crop.getName(),
            crop.getPlantedArea(), crop.getFarm().getId()))
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropsResponse);
  }

  /**
   * Maps the GET /crops/id route to return the information of a crop
   * specifies.
   *
   * @param id id of the searched crop passed by Path
   * @return returns a ResponseEntity with the specified crop or an error
   *        if it is not found.
   */
  @GetMapping("/{id}")
  public ResponseEntity getCropById(@PathVariable Long id) {
    try {

      Crop cropFound = this.cropService.getCropById(id);

      CropResponseDto cropResponse = new CropResponseDto(cropFound.getId(),
          cropFound.getName(), cropFound.getPlantedArea(), cropFound.getFarm().getId());

      return ResponseEntity.status(HttpStatus.OK).body(cropResponse);

    } catch (CropNotFoundException cropNotFoundException) {

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cropNotFoundException.getMessage());

    }
  }
}