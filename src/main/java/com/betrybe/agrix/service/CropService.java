package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

/**
 * Service layer for everything that interacts with the crop entity.
 */
@Service
public class CropService {

  private CropRepository cropRepository;

  /**
   * Service layer constructor.
   *
   * @param cropRepository Repository received by injection
   *        dependency
   */
  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  /**
   * Saves the Crop by Farm id.
   *
   * @param newCrop receives a new crop to be saved.
   * @return returns the saved crop.
   */
  public Crop saveCropByFarmId(Crop newCrop) {

    Crop cropToCreate = this.cropRepository.save(newCrop);

    Optional<Crop> cropToFound = this.cropRepository.findById(cropToCreate.getId());

    Crop cropFound = cropToFound.get();

    return cropFound;

  }

  /**
   * Method that searches the database for all plantations.
   *
   * @return returns a list of all plantations.
   */
  public List<Crop> getAllCrops() {
    List<Crop> allCrops = this.cropRepository.findAll();

    return allCrops;
  }

  /**
   * Method that searches for crops based on id.
   *
   * @param id receives the searched id.
   * @return returns an optional with the returned plantation.
   */
  public Crop getCropById(Long id) throws CropNotFoundException {
    Optional<Crop> cropToFound = this.cropRepository.findById(id);

    if (cropToFound.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropToFound.get();
  }

  /**
   * Returns the crops with the harvest date between the
   * searched dates.
   *
   * @param startingDate starting date of search
   * @param endingDate end date of search
   * @return returns a list of crops that meet the requirement
   */
  public List<Crop> searchCropByDate(LocalDate startingDate, LocalDate endingDate) {

    List<Crop> allCrops = this.cropRepository.findAll();
    List<Crop> cropsReturned = allCrops.stream()
        .filter(crop -> startingDate.isBefore(crop.getHarverstDate())
            && endingDate.isAfter(crop.getHarverstDate()))
        .toList();

    return cropsReturned;
  }

}