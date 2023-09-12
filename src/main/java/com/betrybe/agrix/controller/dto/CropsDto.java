package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;

/**
 * Dto for creating Crops.
 *
 * @param name name of the Crop created
 * @param plantedArea planted area of the created Crop
 */
public record CropsDto(String name, Double plantedArea) {

  public Crop toCrop(Farm farm) {
    return new Crop(name, plantedArea, farm);
  }
}